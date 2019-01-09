package DS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;

public class dictionarysever {
	static ArrayList<dictionary> dictionary=new ArrayList<dictionary>();
	public static int getword (ArrayList<dictionary> dictionary,String word)
	{
		for(int i=0;i<dictionary.size();++i)
		{
			if(dictionary.get(i).getword().equals(word))
			{
				return i;
			}
			
		}
		return -1;
	}
    public class Clienthandler implements  Runnable {
    	BufferedReader reader;
    	PrintWriter writer;
		Socket sock;
		public Clienthandler (Socket clientsock)
		{
			try {
				sock=clientsock;
				InputStreamReader isReader=new InputStreamReader(sock.getInputStream());
				reader=new BufferedReader(isReader);
				writer=new PrintWriter(sock.getOutputStream());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void run() {
		String message;
	        String name;
		String word;
		String comtext;
		String [] data;
		try {
			while((message=reader.readLine())!=null)
			{   
				System.out.println(message);
                    data=message.split("/");
                    System.out.println(data[0] + " " + data[1]);
				if(data[0].equals("delete"))
				{
					deletaction(data[1],writer);
				}
				if(data[0].equals("add"))
				{
					if(data.length==3) {
					addaction(data[1],data[2],writer);
					}
					else
					{						
							writer.println("sever:please input the correct format");
							writer.flush();
						}
												
					}
					
				if(data[0].equals("research"))
				{
					System.out.println("hit!");
					searchaction(data[1],writer);
				}
			}
		} catch (Exception e) {
		e.getStackTrace();
		
		}
		}
    }                       

	public static void main(String[] args) {
		
		String a=System.getProperty("user.dir");
		System.out.println(a);
		try {
			BufferedReader output=new BufferedReader(new FileReader("dictionary1.dat"));
			while(true)
			{
				String outputData=output.readLine();
				if (outputData!=null)
				{
					String []data=outputData.split("/");
					dictionary  dict= new dictionary(data[0], data[1]);
					dictionary.add(dict);
					
				}else
				{
					break;
				}
				
			}
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dictionarysever gui=new dictionarysever();
		gui.go();
	}
	public void go() {
	try {
		ServerSocket seversock=new ServerSocket(5000);
		System.out.println("waiting for connection...");
		while (true)
		{
			Socket cilentsock= seversock.accept();
			System.out.println("new client connected");
			PrintWriter writer=new PrintWriter(cilentsock.getOutputStream());
		    Thread t1=new Thread(new Clienthandler(cilentsock));
		    t1.start();
		    writer.println("sever:got a connect");
		    writer.flush();
			  }
			
	} catch (Exception e) {
		e.toString();
	}
	}
	public  void deletaction (String word, PrintWriter writer)
	{
		synchronized (dictionary) {
			
			try {
				if (getword(dictionary, word)==-1)
				{
				writer.println("sever:word does not exist");
				writer.flush();
				}
				
				else {
					dictionary.remove(getword(dictionary, word));
					writer.println("sever:"+word+" has been deleted");
					writer.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
		
			}
			}
		}
	public  void addaction (String word,String context,PrintWriter writer)
	{
		synchronized (dictionary) {
			
			try {
				if(getword(dictionary, word)!=-1)
				{
					writer.println("sever:"+word+" has exist, you are adding new meanings");
					String newmeaning=dictionary.get(getword(dictionary, word)).getcontext()+". new meaings:"+context;
					dictionary.get(getword(dictionary, word)).setcontext(newmeaning);
					writer.println("new meanings has benn added");
					writer.flush();
				}
				if (getword(dictionary, word)==-1)
				{
				dictionary dictionary1=new dictionary(word, context);
				dictionary.add(dictionary1);
				writer.println("sever:"+word+" has been added");
				writer.flush();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}

	
	public  void  searchaction (String word,PrintWriter writer)
	{
		synchronized (this) {
		
		System.out.println(word);
			try {
				if (getword(dictionary, word)==-1)
				{
				writer.println("sever:"+word+" does not exist");
				writer.flush();
				}
				else
				{
					writer.println("sever: "+word+": "+dictionary.get(getword(dictionary, word)).getcontext());
					writer.flush();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		
			}
		}
	}


