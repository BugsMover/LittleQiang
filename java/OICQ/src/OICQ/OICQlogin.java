package OICQ;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OICQlogin extends JFrame{
	private  JLabel yonghuiming = new JLabel("用户名");
	private JLabel mima = new JLabel("密码");
	private static  JTextField yong = new JTextField(10);
	private static JPasswordField mi = new JPasswordField(10);
	private JButton quxiao = new JButton("取消");
	private JButton denglu = new JButton("登录");
	private JButton zhuce = new JButton("注册");
    private static  String m = null;
    String x;
	 public OICQlogin(){
		JPanel sp = new JPanel();
		sp.add(denglu);
		sp.add(zhuce);
		sp.add(quxiao);
		JPanel cp = new JPanel();
		cp.add(yonghuiming);
		cp.add(yong);
		cp.add(mima);
		cp.add(mi);
		this.setLayout(new BorderLayout());
		this.add(cp,BorderLayout.CENTER);
		this.add(sp, BorderLayout.SOUTH);
				
		this.setTitle("登录界面");
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setSize(180,125);
		this.setResizable(false);
		
		zhuce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Enroll();
				setVisible(false);
			}
		});
		
        quxiao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
        
        
        denglu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(yong.getText().equals("")&&
						mi.getText().equals("")
						){
					JOptionPane.showMessageDialog(null, "用户名和密码不能为空！", "", JOptionPane.ERROR_MESSAGE);
				}else{
				try {
					Connection connection = DriverManager.getConnection(""
							+ "jdbc:mysql://localhost/user?user=root&password=1234");
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from user where yonghuming = "+"'"+yong.getText()+"'"+";");
			//		ArrayList<User> user = new ArrayList<User>();
					while(resultSet.next()){
					x = resultSet.getString(2);
					}			
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(mi.getText().equals(x)){
					      m =  yong.getText();
				            new CanvasClient(); 
				            
				          setVisible(false);
				          
//				          CanvasClient hi = new CanvasClient();
				
				          }else{
				        	  JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入！", "", JOptionPane.ERROR_MESSAGE);
				          }				
						}						
			}
		});  
	}
	
	 


	public static void main(String args[]){
	new OICQlogin();
	
	      

		}




	public static String getM() {
		return m;
	}




	public void setM(String m) {
		this.m = m;
	}
	
	}

