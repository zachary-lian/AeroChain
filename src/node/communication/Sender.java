package node.communication;

import constant.Constant;

import java.io.IOException;
import java.net.*;

/**
 * 实现节点间发送消息的功能
 */
public class Sender{
    public static void sendData(String data , String ip){
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] sendBuf = data.getBytes();
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket sendPacket = new DatagramPacket(sendBuf ,sendBuf.length , addr , Constant.PORT);
        try {
            assert client != null;
            client.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
    }

    public static void broadcast(String data){
        for(String ip : Constant.address){
            sendData(data , ip);
        }
    }
}
