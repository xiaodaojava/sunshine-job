package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.model.dos.JobExec;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public interface Executor {

    /**
     * 用于提交异步的任务
     * @param jobExec
     */
    String submitAsync(JobExec jobExec);

    String submit(JobExec jobExec);
}
