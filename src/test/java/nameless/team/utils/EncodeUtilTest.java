package nameless.team.utils;


import nameless.team.utils.encode.EncodeUtil;

/**
 * @Auther: cujamin
 * @Date: 2019/3/5 18:00
 * @Description:
 */
public class EncodeUtilTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void changeCharset() throws Exception{
        String str = "WinDump.exe: listening on ";
        for (String charset: EncodeUtil.Charsets) {
            String newStr = EncodeUtil.changeCharset(str,charset);
            System.out.println(charset+":"+newStr);
        }
    }

    @org.junit.Test
    public void changeCharset2() throws Exception{
        String str = "WinDump.exe: listening on ";

        for (String oldCharset : EncodeUtil.Charsets){
            for (String newCharset : EncodeUtil.Charsets) {
                String newStr = EncodeUtil.changeCharset(str, oldCharset,newCharset);
                System.out.println("oldCharset:"+oldCharset+";newCharset:"+newCharset + "-" + newStr);
            }
            System.out.println();
        }
    }
}