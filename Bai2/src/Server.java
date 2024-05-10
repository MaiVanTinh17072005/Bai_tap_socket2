import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public class Sever {
        public Sever() throws Exception {
            ServerSocket serverSocket = new ServerSocket(1707);
            Socket socket = serverSocket.accept();
            while (true) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader fromClient = null;
                        try {
                            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String data_of_Client = null;
                        try {
                            data_of_Client = fromClient.readLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (data_of_Client.equals("exit")) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println(data_of_Client);
                    }
                });
                thread.start();


                String mess_of_Serve = "";
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                toClient.writeBytes(mess_of_Serve + "\n");
            }
        }

        public static void main(String[] args) throws Exception {
            new Server();
        }
    }
}
