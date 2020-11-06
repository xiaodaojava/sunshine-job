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
public class HttpExecutor extends AbstractExecutor{

    /**
     * 真正执行逻辑的地方,交给子类去实现
     *
     * @param jobExec
     * @return
     */
    @Override
    public String doSubmitAsync(JobExec jobExec) {
        String httpUrl = jobExec.getHttpUrl();
        logger.error("正在执行http定时任务,url:{}",httpUrl);
        HttpResponse<String> response = HttpTools.doGet(httpUrl, null, String.class);
        String body = response.getBody();
        return body;
    }

}
