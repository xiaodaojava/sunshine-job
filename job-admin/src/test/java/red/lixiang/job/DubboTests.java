package red.lixiang.job;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import red.lixiang.job.admin.dao.JobExecMapper;
import red.lixiang.job.admin.dao.JobMapper;
import red.lixiang.job.admin.model.dos.Job;
import red.lixiang.tools.common.dubbo.DubboInvokeConfig;
import red.lixiang.tools.common.dubbo.ReferenceFactory;

import java.util.List;
import java.util.Map;

@SpringBootTest
class DubboTests {



	@Test
	void inokeDubbo() {
		DubboInvokeConfig param = new DubboInvokeConfig();
		param.setRegistryAddress("nacos://10.146.143.245:8848");
		param.setInterfaceName("com.platform.forest.client.interfaces.AccountService");
		param.setMethodName("getAccountListByPassportId");
		param.setArgTypes(new String[]{"java.lang.Long"});
		param.setArgObjects(new Object[]{1223L});
		ReferenceConfig<GenericService> referenceConfig = ReferenceFactory.buildReferenceConfig(param);
		GenericService genericService = referenceConfig.get();
		Map<String, String> attachments = param.getAttachments();
		if (attachments != null) {
			RpcContext.getContext().setAttachments(attachments);
		}
		Object result = genericService.$invoke(param.getMethodName(), param.getArgTypes(), param.getArgObjects());
		System.out.println(result);

	}

}
