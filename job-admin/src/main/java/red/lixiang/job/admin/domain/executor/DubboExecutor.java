package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.tools.common.dubbo.DubboTools;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class DubboExecutor implements Executor{

    @Override
    public String submitAsync(JobExec jobExec) {
        return null;
    }

    @Override
    public String submit(JobExec jobExec) {
        Object result = DubboTools.invokeNoArg(jobExec.getDubboRegistryUrl(), jobExec.getDubboClass(), jobExec.getDubboMethod());

        return result.toString();
    }
}
