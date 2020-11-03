package red.lixiang.job.admin.domain.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import red.lixiang.job.admin.dao.JobLogMapper;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.job.admin.model.dos.JobLog;
import red.lixiang.tools.jdk.http.HttpResponse;
import red.lixiang.tools.jdk.http.HttpTools;
import red.lixiang.tools.jdk.thread.ThreadPoolFactory;
import red.lixiang.tools.jdk.thread.ThreadPoolTools;
import red.lixiang.tools.spring.ContextHolder;

import java.util.concurrent.Future;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class HttpExecutor implements Executor{

    Logger logger = LoggerFactory.getLogger(HttpExecutor.class);

    private JobLogMapper jobLogMapper;

    public HttpExecutor() {
        jobLogMapper = ContextHolder.getBean(JobLogMapper.class).get();
    }

    @Override
    public String submitAsync(JobExec jobExec) {
        ThreadPoolTools poolTools = ThreadPoolFactory.get();
        Future<Object> submit = poolTools.submit(() -> {
            // 现在就先只支持http-get方式
            // 记日志
            JobLog log = new JobLog();
            log.setExecTime(jobExec.getExecTime())
                    .setJobCode(jobExec.getJobCode());
            String httpUrl = jobExec.getHttpUrl();
            logger.error("正在执行http定时任务,url:{}",httpUrl);
            HttpResponse<String> response = HttpTools.doGet(httpUrl, null, String.class);
            String body = response.getBody();
            log.setResult(body);
            jobLogMapper.insert(log);
            return body;
        });
        return null;
    }



    @Override
    public String submit(JobExec jobExec) {
        return null;
    }

}
