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

    public LogEntry() {}

    /**
     *
     * @param threadId
     * @param className
     * @param methodName
     * @param lineNumber
     * @param level
     * @param log
     */
    public void setLogEntry(long threadId, String className, String methodName, int lineNumber, Level level, String log) {
        this.threadId = threadId;
        this.className = className;
        this.methodName = methodName;
        this.lineNumber = lineNumber;
        this.level = level;
        this.log = log;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getFullLog(String format){
        StringBuffer stringBuffer = new StringBuffer();
        if(null==format){
            stringBuffer.append("threadId=").append(threadId)
                    .append("-").append(className)
                    .append(":").append(methodName).append("(").append(lineNumber)
                    .append(")[").append(level).append("]")
                    .append(log);
            return stringBuffer.toString();
        }else {
            return null;
        }
    }
}
