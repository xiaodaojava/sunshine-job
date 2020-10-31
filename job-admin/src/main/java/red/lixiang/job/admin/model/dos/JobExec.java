package red.lixiang.job.admin.model.dos;

import red.lixiang.tools.common.mybatis.model.SqlField;

import java.util.Date;

/**
 * @author lixiang
 * @date 2020/10/17
 **/
public class JobExec {

    @SqlField(snowId = true)
    private Long id;

    @SqlField
    private Long jobId;

    @SqlField
    private String jobCode;

    @SqlField
    private String parameter;

    @SqlField
    private Integer invokeType;

    @SqlField
    private String httpUrl;

    /** dubbo注册地址 */
    @SqlField
    private String dubboRegistryUrl;

    @SqlField
    private String dubboClass;

    @SqlField
    private String dubboMethod;

    @SqlField
    private Date execTime;

    @SqlField(createTime = true)
    private Date createTime;

    public String getJobCode() {
        return jobCode;
    }

    public JobExec setJobCode(String jobCode) {
        this.jobCode = jobCode;
        return this;
    }

    public String getDubboRegistryUrl() {
        return dubboRegistryUrl;
    }

    public void setDubboRegistryUrl(String dubboRegistryUrl) {
        this.dubboRegistryUrl = dubboRegistryUrl;
    }

    public Long getId() {
        return id;
    }

    public JobExec setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getJobId() {
        return jobId;
    }

    public JobExec setJobId(Long jobId) {
        this.jobId = jobId;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public JobExec setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public Date getExecTime() {
        return execTime;
    }

    public JobExec setExecTime(Date execTime) {
        this.execTime = execTime;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public JobExec setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public JobExec setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
        return this;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public JobExec setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
        return this;
    }

    public String getDubboClass() {
        return dubboClass;
    }

    public JobExec setDubboClass(String dubboClass) {
        this.dubboClass = dubboClass;
        return this;
    }

    public String getDubboMethod() {
        return dubboMethod;
    }

    public JobExec setDubboMethod(String dubboMethod) {
        this.dubboMethod = dubboMethod;
        return this;
    }

    public static JobExec fromJob(Job job){
        JobExec jobExec = new JobExec();
        jobExec.setJobId(job.getId())
                .setParameter(job.getParameter())
                .setDubboClass(job.getDubboClass())
                .setDubboMethod(job.getDubboMethod())
                .setHttpUrl(job.getHttpUrl());
        return jobExec;
    }
}
