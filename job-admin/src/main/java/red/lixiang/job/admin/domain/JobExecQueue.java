package red.lixiang.job.admin.domain;

import red.lixiang.job.admin.dao.JobExecMapper;
import red.lixiang.job.admin.dao.JobLogMapper;
import red.lixiang.job.admin.domain.executor.Executor;
import red.lixiang.job.admin.domain.executor.ExecutorFactory;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.job.admin.model.dos.JobLog;
import red.lixiang.tools.jdk.ListTools;

import java.util.LinkedList;
import java.util.List;

/**
 * 待执行任务列表
 * @author lixiang
 * @date 2020/10/17
 **/
public class JobExecQueue {

    private Long nextRunTime = System.currentTimeMillis();

    private List<JobExec> jobList = new LinkedList<>();

    private JobExecMapper jobExecMapper;

    private JobLogMapper jobLogMapper;

    public void run(){
            while (true){
                while (System.currentTimeMillis() > nextRunTime && ListTools.isNotBlank(jobList) ){

                    // 从队列中取出一个任务
                    JobExec job = jobList.remove(0);
                    Executor executor = ExecutorFactory.getExecutor(job.getInvokeType());
                    String submit = executor.submit(job);
                    // 记录执行情况
                    JobLog log = new JobLog();
                    log.setJobCode(log.getJobCode());
                    log.setResult(submit);
                    jobLogMapper.insert(log);
                    // 从库里删掉这个任务
                    jobExecMapper.removeById(job.getId());

                }
            }



    }

    public void add(JobExec jobExec){

        if(jobExec==null || jobExec.getExecTime()==null){
            return;
        }
        if(ListTools.isBlank(jobList)){
            // 如果List为空的话,则直接插入
            jobList.add(jobExec);
            return;
        }
        int size = jobList.size();
        for (int i = 0; i < size; i++) {
            JobExec ele = jobList.get(i);
            long eleTimeStamp = ele.getExecTime().getTime();
            long jobTimeStamp = jobExec.getExecTime().getTime();
            if(jobTimeStamp==eleTimeStamp){
                // 如果两个任务的执行时间是一样的
                // 则要看看id是不是一样的,如果ID是一样的话,就跳过
                if(jobExec.getId().equals(ele.getId())){
                    break;
                }
            }
            if(jobTimeStamp<eleTimeStamp){
                // 找到了合适的位置,就插入进去
                jobList.add(i,jobExec);
                break;
            }
            if(i==size-1){
                // 到了最后一个了,还没有找到合适的位置.就直接加在最后
                jobList.add(size,jobExec);
            }
        }
    }

    public void init(){
        //从库里面取50个任务,放到List中,并进行判重


    }



}
