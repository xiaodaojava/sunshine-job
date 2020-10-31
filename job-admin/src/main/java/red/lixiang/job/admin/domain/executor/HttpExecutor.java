package red.lixiang.job.admin.domain.executor;

import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.tools.jdk.http.HttpResponse;
import red.lixiang.tools.jdk.http.HttpTools;

/**
 * @author lixiang
 * @date 2020/10/18
 **/
public class HttpExecutor implements Executor{



    @Override
    public String submitAsync(JobExec jobExec) {
        return null;
    }



    @Override
    public String submit(JobExec jobExec) {
        // 现在就先只支持http-get方式
        String httpUrl = jobExec.getHttpUrl();
        HttpResponse<String> response = HttpTools.doGet(httpUrl, null, String.class);
        return response.getBody();
    }

}
