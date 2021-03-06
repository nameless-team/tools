package nameless.team.utils.log;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/3/18 09:41
 * @Description:
 */
public class MyLogger {

    //日志缓存
    private static List<LogEntry> logList = new LinkedList<>();

    /**
     *
     * @param fileName      fileName
     */
    public MyLogger(String fileName) {
        LogThread logThread = LogThread.newInstance(fileName,null);
        logThread.start();
    }

    /**
     *
     * @param fileName      fileName
     * @param format        format
     */
    public MyLogger(String fileName,String format) {
        LogThread logThread = LogThread.newInstance(fileName,null);
        logThread.start();
    }

    /**
     * INFO级别日志
     * @param log           日志内容
     */
    public void info(String log){
        write(Level.INFO,log);
    }

    /**
     * ERROR级别日志
     * @param log           日志内容
     */
    public void error(String log){
        write(Level.ERROR,log);
    }

    /**
     * DEBUG级别日志
     * @param log           日志内容
     */
    public void debug(String log){
        write(Level.DEBUG,log);
    }

    /**
     * 多线程写，同步锁，尽可能保证高效
     * @param level         日志级别
     * @param log           日志内容
     */
    private synchronized void write(Level level ,String log){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        int current = stacks.length-1;

        LogEntry logEntry = new LogEntry();
        logEntry.setLogEntry(
                Thread.currentThread().getId(),
                stacks[current].getClassName(),
                stacks[current].getMethodName(),
                stacks[current].getLineNumber(),
                level,
                log);

        logList.add(logEntry);
    }

    /**
     * LogThread    串行读取，不影响多线程写的问题
     * @return      获取日志对象LogEntry
     */
    public static LogEntry read(){

        if(logList.size()>0){
            return logList.remove(0);
        }else {
            return null;
        }
    }
}

