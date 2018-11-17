package DS;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class mainpanel {
JPanel panel;
JButton button1;
JButton button2;
JButton button3;
JButton button4;
JLabel label1;
JLabel label2;
JLabel label3;
JLabel label4;
JTextArea text;
JFrame frame;
JTextField text1;
JTextField text2;
JTextField text3;
private String IP;
private int port;
BufferedReader reader;
PrintWriter writer;
Socket s;
Thread testThread;
public mainpanel (String IP,int port)
{
	this.IP=IP;
	this.port=port;
	frame = new JFrame("Dictionary");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel=new JPanel();
	text=new JTextArea(15,50);
	text.setLineWrap(true);
	text.setWrapStyleWord(true);
	text.setEditable(false);
	JScrollPane qs=new JScrollPane(text);
	qs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	qs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	label1=new JLabel("what words you want to search?");
	label2=new JLabel("what words you want to add? please input by words" +"/"+ "meanings");
	label3=new JLabel("what words you want to delete?");
	label4=new JLabel("result");
	button1=new JButton("search");
	button1.addActionListener(new snedbutton1());
	button2=new JButton("add");
    button2.addActionListener(new snedbutton2());
	button3=new JButton("delete");
	button3.addActionListener(new snedbutton3());
	button4 =new JButton("return");
	button4.addActionListener(new sendbutton4());
    text1=new JTextField(20);
    text2=new JTextField(20);
    text3=new JTextField(20);
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	panel.add(label4);
	panel.add(text);
	panel.add(label1);
	panel.add(text1);
	panel.add(button1);
	panel.add(label2);
	panel.add(text2);
	panel.add(button2);
	panel.add(label3);
	panel.add(text3);
	panel.add(button3);
	panel.add(button4);
	frame.getContentPane().add(panel, BorderLayout.CENTER);
	frame.setSize(400, 400);
	frame.setVisible(true);
	try {
			 s= new Socket(IP, port);	
			InputStreamReader streamReader=new InputStreamReader(s.getInputStream());
			reader=new BufferedReader(streamReader);
			writer=new PrintWriter(s.getOutputStream());
			text.append("networking established\n");
	} catch (IOException e) {
		text.append("connect error.please connect again!");
	}
	Thread readerThread= new Thread(new Incoming());
	readerThread.start();
	 testThread =new Thread(new output(s));
	 testThread.start();
}

		public class snedbutton1 implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					writer.println("research/"+text1.getText());
					writer.flush();
				} catch (Exception e2) {
					text.append("connect error.please connect again!");
				}
				text1.setText("");
				text1.requestFocus();
			}
			
		}

		public class snedbutton2 implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					writer.println("add/"+text2.getText());
					writer.flush();
				} catch (Exception e2) {
					text.append("connect error.please connect again!");
				}
				text2.setText("");
				text2.requestFocus();
			}
			
		}

		public class snedbutton3 implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					writer.println("delete/"+text3.getText());
					writer.flush();
				} catch (Exception e2) {
					text.append("connect error.please connect again!");
				}
				text3.setText("");
				text3.requestFocus();
			}
			
		}
		public class sendbutton4 implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(false);
					new dictionpanel().go();
				} catch (Exception e2) {
					text.append("connect error.please connect again!");
				}
				
			}
			
		}
		
public class Incoming implements Runnable
{

	@Override
	public void run() {
		String message;
		try {
			while ((message=reader.readLine())!=null)
					{
				text.append(message+"\n");
					}
		} catch (Exception e) {
			text.append("connect error.please connect again!");
		}
		
	}
	
}
public class output implements Runnable
{
   Socket sock;
   public output(Socket s)
   {
	   sock=s;
   }
   public boolean isConnected(){
       try{
           s.sendUrgentData(0xFF);
           return true;
       }catch(Exception e){
           return false;
       }
	}
	@Override
	public void run() {
		while(isConnected())
		{
			try {
				testThread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	      text.append("connect error.please connect again!");
}
}
}
