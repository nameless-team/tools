package nameless.team.utils.file;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/3/5 19:12
 * @Description:
 */
public class FileUtil {

    private static BufferedReader reader = null;

    public static List<String> readFile(String fileName){
        File file = new File(fileName);
        System.out.println(" [ start to read ... ] ");
        List<String> list = new LinkedList<String>();
        try
        {
            reader =new BufferedReader(new FileReader(file));
            String info = reader.readLine();
            while(null!=info)
            {
                list.add(info);
                info = reader.readLine();
            }
        }catch (FileNotFoundException f)
        {
            System.out.println("没有找到该文件");
        }catch (IOException io)
        {
            System.out.println(" [ IOException ] ");
        }
        finally {
            System.out.println(" [ read completed ] ");
        }
        return list;
    }

    public static void writeInNextLine(String fileName,String data){
        System.out.println("写入"+fileName+":"+data);
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
