package red.lixiang.job.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import red.lixiang.job.admin.model.dos.Job;
import red.lixiang.job.admin.model.qc.JobQC;
import red.lixiang.tools.common.mybatis.BaseMapper;
import red.lixiang.tools.common.mybatis.MybatisToolCache;

/**
 * @author lixiang
 * @date 2020/10/17
 **/
@Mapper
@Repository
public interface JobMapper extends BaseMapper<Job> {
    @Override
    default Class<?> getMapperClass(){
        MybatisToolCache.cacheDomain(Job.class, JobQC.class);
        return JobMapper.class;
    }
}
