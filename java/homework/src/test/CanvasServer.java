

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class CanvasServer {

	Vector<ServerThread> serverThreads =
			new Vector<ServerThread>();
	
	public CanvasServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(9088);
			
			while(true) {
				Socket socket = serverSocket.accept();
				ServerThread serverThread = 
						new ServerThread(socket, serverThreads);
				serverThreads.add(serverThread);
				serverThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new CanvasServer();
	}

}

class ServerThread extends Thread {
	Socket socket;
	Vector<ServerThread> serverThreads;	
	DataInputStream dataInputStream = null;
	DataOutputStream dataOutputStream = null;
	
	public ServerThread(Socket socket, Vector<ServerThread> serverThreads) {
		super();
		this.socket = socket;
		this.serverThreads = serverThreads;
		try {
			dataInputStream =
					new DataInputStream(socket.getInputStream());
			dataOutputStream =
					new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendStr(String str, int x, int y) {
		if (dataOutputStream == null)
			return;
		try {
			dataOutputStream.writeInt(x);
			dataOutputStream.writeInt(y);
			dataOutputStream.writeUTF(str);
			System.out.println("���͵��ͻ���"+str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			if (dataInputStream == null)
				return;
			int x;
			int y;
			String str;
			try {
				x = dataInputStream.readInt();
				y = dataInputStream.readInt();
				str = dataInputStream.readUTF();
				System.out.println(x + "," + y + "," + str);
				if (x < 0 || x > 480 || y < 0 || y > 480 || str.length()>20)
					continue;
				for (ServerThread st : serverThreads) {
					st.sendStr(str, x, y);
				}
			} catch (IOException e) {
				serverThreads.remove(this);
				return;
			}
		}
	}
}