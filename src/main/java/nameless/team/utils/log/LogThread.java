package nameless.team.utils.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/3/20 15:40
 * @Description:
 */
public class LogThread extends Thread{
    private static LogThread logThread = null;
    private static String staticFileName;
    private static String staticFormat = null;

    private LogThread(){}
    /**
     *
     * @param fileName
     * @param format
     */
    public static synchronized LogThread newInstance(String fileName,String format){
        if(null==logThread){
            staticFileName = fileName;
            staticFormat = format;
            logThread = new LogThread();
        }
        return logThread;
    }

    public void run(){
        System.out.println(" LogThread start");
        List<String> loglist = new LinkedList<>();
        while (true){
            LogEntry logEntry = MyLogger.read();
            while (null!=logEntry){
                // TODO: 2019/3/20 待优化
                loglist.add(logEntry.getFullLog(staticFormat));
                logEntry = MyLogger.read();
            }

            switch (loglist.size()){
                case 0:break;
                case 1:writeInNextLine(loglist.remove(0));break;
                default:writeLineList(loglist);break;
            }
            try{
                Thread.sleep(1);
            }catch (InterruptedException ie){
                System.out.println("InterruptedException");
            }
            loglist.clear();
        }
    }

    public void writeInNextLine(String data){
        try(FileOutputStream fout = new FileOutputStream(new File(staticFileName),true);){
            fout.write(data.getBytes());
            fout.write("\r\n".getBytes());
            fout.close();
        }catch (IOException ie){
            System.out.println("IOException:"+ie);
        }
    }

    public void writeLineList(List<String> datas){
        try(FileOutputStream fout = new FileOutputStream(new File(staticFileName),true);){
            for (String data:datas){
                fout.write(data.getBytes());
                fout.write("\r\n".getBytes());
            }
            fout.close();
        }catch (IOException ie){
            System.out.println("IOException:"+ie);
        }
    }
}
