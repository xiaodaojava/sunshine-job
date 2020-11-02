package red.lixiang.job.admin.domain.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.tools.jdk.http.HttpResponse;
import red.lixiang.tools.jdk.http.HttpTools;
import red.lixiang.tools.jdk.thread.ThreadPoolFactory;
import red.lixiang.tools.jdk.thread.ThreadPoolTools;

import java.util.concurrent.Future;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class HttpExecutor implements Executor{

    Logger logger = LoggerFactory.getLogger(HttpExecutor.class);


    @Override
    public String submitAsync(JobExec jobExec) {
        ThreadPoolTools poolTools = ThreadPoolFactory.get();
        Future<Object> submit = poolTools.submit(() -> {
            // 现在就先只支持http-get方式
            String httpUrl = jobExec.getHttpUrl();
            logger.error("正在执行http定时任务,url:{}",httpUrl);
            HttpResponse<String> response = HttpTools.doGet(httpUrl, null, String.class);
            return response.getBody();
        });
        return null;
    }



    @Override
    public String submit(JobExec jobExec) {
        return null;
    }

}
