package 作业;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Vector;

public class CanvasServer {

	Vector<ServerThread> serverThreads =
			new Vector<ServerThread>();
	
	public CanvasServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(7878);
			
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

	public void sendStr(String str) {
		if (dataOutputStream == null)
			return;
		try {
//			dataOutputStream.writeInt(x);
//			dataOutputStream.writeInt(y);
			dataOutputStream.writeUTF(str);
			System.out.println("发送到客户顿"+str);
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
//			String string;
//			Calendar now = Calendar.getInstance();
//			StringBuffer a = new StringBuffer();
			try {
//				x = dataInputStream.readInt();
//				y = dataInputStream.readInt();
				 str = dataInputStream.readUTF();
//				 string = now.get(Calendar.YEAR) +"."+
//						(now.get(Calendar.MONTH) + 1)+"."+
//						now.get(Calendar.DAY_OF_MONTH)+ " "+
//						now.get(Calendar.HOUR_OF_DAY)+":"+
//						now.get(Calendar.MINUTE)+":"+
//						now.get(Calendar.SECOND);
//				System.out.println(x + "," + y + "," + str);
//                           a.append(string+"\n"+str);
 //                          String b = a.toString();
				for (ServerThread st : serverThreads) {
//					st.sendStr();
					st.sendStr(str);
				}
			} catch (IOException e) {
				serverThreads.remove(this);
				return;
			}
		}
	}
}