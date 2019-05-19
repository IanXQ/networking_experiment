import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread {
    @Override
    public void run(){
        ServerSocket s = null;
        String hello = "Hello World!";
        try{
            s = new ServerSocket(5432);
        }catch (IOException e){
            e.printStackTrace();
        }
        while(true){
            try {
                Socket cs = s.accept();
                OutputStream out = cs.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);
                dos.writeUTF(hello);
                out.close();
                cs.close();
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
