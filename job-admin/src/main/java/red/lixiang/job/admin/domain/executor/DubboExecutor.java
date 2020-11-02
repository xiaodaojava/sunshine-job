package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.tools.common.dubbo.DubboTools;
import red.lixiang.tools.jdk.thread.ThreadPoolFactory;
import red.lixiang.tools.jdk.thread.ThreadPoolTools;

import java.util.concurrent.Future;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class DubboExecutor implements Executor{

    @Override
    public String submitAsync(JobExec jobExec) {
        ThreadPoolTools poolTools = ThreadPoolFactory.get();
        Future<Object> submit = poolTools.submit(() -> {
            Object result = DubboTools.invokeNoArg(jobExec.getDubboRegistryUrl(), jobExec.getDubboClass(), jobExec.getDubboMethod());
            return result;
        });
        return null;
    }

    @Override
    public String submit(JobExec jobExec) {

        return null;
    }
}
