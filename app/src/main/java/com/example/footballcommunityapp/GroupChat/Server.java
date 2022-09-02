package com.example.footballcommunityapp.GroupChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Reference Link: https://blog.csdn.net/weixin_38648693/article/details/78156070
// I cite some framework from this link, and design some functions according to the framework and my application demand.
public class Server implements Runnable {
    List<Socket> sockets = new ArrayList<>();
    private static final String ServerIp = "192.168.1.133";
    private static final int ServerPort = 6066;

    @Override
    public void run() {
        try {
            System.out.println("Start Server");
            ServerSocket server = new ServerSocket(ServerPort);
            while (true) {
                Socket client = server.accept();
                sockets.add(client);
                receiveThread re = new receiveThread(client);
                re.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // accept the thread of message
    public class receiveThread extends Thread {
        Socket socket;
        private BufferedReader br;
        private PrintWriter pw;
        public String messageContent;

        public receiveThread(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));//不转码在Android端会乱码
                messageContent = "Server >> " + "welcome" + socket.getInetAddress() + "into the group chat";
                sendMessage(InetAddress.getByName("1"));
                while ((messageContent = br.readLine()) != null) {
                    if (messageContent.equals("EndEndClosethesocket")) {
                        close(socket.getInetAddress());
                    } else {
                        messageContent = socket.getInetAddress() + ":##:" + messageContent;
                        sendMessage(socket.getInetAddress());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // send message according to InetAddress IP
        public void sendMessage(InetAddress ip) {
            try {
                System.out.println(messageContent);
                for (int i = 0; i < sockets.size(); i++) {
                    if (!ip.equals(sockets.get(i).getInetAddress())) {
                        pw = new PrintWriter(new OutputStreamWriter(sockets.get(i).getOutputStream(), "UTF-8"), true);
                        pw.println(messageContent);
                        pw.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // close the certain socket according to InetAddress IP
        public void close(InetAddress ip) {
            for (int i = 0; i < sockets.size(); i++) {
                if (sockets.get(i).getInetAddress() == ip) {
                    sockets.remove(i);
                    messageContent = "Player " + ip + ">> Leave the group chat";
                    try {
                        sendMessage(InetAddress.getByName("1"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
