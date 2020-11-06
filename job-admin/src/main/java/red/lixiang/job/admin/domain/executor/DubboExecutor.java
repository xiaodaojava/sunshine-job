package red.lixiang.job.admin.domain.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import red.lixiang.job.admin.dao.JobLogMapper;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.job.admin.model.dos.JobLog;
import red.lixiang.tools.common.dubbo.DubboTools;
import red.lixiang.tools.jdk.thread.ThreadPoolFactory;
import red.lixiang.tools.jdk.thread.ThreadPoolTools;
import red.lixiang.tools.spring.ContextHolder;

import java.util.concurrent.Future;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class DubboExecutor extends AbstractExecutor{


    /**
     * 真正执行逻辑的地方,交给子类去实现
     *
     * @param jobExec
     * @return
     */
    @Override
    public String doSubmitAsync(JobExec jobExec) {
        Object result = DubboTools.invokeNoArg(jobExec.getDubboRegistryUrl(), jobExec.getDubboClass(), jobExec.getDubboMethod());
        return result.toString();
    }


}
