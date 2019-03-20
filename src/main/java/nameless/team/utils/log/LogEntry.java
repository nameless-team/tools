package nameless.team.utils.log;

/**
 * @Auther: cujamin
 * @Date: 2019/3/20 15:42
 * @Description:
 */
public class LogEntry{
    private long threadId;
    private String className;
    private String methodName;
    private int lineNumber;
    private Level level;
    private String log;

    /**
     *
     * @param threadId          线程号
     * @param className         类名
     * @param methodName        方法名
     * @param lineNumber        行号
     * @param level             日志级别
     * @param log               日志内容
     */
    public void setLogEntry(long threadId, String className, String methodName, int lineNumber, Level level, String log) {
        this.threadId = threadId;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.level = level;
        this.log = log;
    }

    /**
     * 使用StringBuilder，因为该方法只会被串行调用
     * @param format    日志格式
     * @return          最终日志    stringBuilder.toString()
     */
    public String getFullLog(String format){
        StringBuilder stringBuilder = new StringBuilder();
        if(null==format){
            stringBuilder.append("threadId=").append(threadId)
                    .append("-").append(className)
                    .append(":").append(methodName).append("(").append(lineNumber)
                    .append(")[").append(level).append("]")
                    .append(log);
            return stringBuilder.toString();
        }else {
            return null;
        }
    }
}
