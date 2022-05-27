import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client{

	static Socket s;
	static BufferedReader bin;	
	static DataOutputStream dout;

	static Server[] serverRecs;
	static String jobs[];
	static String[] data;
	static String output = new String();

	static int nRecs;
	
	public static void main(String[]args){
	try{
		s= new Socket("127.0.0.1", 50000);
		dout=new DataOutputStream(s.getOutputStream());
  		bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		

		dout.write(("HELO\n").getBytes());
        String str=(String)bin.readLine();
		System.out.println(str);
        dout.write(("AUTH " + System.getProperty("user.name") + "\n").getBytes());        
		str=(String)bin.readLine();
		System.out.println(str);

		while(!output.equals("NONE")){
        	dout.write(("REDY\n").getBytes());
        	output=(String)bin.readLine();
			System.out.println(output);
			jobs = output.split(" ");


			
			if(serverRecs == null){
				dout.write(("GETS Avail " + attributeGetter(output) +"\n").getBytes());
				output=(String)bin.readLine();
				System.out.println(output);
				
				data = output.split(" ");
				nRecs = Integer.parseInt(data[1]);
			
				serverRecs = new Server[nRecs];
				dout.write(("OK\n").getBytes());

				LRR.algorithm();


				dout.write(("OK\n").getBytes());
				output = (String) bin.readLine();
				System.out.println(output);
			}
			if(jobs[0].equals("JOBN")){
				LRR.printer();
			}
}
    dout.write(("QUIT\n").getBytes());
	dout.flush();
	dout.close();
	s.close();
	System.out.println("client disconnected...");
	}catch(Exception e){System.out.println(e);} 
}

public static String attributeGetter(String s){
	String[] s2= s.split(" ");
	String s3 = s2[4] + " " + s2[5] + " " + s2[6];
	return s3;
	}
}
