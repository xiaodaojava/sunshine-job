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
 * @date 2020/11/5
 **/
public abstract class AbstractExecutor implements Executor{

    Logger logger = LoggerFactory.getLogger(AbstractExecutor.class);


    private JobLogMapper jobLogMapper;

    public AbstractExecutor() {
        jobLogMapper = ContextHolder.getBean(JobLogMapper.class).get();
    }

    /**
     * 用于提交异步的任务
     *
     * @param jobExec
     */
    @Override
    public String submitAsync(JobExec jobExec) {

        ThreadPoolTools poolTools = ThreadPoolFactory.get();
        Future<Object> submit = poolTools.submit(() -> {
            // 记日志
            JobLog log = new JobLog();
            log.setExecTime(jobExec.getExecTime())
                    .setJobCode(jobExec.getJobCode());
            String result = doSubmitAsync(jobExec);
            log.setResult(result);
            jobLogMapper.insert(log);
            return result;
        });
        return null;
    }

    /**
     * 真正执行逻辑的地方,交给子类去实现
     * @return
     */
    public abstract String doSubmitAsync(JobExec jobExec);

    @Override
    public String submit(JobExec jobExec) {
        return null;
    }
}
