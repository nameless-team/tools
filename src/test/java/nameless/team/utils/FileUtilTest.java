package nameless.team.utils;

import nameless.team.utils.encode.EncodeUtil;
import nameless.team.utils.file.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: cujamin
 * @Date: 2019/3/5 19:15
 * @Description:
 */
public class FileUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFile() throws Exception{
        String fileName = "cap.log";
        List<String> dataList = FileUtil.readFile(fileName);
        String data2 = "WinDump.exe:";
        for (String data:dataList){

            for (String charset: EncodeUtil.Charsets) {
                String newStr = EncodeUtil.changeCharset(data,charset);
                System.out.println(charset+":"+newStr);
            }



            for (String oldCharset : EncodeUtil.Charsets){
                for (String newCharset : EncodeUtil.Charsets) {
                    String newStr = EncodeUtil.changeCharset(data, oldCharset,newCharset);
                    System.out.println("oldCharset:"+oldCharset+";newCharset:"+newCharset + "-" + newStr);
                }
                System.out.println();
            }

            System.out.println(data);
        }
    }
}