 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class Form3 
        {
        	;
        
        	Statement st;
        	ResultSet set;
        	abamin aba;   
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
          
        	
        	public Form3(Statement st,abamin aba)
        	{
        		 this.aba=aba;		 
        		 this.st=st;
        	
        		 
	           	 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,200);}}; 
	             p3.setLayout(new BorderLayout());
	           	 
	           	
	             list=new JList();
	             load();
	             p3.add(new JScrollPane(list),BorderLayout.CENTER);	             	        		       		       
        		 Object[] message = new Object[1];          
                 message[0]=p3;
                 
                

                 String[] options = {"Select","Cancel"};
               
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
						
				 if(result==0)
				 {
				 	aba.setPurpose(list.getSelectedValue()+"");
				 }
               
           }
                
        }