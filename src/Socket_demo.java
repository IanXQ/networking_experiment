import java.io.*;
import java.net.*;

public class Socket_demo extends Thread {
    @Override
    public void run(){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            Socket s = new Socket(InetAddress.getLocalHost(), 5432);
            InputStream in = s.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            String st = dis.readUTF();
            System.out.println(st);
            in.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketServer().start();
        new Socket_demo().start();

    }
}

