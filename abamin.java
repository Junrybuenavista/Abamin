

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.*;
import java.util.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.table.TableColumn;
import java.text.DecimalFormat;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class abamin extends JFrame implements ActionListener ,TextListener,TableModelListener
{
	int iii=0;
	JTextField fname,lname,add,contact,purpose;
	JMenu menu;
	Connection conn;
	Statement st;
	String myuser="";
	JLabel user,sysDate,sysTime;
	JMenuItem i1,i2,i3;
	j table;
	TextField txtsearch;
	JButton but,butpur;
	GregorianCalendar gr = new GregorianCalendar();
	String date;
	String time;
	ResultSet set;
	String arr2[][]=null; 
	DecimalFormat format;
	DecimalFormat format2;
	JPopupMenu pop;
	boolean tEvent=false;
    javax.swing.Timer timer; 
	JLabel timep;
	Date dates;
	JComboBox combo;
	public String getMonth(int i)
    {
    	String ret="";
    	if(i==1)ret="January";
    	if(i==2)ret="February";
    	if(i==3)ret="March";
    	if(i==4)ret="April";
    	if(i==5)ret="May";
    	if(i==6)ret="June";
    	if(i==7)ret="July";
    	if(i==8)ret="August";
    	if(i==9)ret="September";
    	if(i==10)ret="October";
    	if(i==11)ret="November";
    	if(i==12)ret="December";
    	return ret;
    }
	
	
	
	
	public void tableChanged(TableModelEvent e)
	   	{   
	   		if(tEvent==false)return;
		            DefaultTableModel model = (DefaultTableModel)e.getSource();
       			    int row = e.getFirstRow();
                    int column = e.getColumn();
        			String columnName = model.getColumnName(column);       			
        		    String data2 =(String) model.getValueAt(row,column);
        		    String MyId=table.getValue(table.getTb().getSelectedRow(),0);
		            System.out.println(swp(columnName)+"  "+data2+"   "+columnName+"   "+MyId); 
		            
		            
		            try
		            {
		            	   
		            set=st.executeQuery("select "+swp(columnName)+" from tblRecord where ID="+MyId);
		            set.next();
		            if(data2.equals(set.getString(swp(columnName))))return;	
		            	
		            	
		            st.execute("UPDATE  tblRecord set "+swp(columnName)+"='"+data2+"' where ID="+MyId);
		            }catch(Exception ee){ee.printStackTrace();return;}
		            
		            JOptionPane.showMessageDialog(this,"      Saved !");
		}
		
		public String swp(String sss)
		{    
	    String ret="";
		if(sss.equals(""))ret="ID";
		if(sss.equals("First Name"))ret="Fname";
		if(sss.equals("Last Name"))ret="Lname";
		if(sss.equals("Address"))ret="Address";
		if(sss.equals("Contact No"))ret= "Contact_No";
		if(sss.equals("Purpose"))ret= "purpose";
		
		return ret;
		}	
		
	
	public abamin()
	{   
	
	   addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
      System.exit(0);}});
	    String combost[]={"All Entry","All Received","All Pending"};
	    combo=new JComboBox(combost);
	    
	    format2=new DecimalFormat("00");
        timer=new javax.swing.Timer(1000,this);
	    format=new DecimalFormat("00000");
	    
	    date=getMonth((gr.get(Calendar.MONTH)+1))+"/"+gr.get(Calendar.DATE)+"/"+gr.get(Calendar.YEAR);
	    
	    System.out.print(time);
	     setLayout(new BorderLayout());
        
        try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Forms.class;DriverID=22;READONLY=true;) ","",""); 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 st=conn.createStatement();
    				 
    				 
    				 
    				 
	      }catch(Exception e){e.printStackTrace();}
	     
	   
	     
        String arr[]={"ID No","Last Name","First Name","Address","Contact No","Purpose"};
                       
        table=new j();
        table.setData(arr2,arr);                  
	    TableColumn column=table.getTb().getColumnModel().getColumn(5);
        column.setPreferredWidth(300);
        column=table.getTb().getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
	    table.getTb().getModel().addTableModelListener(this);
	    
	    table.getTb().setGridColor(new Color(246,111,4));
	    table.getTb().setShowVerticalLines(false);
	    table.getTb().setSelectionForeground(new Color(246,111,4));
	    
	    table.getTb().addMouseListener(new MouseAdapter(){
     		public void mouseClicked(MouseEvent e)
     		           {
      							if (e.getClickCount() == 2)
      							{
      								  String arr[]=new String[10];
      								  try{
   	  	        							set=st.executeQuery("Select * from tblRecord where ID = "+table.getValue(table.getTb().getSelectedRow(),0));
   	  	                                    set.next();
	        
	        							
	        								arr[0]=format.format(Integer.parseInt(set.getString("ID")));
	     	 								arr[1]=set.getString("LName");
	     	 								arr[2]=set.getString("Fname");
	     	 								arr[3]=set.getString("Address");
	     	 						  		arr[4]=set.getString("Contact_no");
	     	 								arr[5]=set.getString("Purpose");
	     	 								arr[6]=set.getString("DateRecieved");
	     	 								arr[7]=set.getString("TimeRecieved");
	     	 								arr[8]=set.getString("recieved_by");
	     	 								arr[9]=set.getString("Remarks");
	     	 	
	     	 	
	   	
 		                                   }catch(Exception ee){}
      								
                                      new Form2(st,arr,abamin.this);
                    		


         						}
      					}
      					
      		});
	    
	  
	    setLayout(new BorderLayout());
	
	    GridLayout grid1=new GridLayout(6,1);
	    GridLayout grid2=new GridLayout(6,1);
	    
	    GridLayout grid3=new GridLayout(3,1);
	    GridLayout grid4=new GridLayout(3,1);
	    
	    BorderLayout grid33=new BorderLayout();
	    grid1.setVgap(5);grid2.setVgap(5);grid3.setVgap(5);
	    
	    butpur=new JButton("...");
	    butpur.addActionListener(this);
	    JPanel ppp=new JPanel();
	    ppp.setLayout(grid33);
	    
	    
	    
		JPanel pinfo=new JPanel(){public Dimension getPreferredSize(){return new Dimension(380,260);}};
		JPanel info1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(100,200);}};
		JPanel info2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,200);}};
		
		JPanel info11=new JPanel(){public Dimension getPreferredSize(){return new Dimension(100,200);}};
		JPanel info22=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,200);}};
		
		JPanel pinfo2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(380,150);}};
		info1.setLayout(grid1);
		info2.setLayout(grid2);
		info11.setLayout(grid3);
		info22.setLayout(grid4);
		pinfo.setLayout(new BorderLayout());
		pinfo2.setLayout(new BorderLayout());
		
		JPanel pn=new JPanel(){public Dimension getPreferredSize(){return new Dimension(1250,150);}};
		pn.setLayout(new BorderLayout());
	    JPanel pw=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,500);}};
		JPanel psearch=new JPanel(){public Dimension getPreferredSize(){return new Dimension(40,40);}};
		JPanel kk=new JPanel(){public Dimension getPreferredSize(){return new Dimension(40,40);}};
		JPanel kk2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(40,30);}};
		JPanel pc=new JPanel();
		pc.setLayout(new BorderLayout());
		
		txtsearch=new TextField(25);
		txtsearch.addTextListener(this);	
		fname=new JTextField();
		lname=new JTextField();
		add=new JTextField();
		contact=new JTextField();
		purpose=new JTextField(22);
		but=new JButton("Save");
		but.addActionListener(this);
		pn.add(new JLabel(new ImageIcon("Abamin.jpg")),BorderLayout.CENTER);
		pw.setBackground(new Color(246 ,111,4));
		pinfo.setBackground(new Color(246,111,4));
	    pinfo2.setBackground(new Color(0,111,4));
		info1.setBackground(new Color(246,111,4));
		info2.setBackground(new Color(246,111,4));
	    psearch.setBackground(new Color(246,111,4));
	    kk.setBackground(new Color(246,111,4));
	    kk2.setBackground(new Color(246,111,4));
		pw.add(pinfo);
		pw.add(pinfo2);
		menu=new JMenu("Menu");
		JMenuBar bar=new JMenuBar();
		
		
		i1=new JMenuItem("Add/Delete Purpose");
		i2=new JMenuItem("Add/Delete User");
		i3=new JMenuItem("Exit");
		
		i1.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);
		menu.add(i1);menu.add(i2);menu.add(i3);
		bar.add(menu);
		
		psearch.add(new JLabel("Search"));
		psearch.add(txtsearch);
		psearch.add(new JLabel("Search by"));
		psearch.add(combo);
		
		ppp.add(purpose,"West");ppp.add(butpur,"Center");
		
		kk2.add(but);
		setJMenuBar(bar);
		info1.add(new JLabel("  Last Name " ));info2.add(lname);
		info1.add(new JLabel("  First Name "));info2.add(fname);
		info1.add(new JLabel("  Address "));info2.add(add);
		info1.add(new JLabel("  Contact No "));info2.add(contact);
		info1.add(new JLabel("  Purpose "));info2.add(ppp);
		pinfo.add(info1,"West");
		pinfo.add(info2,"Center");
		pinfo.add(kk,"North");
		pinfo.add(kk2,"South");
		timep=new JLabel();
		user=new JLabel();
		user.setText("");
		sysDate=new JLabel();
		sysDate.setText(date);
		Font fonts=new Font("",Font.BOLD,17);
		
		JLabel l1=new JLabel("   USER :");
		JLabel l2=new JLabel("   DATE :");
		JLabel l3=new JLabel("   TIME :");
		
		
		user.setFont(fonts);
		sysDate.setFont(fonts);
		timep.setFont(fonts);
		l1.setFont(fonts);
		l2.setFont(fonts);
		l3.setFont(fonts);
		
	    info11.setBackground(new Color(255,128,25));
	    info22.setBackground(new Color(255,128,25));
		info11.add(l1);
		info11.add(l2);
		info11.add(l3);
	    
		
		
		pinfo2.add(info11,"West");
		pinfo2.add(info22,"Center");
		
		
		timer.start();
		
		pc.add(psearch,BorderLayout.NORTH);
		pc.add(JTable.createScrollPaneForTable(table.getTb()),BorderLayout.CENTER);
		add(pn,"North");
		add(pw,"West");
		add(pc,BorderLayout.CENTER);
		show();
		setSize(1280,768);
		login log=new login(abamin.this,st);
		myuser=log.getUser();
		user.setText(myuser);
		info22.add(user);
		info22.add(sysDate);
		info22.add(timep);
		load(); 
	}
	public void setPurpose(String set)
	{
	   purpose.setText(set);	
	}
	public void actionPerformed(ActionEvent e)
	{
		
		
		
	 if(e.getSource()==i3){System.exit(0);}	
	  if(e.getSource()==timer)
	  { 
	  
	    dates=new Date();
	    String timepm="";
	    if(dates.getHours()<12)timepm="AM";
	    else timepm="PM";
	   
	    time=format2.format(dates.getHours()%12)+":"+format2.format(dates.getMinutes())+":"+format2.format(dates.getSeconds())+" "+timepm;
	  	timep.setText(time);
	  	iii++;
	  }
	  if(e.getSource()==but)
	  {	
		try
		{
			st.execute("INSERT INTO tblRecord(LName,Fname,Address,Contact_no,Purpose,DateRecieved,TimeRecieved,Remarks,recieved_by) values('"+lname.getText()+"','"+fname.getText()+"','"+add.getText()+"','"+contact.getText()+"','"+purpose.getText()+"','"+date+"','"+time+"','Pending','"+myuser+"')");            
			
		}catch(Exception ee){ee.printStackTrace();}
		
		String arr[]=new String[5];
		arr[0]=lname.getText();
		arr[1]=fname.getText();
		arr[2]=add.getText();
		arr[3]=contact.getText();
		arr[4]=purpose.getText();
	    load();
		lname.setText("");
		fname.setText("");
		contact.setText("");
		add.setText("");
		purpose.setText("");
		JOptionPane.showMessageDialog(this,"        Saved !");
	    }
	    if(e.getSource()==i2)
	    {
	    	new AddUser(st,abamin.this);
	    }
	    if(e.getSource()==i1)
	    {
	    	
	    	new Form1(st,abamin.this);
	    }
	     if(e.getSource()==butpur)
	    {
	    	
	    	new Form3(st,abamin.this);
	    }
	}
	
	public void textValueChanged(TextEvent e)
	{  
		while(table.getTb().getRowCount()!=0)
                     		{
                                    tEvent=false;
                     	        	table.delete(0);
                     		}

   	  try{
   	  	if(combo.getSelectedIndex()==0)
   	  	set=st.executeQuery("Select * from tblRecord where Lname LIKE '"+txtsearch.getText()+"%'");
   	   if(combo.getSelectedIndex()==1)
   	   	set=st.executeQuery("Select * from tblRecord where Lname LIKE '"+txtsearch.getText()+"%' AND  Remarks='Received'");
   	   if(combo.getSelectedIndex()==2)
   	   	set=st.executeQuery("Select * from tblRecord where Lname LIKE '"+txtsearch.getText()+"%'  AND  Remarks='Pending'");
	    while(set.next())
	    	{
	        	String arr[]=new String[6];
	        	arr[0]=format.format(Integer.parseInt(set.getString("ID")));
	     	 	arr[1]=set.getString("LName");
	     	 	arr[2]=set.getString("Fname");
	     	 	arr[3]=set.getString("Address");
	     	 	arr[4]=set.getString("Contact_no");
	     	 	arr[5]=set.getString("Purpose");
	     	 	 tEvent=false;
	     	 	table.insert(arr);
	   		}
	   		  tEvent=true;
 		}catch(Exception ee){}
	}   
	
	public void load()
	{
	     try
	     {   
	         
	         while(table.getTb().getRowCount()!=0)
                     		{
                                    tEvent=false;
                     	        	table.delete(0);
                     		}
	         
	         
	     	 set=st.executeQuery("Select * from tblRecord");
	     	 int count =0;
	     	 while(set.next())
	     	 {
	     	    String arr[]=new String[6];
	        	arr[0]=format.format(Integer.parseInt(set.getString("ID")));
	     	 	arr[1]=set.getString("LName");
	     	 	arr[2]=set.getString("Fname");
	     	 	arr[3]=set.getString("Address");
	     	 	arr[4]=set.getString("Contact_no");
	     	 	arr[5]=set.getString("Purpose");
	     	 	tEvent=false;
	     	 	table.insert(arr);
	     	 }
	     	   tEvent=true;
	     }catch(Exception ee){ee.printStackTrace();}	
	}
	public static void main(String args[])
	{
		new abamin();
	}
	

}
