import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Thread.*;
public class connectui extends JFrame
{
	JLabel status = new JLabel("Avviato");
	String stmnt="";
	boolean runn = false;
	JButton con = new JButton("Connetti");
	JButton dis = new JButton("Disconnetti");
	JPanel pan = new JPanel();
	BorderLayout br = new BorderLayout();
	FlowLayout fl = new FlowLayout();
	String[] options = {"internet.wind","internet.tim"};
	JList apn = new JList(options);
	SimpleRunner r = new SimpleRunner();
	Thread statuses = new Thread(r);
	
	public connectui()
	{
		super("3Gconnect");
		pan.setLayout(fl);
		pan.add(con);
		pan.add(dis);
		add(apn,br.CENTER);
		add(pan,br.NORTH);
		add(status,br.SOUTH);
		actions();
		statuses.start();
		pack();
		show();
	} 
	
	void actions()
	{
		con.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				connect();
			}
		});
		dis.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				disconnect();
			}
		});
	}
	
	void connect()
	{
		Process con;
		
		stmnt ="sakis3gz connect APN="+apn.getSelectedValue();
		System.out.print(stmnt);
		try{con = Runtime.getRuntime().exec(stmnt); 
		status.setText("Connecting");
		runn=true;
		}
		catch(Exception err){status.setText("Errore--Disconnesso");}
		runn=false;
		
		
	}	
		
	void disconnect()
	{
		Process dis;
		stmnt="sakis3gz disconnect";
		try{dis = Runtime.getRuntime().exec(stmnt); status.setText("Disconnetting"); runn=true;}
		catch(Exception err){status.setText("Errore--Connesso");}
		runn=false;
	}
	
	
	
public static void main(String[] arg)
{
	new connectui();
	
}
class SimpleRunner implements Runnable
{
	public void run()
	{
		
		Process stacontrol;
		for (;;){
		
		
		try{stacontrol = Runtime.getRuntime().exec("sakis3gz status");
				BufferedReader in = new BufferedReader(new InputStreamReader(stacontrol.getInputStream()));
				
					
						String returned = in.readLine();
						
						if (runn)
						{} else {status.setText(returned);}
					
			}
		catch(Exception err) {}
		
	}}
}
}
		
		
	
