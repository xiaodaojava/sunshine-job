package red.lixiang.job.admin.utils;

import org.springframework.scheduling.support.CronSequenceGenerator;
import red.lixiang.job.admin.model.dos.JobExec;
import red.lixiang.tools.jdk.StringTools;

import java.util.*;

/**
 * @author lixiang
 * @date 2020/10/17
 **/
public class CronTools {

    /**
     * 从cron表达式获取下次运行时间
     *
     * @param cron
     * @return
     */
    public static List<Date> recentRunTimeFromCron(String cron) {
        if (StringTools.isBlank(cron)) {
            return null;
        }
        if (!CronSequenceGenerator.isValidExpression(cron)) {
            return null;
        }
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
        List<Date> cronTimeList = new ArrayList<>();
        Date nextTimePoint = new Date();
        for (int i = 0; i < 5; i++) {
            nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
            cronTimeList.add(nextTimePoint);
        }
        return cronTimeList;
    }

    public static Date nextRunTimeFromCron(String cron) {
        if (StringTools.isBlank(cron)) {
            return null;
        }
        if (!CronSequenceGenerator.isValidExpression(cron)) {
            return null;
        }
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
        Date nextTimePoint = new Date();
        nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
        return nextTimePoint;
    }


    public static void main(String[] args) {

        List<JobExec> jobExecs = new LinkedList<>();
        JobExec exec1 = new JobExec();
        exec1.setJobId(1L);
        JobExec exec2 = new JobExec();
        exec2.setJobId(2L);
        jobExecs.add(exec1);
        jobExecs.add(exec2);
        System.out.println(jobExecs);

        JobExec exec3 = new JobExec();
        exec3.setJobId(3L);
        jobExecs.add(2,exec3);
        System.out.println(jobExecs);
    }
}
