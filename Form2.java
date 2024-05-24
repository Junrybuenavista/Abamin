 
 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class Form2 
        {
        	JTextField txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
       
        
        	Statement st;
        	ResultSet set;
        	String all[];
        	abamin aba;
        
       
        	
        	public Form2(Statement st,String all[],abamin aba)
        	{
        		 this.aba=aba;
        	
        		 this.all=all;
        		 this.st=st;
        	
        		 JPanel p1=new JPanel();
	           	 JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,200);}};
	           	 JPanel main=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,300);}};
	           
	           	
	           	 
	           	 GridLayout grid1=new GridLayout(10,1);
	           	 GridLayout grid2=new GridLayout(10,1);
	           	 grid1.setVgap(5);grid2.setVgap(5);
        		 p1.setLayout(grid1);
        		 p2.setLayout(grid2);
        		 main.setLayout(new BorderLayout());
        		 main.add(p1,"West");
        		 main.add(p2,"Center");
        		       		       
        		 Object[] message = new Object[2];
                 txt1= new JTextField(15);
                 txt2= new JTextField(15);
                 txt3= new JTextField(15);
                 txt4= new JTextField(15); 
                 txt5= new JTextField(15);
                 txt6= new JTextField(15); 
                 txt7= new JTextField(15);
                 txt8= new JTextField(15); 
                 txt9= new JTextField(15);
                 txt10=new JTextField(15);           
                
                 txt1.setEditable(false);
                 txt2.setEditable(false);
                 txt3.setEditable(false);
                 txt4.setEditable(false);
                 txt5.setEditable(false);
                 txt6.setEditable(false);
                 txt7.setEditable(false);
                 txt8.setEditable(false);
                 txt9.setEditable(false);
                 txt10.setEditable(false); 
                 
                 
                 
                txt1.setForeground(Color.BLACK);
                
                p1.add(new JLabel("Id No"));p2.add(txt1);
                p1.add(new JLabel("Last Name"));p2.add(txt2);
                p1.add(new JLabel("First Name"));p2.add(txt3);
                p1.add(new JLabel("Address"));p2.add(txt4);
                p1.add(new JLabel("Contact No"));p2.add(txt5);
                p1.add(new JLabel("Purpose"));p2.add(txt6);
                p1.add(new JLabel("Date Recieve"));p2.add(txt7);
                p1.add(new JLabel("Time Recieve:"));p2.add(txt8);
                p1.add(new JLabel("Recieve By"));p2.add(txt9);
                p1.add(new JLabel("Remarks"));p2.add(txt10);
                
               
                
                message[0]=main;               
                String stat=null;
                load();
                if(txt10.getText().equals("Received"))
                {
                	stat="Mark As Pending";
                }
                else
                {
                	stat="Mark As Receive";
                }
                 
                 String[] options= {stat,"Delete","Cancel",};
                
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
				  	try{
				  		
				  		  if(txt10.getText().equals("Received"))
                			{
                			 st.execute("UPDATE  tblRecord set Remarks='Pending' where ID="+txt1.getText());
				  		     JOptionPane.showMessageDialog(aba,"Record Updated !");
               				 }
               				 else
               			     {
                				st.execute("UPDATE  tblRecord set Remarks='Received' where ID="+txt1.getText());
				  		        JOptionPane.showMessageDialog(aba,"Record Updated !");
               		     	 }
				  		
				  		
				  		
				  		  
				  	   }catch(Exception ee){}
				  }
				  		
				  if(result==1)
				  { 
				   try
				    {
				    int ress=JOptionPane.showConfirmDialog(aba,"Are You Sure You Want To Delete This Record ?");
					if(ress==JOptionPane.YES_OPTION )
					{	
				  	st.execute("Delete From tblRecord where ID="+txt1.getText());
				  	aba.load();
				  	JOptionPane.showMessageDialog(aba,"Record Deleted !");
				    }
				    }catch(Exception ee){}
				  }
               
           }
           public void load()
           {
           	   txt1.setText(all[0]);
           	   txt2.setText(all[1]);
           	   txt3.setText(all[2]);
           	   txt4.setText(all[3]);
           	   txt5.setText(all[4]);
           	   txt6.setText(all[5]);
           	   txt7.setText(all[6]);
           	   txt8.setText(all[7]);
           	   txt9.setText(all[8]);
           	   txt10.setText(all[9]);
           }
          
        
        }