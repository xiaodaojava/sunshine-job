package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.model.dos.JobExec;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class DubboExecutor implements Executor{

    @Override
    public void exec(JobExec jobExec) {

    }

    @Override
    public String submit(JobExec jobExec) {
        return null;
    }
}
