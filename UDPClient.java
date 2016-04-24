import java.io.IOException;
import java.net.Socket;
 
public class UDPClient {
    private FileTrans transmitter = null;
    Socket clientSocket = null;
    private boolean connectedStatus = false;
    private String ipAddress;
    String srcPath = null;
    String dstPath = "";
    public UDPClient() {
 
    }
 
    public void setIpAddress(String ip) {
        this.ipAddress = ip;
    }
 
    public void setSrcPath(String path) {
        this.srcPath = path;
    }
 
    public void setDstPath(String path) {
        this.dstPath = path;
    }
 
    private void createConnection() {
        Runnable connectRunnable = new Runnable() {
            public void run() {
                while (!connectedStatus) {
                    try {
                        clientSocket = new Socket(ipAddress, 3339);
                        connectedStatus = true;
                        transmitter = new FileTrans(clientSocket, srcPath, dstPath);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
 
            }
        };
        Thread connectionThread = new Thread(connectRunnable);
        connectionThread.start();
    }
 
    public static void main(String[] args) {
        UDPClient main = new UDPClient();
        main.setIpAddress("localHost");
        main.setSrcPath("C:/Users/dragon/Desktop/UDPSocket/original");
        main.setDstPath("C:/Users/dragon/Desktop/UDPSocket/copy");
        main.createConnection();
 
    }
}