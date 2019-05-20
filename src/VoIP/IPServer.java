package VoIP;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.net.*;

public class IPServer {

    private byte[] bos = new byte[2048];
    private byte[] bis = new byte[2048];

    public IPServer(){
        startServer();
    }

    private void startServer(){
        try{
            DatagramPacket dp = new DatagramPacket(bis,bis.length);
            DatagramPacket dop;
            DatagramSocket ds = new DatagramSocket(2999);
            System.out.println("waiting for connection");
            TargetDataLine targetDataLine = AudioUtils.getTargetDataLine();
            SourceDataLine sourceDataLine = AudioUtils.getSourceDataLine();
            while(true){
                System.out.println("Server Send and Receive");
                int writeLen = targetDataLine.read(bos,0,bos.length);
                if(bos!=null){
                    dop = new DatagramPacket(bos,writeLen, InetAddress.getLocalHost(),3999);
                    ds.send(dop);
                }
                ds.receive(dp);
                bis = dp.getData();
                if(bis!=null){
                    sourceDataLine.write(bis,0,bis.length);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new IPServer();
    }
}



