package red.lixiang.job.admin.domain.executor;

/**
 * @author lixiang
 * @date 2020/10/31
 **/
public class ExecutorFactory {
    public static final Integer  INVOKE_HTTP=1;
    public static final Integer  INVOKE_DUBBO=2;

    private static Executor httpExecutor = new HttpExecutor();

    private static Executor dubboExecutor = new DubboExecutor();

    public static Executor getExecutor(Integer invokeType){
        if(invokeType.equals(INVOKE_HTTP)){
            return httpExecutor;
        }
        if(invokeType.equals(INVOKE_DUBBO)){
            return dubboExecutor;
        }
        return null;
    }
}
