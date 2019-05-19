import java.io.*;
import java.net.*;

public class Socket_demo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket(InetAddress.getLocalHost(),5432);
                    InputStream in = s.getInputStream();
                    DataInputStream dis = new DataInputStream(in);
                    String st = dis.readUTF();
                    System.out.println(st);
                    in.close();
                    s.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
