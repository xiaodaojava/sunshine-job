package red.lixiang.job;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import red.lixiang.job.admin.dao.JobExecMapper;
import red.lixiang.job.admin.dao.JobMapper;
import red.lixiang.job.admin.model.dos.Job;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private JobMapper jobMapper;

	@Autowired
	private JobExecMapper jobExecMapper;

	@Test
	void contextLoads() {
		List<Job> all = jobMapper.findAll();
		System.out.println(all);

	}

}
