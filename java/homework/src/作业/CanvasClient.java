package 作业;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mysql.fabric.xmlrpc.Client;
import com.mysql.fabric.xmlrpc.base.Data;
import java.sql.Date; 

public class CanvasClient extends JFrame{
	private  JTextArea shurukuang = new JTextArea(5,72);
    private JButton fasong = new JButton("发送");
    private JTextArea xianshikuang = new JTextArea(35, 79);
    private JScrollPane scrollPane = new JScrollPane(xianshikuang);

    ClientThread clientThread = new ClientThread();

       
	public CanvasClient (){
		clientThread.start();
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		xianshikuang.setFont(new Font("宋体", Font.BOLD, 14));
		xianshikuang.setWrapStyleWord(true);
		xianshikuang.setLineWrap(true);
		shurukuang.setFont(new Font("宋体", Font.BOLD, 14));
		shurukuang.setWrapStyleWord(true);
		shurukuang.setLineWrap(true);
		this.setLayout(new BorderLayout());
		JPanel sp = new JPanel();
		JPanel cp = new JPanel();
		sp.setLayout(new FlowLayout());
		cp.setLayout(new FlowLayout());
		sp.add(shurukuang);
		sp.add(fasong);
		cp.add(scrollPane);
			
		cp.setBorder(new TitledBorder("信息显示区"));
		sp.setBorder(new TitledBorder(OICQlogin.getM()));
		this.add(cp,BorderLayout.CENTER);
		this.add(sp, BorderLayout.SOUTH);

		fasong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(shurukuang.getText().equals("")){
					JOptionPane.showMessageDialog(null, "输入信息不能为空！", "", JOptionPane.WARNING_MESSAGE);
				}else{
					String d = shurukuang.getText();
					clientThread.drawStr(d);
//					xianshikuang.setText(d);
					shurukuang.setText(null);
				}
				
			}
		});
		
		this.setTitle("多人聊天室");
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(675, 750);		                            
		this.setResizable(false);
		
	}
	

	public static void main(String args[]){
		
//		new CanvasClient();
	}


class ClientThread extends Thread {
		
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		
		public ClientThread() {
			try {
				Socket socket = new Socket("localhost",7878);
				dataInputStream =
						new DataInputStream(socket.getInputStream());
				dataOutputStream =
						new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "连接服务器失败！","",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		
		public void drawStr(String str) {
			if (dataOutputStream == null)
				return;
			String s;
			Calendar now = Calendar.getInstance();
            String	string = now.get(Calendar.YEAR) +"."+
					(now.get(Calendar.MONTH) + 1)+"."+
					now.get(Calendar.DAY_OF_MONTH)+ " "+
					now.get(Calendar.HOUR_OF_DAY)+":"+
					now.get(Calendar.MINUTE)+":"+
					now.get(Calendar.SECOND);  
            
//            s = Hello.getM()+"已上线！";
            s =	string+"\n"+OICQlogin.getM()+":"+str + "\n"+"\n";
            
			try {
//				dataOutputStream.writeInt(x);
//				dataOutputStream.writeInt(y);
				dataOutputStream.writeUTF(s);
//				System.out.println("发送到服务器"+str);
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
			StringBuffer a = new StringBuffer();
//			StringBuffer b = new StringBuffer();
			
			while(true) {
				try {
//					x = dataInputStream.readInt();
//					y = dataInputStream.readInt();
					str = dataInputStream.readUTF();
//					b.append(string);
//					Hello.getM()

					a.append(str);
					xianshikuang.setEditable(true);
//					xianshikuang.setText(b.toString());
					xianshikuang.setText(a.toString());
					xianshikuang.setEditable(false);
//					xianshikuang.setLineWrap(true);
//					xianshikuang.append("\n");
//					System.out.println("服务器接受"+str);		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}