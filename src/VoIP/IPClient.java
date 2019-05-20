package VoIP;

import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class IPClient {

    private byte[] bos = new byte[2024];
    private static byte[] bis = new byte[2024];

    IPClient() {
        startClient();
    }

    private void startClient() {
        try {
            DatagramPacket dp;
            DatagramSocket ds = new DatagramSocket(3999);
            DatagramPacket dip = new DatagramPacket(bis, bis.length);
            System.out.println("waiting for connection");
            TargetDataLine targetDataLine = AudioUtils.getTargetDataLine();
            SourceDataLine sourceDataLine = AudioUtils.getSourceDataLine();
            while (true) {
                System.out.println("Client Send and Receive");
                int writeLen = targetDataLine.read(bos, 0, bos.length);
                if (bos != null) {
                    dp = new DatagramPacket(bos, writeLen, InetAddress.getLocalHost(), 2999);
                    ds.send(dp);
                }
                ds.receive(dip);
                bis = dip.getData();
                if (bis != null) {
                    sourceDataLine.write(bis, 0, bis.length);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new IPClient();
    }

}
