package red.lixiang.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import red.lixiang.job.admin.domain.JobDispatch;
import red.lixiang.job.admin.domain.JobExecQueue;
import red.lixiang.tools.spring.controller.BaseSimpleController;

@Import(BaseSimpleController.class)
@SpringBootApplication
public class JobAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAdminApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(){
		return args -> {
			// 启动整个过程
			// 先一个线程启动Dispatch调度
			Thread dispatchThread = new Thread(()->{
				JobDispatch dispatch= new JobDispatch();
				dispatch.init();
			});
			dispatchThread.start();
			// 再一个线程启动ExecQueue执行队列
			Thread execThread = new Thread(()->{
				JobExecQueue execQueue= new JobExecQueue();
				execQueue.run();
			});
			execThread.start();

		};
	}

}
