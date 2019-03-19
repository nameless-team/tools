package nameless.team.utils.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/3/18 09:41
 * @Description:
 */
public class MyLogger {
    private String fileName;

    public static List<String> logList = new LinkedList<>();

    public MyLogger(String fileName) {
        this.fileName = fileName;
        LogThread logThread = new LogThread();
        logThread.start();
    }

    public void write(String log){
        logList.add(log);
    }

    public static String read(){

        if(logList.size()>0){
            return logList.remove(0);
        }else {
            return null;
        }
    }

    class LogThread extends Thread{
        private LogThread(){}

        public void run(){
            System.out.println(" LogThread start");
            while (true){
                String log = MyLogger.read();
                if(null!=log){
                    writeInNextLine(log);
                }
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ie){
                    System.out.println("InterruptedException");
                }
            }
        }
        public void writeInNextLine(String data){
            FileOutputStream fout = null;
            try{
                fout = new FileOutputStream(new File(fileName),true);
                fout.write(data.getBytes());
                fout.write("\r\n".getBytes());
                fout.close();
            }catch (IOException ie){
                System.out.println("IOException:"+ie);
            }finally {
                fout = null;
            }
        }
    }
}

