package red.lixiang.job.admin.domain;

import red.lixiang.job.admin.dao.JobExecMapper;
import red.lixiang.job.admin.dao.JobMapper;
import red.lixiang.job.admin.model.dos.Job;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.job.admin.utils.CronTools;
import red.lixiang.tools.jdk.ListTools;
import red.lixiang.tools.spring.ContextHolder;

import java.util.Date;
import java.util.List;

/**
 * @author lixiang
 * @date 2020/10/17
 **/

public class JobDispatch {

    private Long nextCheckTime = System.currentTimeMillis();

    private JobExecMapper jobExecMapper;

    private JobMapper jobMapper;

    public JobDispatch() {
        this.jobExecMapper = ContextHolder.getBean(JobExecMapper.class).get();
        this.jobMapper = ContextHolder.getBean(JobMapper.class).get();
    }

    public void init(){
        // 当前时间大于下次检查的时间时,就开始一遍检查
        while (true){
            while (System.currentTimeMillis()>nextCheckTime){
                List <Job> jobList = jobMapper.findAll();
                long nestTime = System.currentTimeMillis();
                if(ListTools.isBlank(jobList)){
                    // 如果当前列表为空,则30秒之后再检查
                    nextCheckTime = nestTime+30000;
                    continue;
                }
                boolean firstFlag = true;
                for (int i = 0; i < jobList.size(); i++) {
                    // 拿job的cron表达式,算下次运行的时间
                    Job job = jobList.get(i);
                    String cron = job.getCron();
                    Date date = CronTools.nextRunTimeFromCron(cron);
                    if(date == null ){
                        continue;
                    }
                    if(firstFlag){
                        nestTime = date.getTime();
                        firstFlag =false;
                    }
                    // 替换最近的一个时间
                    long nextRunTime = date.getTime();
                    if(nextRunTime<nestTime){
                        nestTime = nextRunTime;
                    }
                    // 如果记录的next_exec_time和当前的下次执行时间一致
                    // 就是已经记到调度里面的,就不用再记了
                    Date nextExecTime = job.getNextExecTime();
                    if(nextExecTime!=null){
                        long execTime = nextExecTime.getTime();
                        if(execTime==nextRunTime){
                            continue;
                        }
                    }
                    // 更新下次执行时间
                    job.setNextExecTime(date);
                    jobMapper.update(job);
                    // 添加到任务执行库里面去
                    JobExec jobExec = JobExec.fromJob(job);
                    jobExecMapper.insert(jobExec);
                }
                System.out.println("当前时间是"+System.currentTimeMillis()+"下次执行时间为:"+nestTime);
                nextCheckTime = nestTime;
            }
        }


    }




}
