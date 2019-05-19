import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URL_demo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://dean.xjtu.edu.cn");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        /*
        如果使用openConnection
        则需要使用getInPutStream方法获得输入流
         */
        String inputline;
        while((inputline=bufferedReader.readLine())!=null){
            System.out.println(inputline);
        }
        bufferedReader.close();

    }
}
