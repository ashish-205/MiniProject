package ashish;
import java.sql.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class front extends JFrame implements ActionListener {
	 
	
	Connection con=null;
	front()
	{
		
		JFrame frame=new JFrame("Task Management System");

		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(224, 255, 255));
	
		 frame.setBounds(10,10,1000,600);
		 frame.setLayout(null);
//		frame.setResizable(false);
		JLabel l1=new JLabel("TASK MANAGEMENT SYSTEM");
		l1.setFont(new Font("Arial Black", Font.BOLD, 25)); 
		
		l1.setForeground(Color.BLUE);
       
        JLabel l2=new JLabel("Short Term :");
        JLabel l3=new JLabel("Long Term :");
        l2.setFont(new Font("Arial Black", Font.BOLD, 15));
        l3.setFont(new Font("Arial Black", Font.BOLD, 15));
        l1.setBounds(270,0,1000,70);
		l2.setBounds(50,60,200,100);
		l3.setBounds(50,120,200,100);
		
//		frame.getContentPane().add(l1,BorderLayout.CENTER);
//		frame.getContentPane().add(l2,BorderLayout.CENTER);
//		frame.getContentPane().add(l3,BorderLayout.CENTER);
		 
		JTextField tf1=new JTextField(20);
		
		tf1.setBounds(180,95,200,25);
//		frame.getContentPane().add(tf1,BorderLayout.CENTER);
		
		JTextArea tf2=new JTextArea();
		tf2.setBounds(180,180,200,200);
		
		
		JButton b1=new JButton("ADD>>");
		b1.setBounds(190,450,90,30);
	
		JButton b2=new JButton("New");
		b2.setBounds(290,450,90,30);
		JTable table = new JTable();
		
		
		
		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(600,120,500,350);

		frame.setVisible(true);
		
		frame.add(l1);
		frame.add(l2);
		frame.add(l3);
		frame.add(tf1);
		frame.add(tf2);
		frame.add(b1);
		frame.add(b2);
		frame.add(jp);
		
	
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String s=e.getActionCommand();
				if(s.equals("ADD>>"))
				{
					try {
						String s1=tf1.getText();
						String s2=tf2.getText();
					 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Task","root","root");
					PreparedStatement ps=(PreparedStatement)con.prepareStatement("insert into tasks(Important,Other) values(?,?)");
					ps.setString(1,s1);
					ps.setString(2, s2);
					ps.executeUpdate();
					
					tf1.setText("");
					tf2.setText("");
					JOptionPane.showMessageDialog(frame,"Task Added Successfully");
					
					
					PreparedStatement pst=con.prepareStatement("select * from tasks");
					ResultSet rs=pst.executeQuery();
					ResultSetMetaData rd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int a=rd.getColumnCount();
					
					String[] col=new String[a];
					
					for(int i=0;i<a;i++)
					{
						col[i]=rd.getColumnName(i+1);
					}
					
					model.setColumnIdentifiers(col);
					
					String imp,other;
					while(rs.next())
					{
						imp=rs.getString(1);
						other=rs.getString(2);
						String[] row= {imp,other};
						
						model.addRow(row);
					}
					
					}
					catch(Exception f)
					{
		
					}

				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) {
				String s4=e3.getActionCommand();
				if(s4.equals("New"))
				{
				
				}
				}
				
		});
		try {
			con.close();
		}
		catch(Exception a)
		{
			
		}
		
	}
	
	public static void main(String args[])throws SQLException
	{
		new front();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception g)
		{
			System.out.println(g);
		}
		
	}
	
}
