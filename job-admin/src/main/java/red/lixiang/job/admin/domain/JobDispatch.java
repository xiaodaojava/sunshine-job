package red.lixiang.job.admin.domain;

import org.springframework.stereotype.Component;
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

    private List<Job> jobList;

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
                jobList = jobMapper.findAll();
                long nestTime = 0;

                if(ListTools.isBlank(jobList)){
                    // 如果当前列表为空,则30秒之后再检查
                    nestTime = System.currentTimeMillis()+30000;
                }
                for (Job job : jobList) {
                    // 拿job的cron表达式,算下次运行的时间
                    String cron = job.getCron();
                    Date date = CronTools.nextRunTimeFromCron(cron);
                    if(date == null ){
                        continue;
                    }
                    // 替换最近的一个时间
                    long time = date.getTime();
                    if(time<nestTime){
                        nestTime = time;
                    }
                    // 添加到任务执行库里面去
                    JobExec jobExec = JobExec.fromJob(job);
                    jobExecMapper.insert(jobExec);
                }
                nextCheckTime = nestTime;
            }
        }


    }




}
