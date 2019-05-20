import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_demo {
    public static void main(String[] args) {
        UDP_demo udp = new UDP_demo();
        udp.new UDP_receiver().start();
        udp.new UDP_Sender().start();
    }
    class UDP_Sender extends Thread{
        @Override
        public void run(){
            String hello = "Hello World!";
            byte[] h = hello.getBytes();
            try{
                Thread.sleep(1000);
                DatagramPacket dp = new DatagramPacket(
                    h,h.length,InetAddress.getLocalHost(),5432);
                DatagramSocket ds= new DatagramSocket();
                ds.send(dp);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class UDP_receiver extends Thread{
        @Override
        public void run(){
            try{
                byte[] re = new byte[20];
                DatagramPacket dp = new DatagramPacket(re,re.length);
                DatagramSocket ds = new DatagramSocket(5432);
                ds.receive(dp);
                System.out.println(new String(dp.getData()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
