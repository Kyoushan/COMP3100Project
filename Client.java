import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client{

	static Socket s;
	static BufferedReader bin;	
	static DataOutputStream dout;
	static String[] jobs;
	static String output = new String();
	static int nRecs;
	static String attributes;
	static String[] data;


	
	public static void main(String[]args){
		try{
			s= new Socket("127.0.0.1", 50000);
			dout=new DataOutputStream(s.getOutputStream());
  			bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
			authenticate();

			while(!output.equals("NONE")){
        		dout.write(("REDY\n").getBytes());
        		output=(String)bin.readLine();
				System.out.println(output);

				SchFATC.algorithm();
			}

    		dout.write(("QUIT\n").getBytes());
			dout.flush();
			dout.close();
			s.close();
			System.out.println("client disconnected...");
		}catch(IOException e){System.out.println(e);} 
	}

	public static String attributeGetter(String s){
		String[] s2= s.split(" ");
		String s3 = s2[4] + " " + s2[5] + " " + s2[6];
		return s3;
	}


	public static void authenticate(){
		try {
			dout.write(("HELO\n").getBytes());
			String str=(String)bin.readLine();

			dout.write(("AUTH " + System.getProperty("user.name") + "\n").getBytes());        
			str=(String)bin.readLine();
			System.out.println(str);
		} catch (IOException e) {System.out.println(e);}
	}

	public static void getsInfo(String s){
        try{
            dout.write(("GETS " + s + " "+ attributes +"\n").getBytes());
			output=(String)bin.readLine();
			data = output.split(" ");
			nRecs = 0;
			if (data[1]!="."){
		        nRecs = Integer.parseInt(data[1]);
			}

			dout.write(("OK\n").getBytes());
			output = (String)bin.readLine();
			
        } catch (IOException e){System.out.println(e);}
    }
}
