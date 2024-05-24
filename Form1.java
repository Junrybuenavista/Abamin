 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class Form1 implements ActionListener 
        {
        	JTextField txt;
        
        	Statement st;
        	ResultSet set;
        	abamin aba;
            JButton b1,b2;
            JList list;
            
            public void load()
            {
            	try
            	{   
            	    
            	    
            	    set=st.executeQuery("Select count(*) as cc from purpose");set.next();
          	        int cman=Integer.parseInt(set.getString("cc"));
            	    String arr[]=new String[cman];
            	    
            		int ctr=0;
            		set=st.executeQuery("Select purpose_name from purpose");
            		while(set.next())
            		{
            			arr[ctr]=set.getString("purpose_name");
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
            		 	st.execute("Insert Into purpose(acc,purpose_name) values('key','"+txt.getText()+"')");
            		 	load();
            		 	txt.setText("");
            		 	JOptionPane.showMessageDialog(aba,"New Purpose Added !");
            		 }catch(Exception ee){}
            	}
            	if(e.getSource()==b2)
            	{
            		int ress=JOptionPane.showConfirmDialog(aba,"Are You Sure You Want To Delete This Record ?");
					if(ress==JOptionPane.YES_OPTION )
					{
					  try
					   {
				
						st.execute("Delete from purpose where purpose_name='"+list.getSelectedValue()+"'");
						load();
						JOptionPane.showMessageDialog(aba,"          Deleted !");
					   }catch(Exception ee){ee.printStackTrace();}
					}
            	}
            }
        	
        	public Form1(Statement st,abamin aba)
        	{
        		 this.aba=aba;
        		 
        		 
        		 this.st=st;
        	
        		 JPanel p1=new JPanel();
        		 JPanel p2=new JPanel();
	           	 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,200);}}; 
	             p3.setLayout(new BorderLayout());
	           	 
	           	 b1=new JButton("Add");
	           	 b2=new JButton("Delete");
	             txt=new JTextField(25);
	             list=new JList();
	             load();
	             b1.addActionListener(this);
	             b2.addActionListener(this);
	             
	             
	             p1.add(new JLabel("Add Here:"));p1.add(txt);
	             p2.add(b1);p2.add(b2);
	             p3.add(new JScrollPane(list),BorderLayout.CENTER);
	             
	             	        		       		       
        		 Object[] message = new Object[3];          
                 message[0]=p1;
                 message[1]=p2;
                 message[2]=p3;                            
             
                

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