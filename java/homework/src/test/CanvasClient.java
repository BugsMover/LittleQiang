

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CanvasClient extends JFrame{
	
	Canvas canvas = new Canvas();
	
	JTextField inputField = new JTextField(40);
	JButton clearButton = new JButton("CLEAR");
	
	ClientThread clientThread = new ClientThread();

	public CanvasClient() {
		clientThread.start();
		
		this.setLayout(new BorderLayout());
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(inputField);
		southPanel.add(clearButton);
		
		canvas.setSize(480, 480);
		
		this.add(canvas, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas.repaint();
				
			}
		});
		
		canvas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				clientThread.drawStr(inputField.getText(), mouseEvent.getX(), mouseEvent.getY());
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public static void main(String[] args) {
		new CanvasClient();

	}

	class ClientThread extends Thread {
		
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		
		public ClientThread() {
			try {
				Socket socket = new Socket("10.20.2.128", 9888);
				dataInputStream =
						new DataInputStream(socket.getInputStream());
				dataOutputStream =
						new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "连接服务器失败！");
			}
		}
		
		public void drawStr(String str, int x, int y) {
			if (dataOutputStream == null)
				return;
			try {
				dataOutputStream.writeInt(x);
				dataOutputStream.writeInt(y);
				dataOutputStream.writeUTF(str);
				System.out.println("发送到服务器"+str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run() {
			if (dataInputStream == null)
				return;
			int x;
			int y;
			String str;
			while(true) {
				try {
					x = dataInputStream.readInt();
					y = dataInputStream.readInt();
					str = dataInputStream.readUTF();
					System.out.println("服务器接受"+str);
					canvas.getGraphics().drawString(str, x, y);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}


