package DS;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
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
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;



public class dictionpanel implements ActionListener {
	
	JTextField outgoingIP;
	JTextField outgoingPort;
	JButton button1;
	JLabel labe1;
	JLabel labe2;
	JFrame frame;
	public static void main(String[] args) {
		dictionpanel client=new dictionpanel();
		client.go();
		}
	public void go()
	{
	    frame =new JFrame("Wellcome use Xenon's dictionary");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel =new JPanel();
		outgoingIP=new JTextField(10);
		outgoingPort=new JTextField(10);
		button1= new JButton("connect");
		labe1=new JLabel("please input the IP address");
		labe2 = new JLabel("please input the Port number");
		button1.addActionListener(this);
		mainpanel.setLayout( new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		mainpanel.add(labe1);
		mainpanel.add(outgoingIP);
		mainpanel.add(labe2);
		mainpanel.add(outgoingPort);
		mainpanel.add(button1);
		frame.getContentPane().add(BorderLayout.CENTER, mainpanel);
		frame.setSize(200, 200);
		frame.setVisible(true);
		
	}
	/*JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	public static void main(String[] args) {
		chatclient client=new chatclient();
		client.go();
	}
	
	public void go()
	{
		JFrame frame =new JFrame("欢迎来到雷超设计的聊天程序");
		JPanel mainpanel =new JPanel();
		incoming =new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qs=new JScrollPane(incoming);
		qs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing=new JTextField(20);
		JButton sendbuttom=new JButton("send");
		sendbuttom.addActionListener(new sendbuttonl());
         mainpanel.add(qs);
         mainpanel.add(outgoing);
         mainpanel.add(sendbuttom);
         setupconncet();
         Thread readerthread=new Thread(new IncomingReader());
         readerthread.start();
         frame.getContentPane().add(BorderLayout.CENTER, mainpanel);
         frame.setSize(400, 500);
         frame.setVisible(true);
	}
	private void setupconncet ()
	{
		try {
			sock=new Socket("10.0.0.17",5000);
			InputStreamReader streamreader=new InputStreamReader(sock.getInputStream());
			reader=new BufferedReader(streamreader);
			writer=new PrintWriter(sock.getOutputStream());
			System.out.println("networking established");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public class sendbuttonl implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			// TODO Auto-generated method stub
			try {
				 writer.println(outgoing.getText());
				 writer.flush();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	}
public class IncomingReader implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message;
		try {
			while ((message=reader.readLine()) !=null)
			{
				System.out.println("here");
				System.out.println("client read"+message);
				incoming.append("雷超： "+message+"\n");
			}
		}catch(Exception ex) {ex.printStackTrace();}
	}
	
}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//try {
		//	Socket s= new Socket(outgoingIP.getText(), Integer.parseInt(outgoingPort.getText()));
			frame.setVisible(false);
			new mainpanel(outgoingIP.getText(),Integer.parseInt(outgoingPort.getText()));

			
		//} catch (IOException e1) {
			// TODO Auto-generated catch block
		//e1.printStackTrace();
		//}
		
		
	}
}

