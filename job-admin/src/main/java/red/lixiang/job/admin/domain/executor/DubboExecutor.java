package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.dao.JobExecMapper;
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
public class DubboExecutor implements Executor{

    private JobLogMapper jobLogMapper;

    public DubboExecutor() {
        jobLogMapper = ContextHolder.getBean(JobLogMapper.class).get();
    }

    @Override
    public String submitAsync(JobExec jobExec) {
        ThreadPoolTools poolTools = ThreadPoolFactory.get();
        Future<Object> submit = poolTools.submit(() -> {
            // 记日志
            JobLog log = new JobLog();
            log.setExecTime(jobExec.getExecTime())
                    .setJobCode(jobExec.getJobCode());
            Object result = DubboTools.invokeNoArg(jobExec.getDubboRegistryUrl(), jobExec.getDubboClass(), jobExec.getDubboMethod());
            log.setResult(result.toString());
            jobLogMapper.insert(log);
            return result;
        });
        return null;
    }

    @Override
    public String submit(JobExec jobExec) {

        return null;
    }
}
