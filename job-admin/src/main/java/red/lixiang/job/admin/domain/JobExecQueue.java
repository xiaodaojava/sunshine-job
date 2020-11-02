package red.lixiang.job.admin.domain;

import red.lixiang.job.admin.dao.JobExecMapper;
import red.lixiang.job.admin.dao.JobLogMapper;
import red.lixiang.job.admin.domain.executor.Executor;
import red.lixiang.job.admin.domain.executor.ExecutorFactory;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.job.admin.model.dos.JobLog;
import red.lixiang.job.admin.model.qc.JobExecQC;
import red.lixiang.tools.common.mybatis.model.Page;
import red.lixiang.tools.common.mybatis.model.Sort;
import red.lixiang.tools.jdk.ListTools;
import red.lixiang.tools.spring.ContextHolder;

import java.util.LinkedList;
import java.util.List;

/**
 * 待执行任务列表
 * 这里要修改,不缓存jobExecList,直接从数据库第一个执行.
 * 然后变量里面有一个下次取任务时间
 * @author lixiang
 * @date 2020/10/17
 **/
public class JobExecQueue {

    private Long nextRunTime = System.currentTimeMillis();


    private JobExecMapper jobExecMapper;

    private JobLogMapper jobLogMapper;

    public JobExecQueue() {
        this.jobExecMapper = ContextHolder.getBean(JobExecMapper.class).get();
        this.jobLogMapper = ContextHolder.getBean(JobLogMapper.class).get();
    }

    public void run(){
            while (true){
                while (System.currentTimeMillis() > nextRunTime ){

                    // 从库里面取出一个任务
                    JobExec job = getOneJobExec();
                    Executor executor = ExecutorFactory.getExecutor(job.getInvokeType());
                    // 先都使用异步去处理
                    String submit = executor.submitAsync(job);

                    // 从库里删掉这个任务
                    jobExecMapper.removeById(job.getId());

                }
            }
    }


    public JobExec getOneJobExec(){
        //从库里面按时间取50个任务,放到List中,并进行判重
        JobExecQC execQC = new JobExecQC();
        execQC.setPage(Page.getOne());
        execQC.addSort("exec_time");
        List<JobExec> dbJobExecList = jobExecMapper.findByQuery(execQC);
        if(ListTools.isBlank(dbJobExecList)){
            return null;
        }
        return dbJobExecList.get(0);

    }



}
