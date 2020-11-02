package red.lixiang.job.admin.model.dos;

import red.lixiang.tools.common.mybatis.model.SqlField;

import java.util.Date;

/**
 * @author lixiang
 * @date 2020/10/17
 **/
public class Job {

    @SqlField(snowId = true)
    private Long id;

    @SqlField
    private String code;

    @SqlField
    private Integer invokeType;

    @SqlField
    private String desc;

    @SqlField
    private String cron;

    @SqlField
    private Date nextExecTime;

    @SqlField
    private String parameter;

    @SqlField
    private String httpUrl;

    @SqlField
    private String dubboClass;

    @SqlField
    private String dubboMethod;

    @SqlField
    private Integer status;

    @SqlField(createTime = true)
    private Date createTime;

    @SqlField(updateTime = true)
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public Date getNextExecTime() {
        return nextExecTime;
    }

    public Job setNextExecTime(Date nextExecTime) {
        this.nextExecTime = nextExecTime;
        return this;
    }

    public Job setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Job setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getInvokeType() {
        return invokeType;
    }

    public Job setInvokeType(Integer invokeType) {
        this.invokeType = invokeType;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Job setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getCron() {
        return cron;
    }

    public Job setCron(String cron) {
        this.cron = cron;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Job setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Job setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public Job setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public Job setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
        return this;
    }

    public String getDubboClass() {
        return dubboClass;
    }

    public Job setDubboClass(String dubboClass) {
        this.dubboClass = dubboClass;
        return this;
    }

    public String getDubboMethod() {
        return dubboMethod;
    }

    public Job setDubboMethod(String dubboMethod) {
        this.dubboMethod = dubboMethod;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Job setStatus(Integer status) {
        this.status = status;
        return this;
    }
}
