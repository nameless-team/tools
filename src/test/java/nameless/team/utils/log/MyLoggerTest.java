package nameless.team.utils.log;

import java.util.Scanner;

/**
 * @Auther: cujamin
 * @Date: 2019/3/19 09:58
 * @Description:
 */
public class MyLoggerTest {
    public static void main(String[] args) {

        MyLogger myLogger = new MyLogger("log.log");

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            myLogger.info(scanner.nextLine());
        }
    }
}