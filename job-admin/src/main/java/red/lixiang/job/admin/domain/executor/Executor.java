package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.model.dos.JobExec;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public interface Executor {

    void exec(JobExec jobExec);

    String submit(JobExec jobExec);
}
