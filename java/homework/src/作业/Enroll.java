package ��ҵ;

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
	private JLabel yonghuming = new JLabel("�û���");
	private JLabel mima = new JLabel("����");
	private JLabel qverenmima = new JLabel("ȷ������");
	private JTextField �û��� = new JTextField(10);
	private JPasswordField ���� = new JPasswordField(10);
	private JPasswordField ȷ������ = new JPasswordField(10);
    private JButton qveren = new JButton("ȷ��");
    private JButton quxiao = new JButton("ȡ��");
    
    
    public Enroll(){
    	JPanel sp = new JPanel();
    	JPanel cp = new JPanel();
    	this.setLayout(new BorderLayout());
    	this.add(sp,BorderLayout.SOUTH);
    	this.add(cp,BorderLayout.CENTER);
    	cp.add(yonghuming,�û���);
    	cp.add(�û���);
    	cp.add(mima);
    	cp.add(����);
    	cp.add(qverenmima);
    	cp.add(ȷ������);
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
				if(����.getText().equals("")&&
						ȷ������.getText().equals("")&&
						�û���.getText().equals("")){
				JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ� ",  "",JOptionPane.ERROR_MESSAGE);
				}else{
					if(����.getText().equals(ȷ������.getText())){
								
						try {
							Connection connection;
							connection = DriverManager.getConnection(""
									+ "jdbc:mysql://localhost/user?user=root&password=1234");
							Statement statement = connection.createStatement();
							statement.executeUpdate("insert into user values("+"'"+�û���.getText()+"'"+","+
							"'"+����.getText()+"'"+");");
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
				JOptionPane.showMessageDialog(null, "ע��ɹ�,����û�����:" +�û���.getText() );
				
					}else{
						JOptionPane.showMessageDialog(null, "�����������������룡 ", "", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
    	

    	this.pack();
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);     	
    	this.setTitle("�����˺�ע�᣺");
    	this.setResizable(false);
    }
   

	public static void main(String args[]){
//	new Enroll();

	}
}
