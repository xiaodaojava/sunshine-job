package red.lixiang.job.admin.model.dos;

import red.lixiang.tools.common.mybatis.model.SqlField;

import java.util.Date;

/**
 * @author lixiang
 * @date 2020/10/17
 **/
public class JobLog {

    @SqlField(snowId = true)
    private Long id;

    @SqlField
    private String jobCode;

    @SqlField
    private Date execTime;

    @SqlField
    private String parameter;

    @SqlField
    private String result;

    @SqlField(createTime = true)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public JobLog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getJobCode() {
        return jobCode;
    }

    public JobLog setJobCode(String jobCode) {
        this.jobCode = jobCode;
        return this;
    }

    public Date getExecTime() {
        return execTime;
    }

    public JobLog setExecTime(Date execTime) {
        this.execTime = execTime;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public JobLog setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getResult() {
        return result;
    }

    public JobLog setResult(String result) {
        this.result = result;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JobLog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
