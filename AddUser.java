 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class AddUser implements ActionListener 
        {
        	JTextField txt;
            JPasswordField pass1;
            JPasswordField pass2;
        	Statement st;
        	ResultSet set;
        	abamin aba;
            JButton b1,b2;
            JList list;
            
            public void load()
            {
            	try
            	{   
            	    
            	    
            	    set=st.executeQuery("Select count(*) as cc from tblLogin");set.next();
          	        int cman=Integer.parseInt(set.getString("cc"));
            	    String arr[]=new String[cman];
            	    
            		int ctr=0;
            		set=st.executeQuery("Select username from tblLogin");
            		while(set.next())
            		{
            			arr[ctr]=set.getString("username");
            			ctr++;
            		}
            		list.setListData(arr);
            	}catch(Exception ee){ee.printStackTrace();}
            }
            public void actionPerformed(ActionEvent e)
            {
            	if(e.getSource()==b1)
            	{
            		 try
            		 {  
            		 	if(!pass1.getText().equals(pass2.getText())){JOptionPane.showMessageDialog(aba,"Password Did Not Match !");return;}
            		 	
            		 	st.execute("Insert Into tblLogin(Username,password) values('"+txt.getText()+"','"+pass1.getText()+"')");
            		 	load();
            		 	txt.setText("");
            		 	pass1.setText("");
            		 	pass2.setText("");
            		 	JOptionPane.showMessageDialog(aba,"New User Added !");
            		 }catch(Exception ee){}
            	}
            	if(e.getSource()==b2)
            	{
            	    try
					   {	
            		String pas=JOptionPane.showInputDialog(aba,"Enter Username Password");
            		set=st.executeQuery("Select Password from tblLogin where username='"+list.getSelectedValue()+"'");
            		
            		
            		set.next();String pas2=set.getString("Password");
            		if(!pas.equals(pas2)){JOptionPane.showMessageDialog(aba,"Invalid Password");return;}
            		
            		
					  
				
						st.execute("Delete from tblLogin where username='"+list.getSelectedValue()+"'");
						load();
						JOptionPane.showMessageDialog(aba,"        User Deleted !");
					   }catch(Exception ee){ee.printStackTrace();}
					
            	 }
            }
        	
        	public AddUser(Statement st,abamin aba)
        	{
        		 this.aba=aba;
        		 
        		 
        		 this.st=st;
        	
        	     pass1=new JPasswordField(25);
        	     pass2=new JPasswordField(25);
        	     JPanel p11=new JPanel();
        		 JPanel p22=new JPanel();
        		 JPanel p1=new JPanel();
        		 JPanel p2=new JPanel();
	           	 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,200);}}; 
	             p3.setLayout(new BorderLayout());
	           	 txt=new JTextField(25);
	           	 p11.add(new JLabel(" Enter UserName  "));
	           	 p11.add(txt);
	           	 
	           	 p22.add(new JLabel(" Enter Password  "));
	           	 p22.add(pass1);
	           	 
	           	 b1=new JButton("Add");
	           	 b2=new JButton("Delete");
	             
	             list=new JList();
	             load();
	             b1.addActionListener(this);
	             b2.addActionListener(this);
	             
	             
	             p1.add(new JLabel("Confirm Passwod"));p1.add(pass2);
	             p2.add(b1);p2.add(b2);
	             p3.add(new JScrollPane(list),BorderLayout.CENTER);
	             
	             	        		       		       
        		 Object[] message = new Object[5];          
                 message[0]=p11;
                 message[1]=p22;
                 message[2]=p1;
                 message[3]=p2;
                 message[4]=p3;                            
             
                

                 String[] options = {"Close"};
               
		    		int result = JOptionPane.showOptionDialog(
		    		aba,
		    		message,
		    		"",
		    		JOptionPane.DEFAULT_OPTION,
		    		JOptionPane.INFORMATION_MESSAGE,
		    		null,
		    		options,
		    		options[0]

						);
						
				 
               
           }
                
        }