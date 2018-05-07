package 作业;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Enroll extends JFrame{
	private JLabel yonghuming = new JLabel("用户名");
	private JLabel mima = new JLabel("密码");
	private JLabel qverenmima = new JLabel("确认密码");
	private JTextField 用户名 = new JTextField(10);
	private JPasswordField 密码 = new JPasswordField(10);
	private JPasswordField 确认密码 = new JPasswordField(10);
    private JButton qveren = new JButton("确认");
    private JButton quxiao = new JButton("取消");
    
    
    public Enroll(){
    	JPanel sp = new JPanel();
    	JPanel cp = new JPanel();
    	this.setLayout(new BorderLayout());
    	this.add(sp,BorderLayout.SOUTH);
    	this.add(cp,BorderLayout.CENTER);
    	cp.add(yonghuming,用户名);
    	cp.add(用户名);
    	cp.add(mima);
    	cp.add(密码);
    	cp.add(qverenmima);
    	cp.add(确认密码);
    	sp.add(qveren);
    	sp.add(quxiao);
    	
    	quxiao.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OICQlogin();
				setVisible(false);
			}
		});
    	
    	qveren.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(密码.getText().equals("")&&
						确认密码.getText().equals("")&&
						用户名.getText().equals("")){
				JOptionPane.showMessageDialog(null, "用户名或密码不能为空！ ",  "",JOptionPane.ERROR_MESSAGE);
				}else{
					if(密码.getText().equals(确认密码.getText())){
								
						try {
							Connection connection;
							connection = DriverManager.getConnection(""
									+ "jdbc:mysql://localhost/user?user=root&password=1234");
							Statement statement = connection.createStatement();
							statement.executeUpdate("insert into user values("+"'"+用户名.getText()+"'"+","+
							"'"+密码.getText()+"'"+");");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
				JOptionPane.showMessageDialog(null, "注册成功,你的用户名是:" +用户名.getText() );
				
					}else{
						JOptionPane.showMessageDialog(null, "密码有误，请重新输入！ ", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
    	

    	this.pack();
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);     	
    	this.setTitle("个人账号注册：");
    	this.setResizable(false);
    }
   

	public static void main(String args[]){
//	new Enroll();

	}
}
