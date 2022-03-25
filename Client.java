import java.io.*;  
import java.net.*;  
public class Client{

	public static String attributeGetter(String s){
		String s2= s.substring(13,24);
		return s2;
	}
	
public static void main(String[]args){
	try{
	Socket s= new Socket("127.0.0.1", 50000);
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());
	dout.write(("HELO\n").getBytes());
	dout.flush();
  	BufferedReader bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str=(String)bin.readLine();
	System.out.println(str);

	DataOutputStream dout2=new DataOutputStream(s.getOutputStream());
        dout2.write(("AUTHAngus\n").getBytes());
	dout2.flush();

	BufferedReader bin2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str2=(String)bin2.readLine();
	System.out.println(str2);


	DataOutputStream dout3=new DataOutputStream(s.getOutputStream());
        dout3.write(("REDY\n").getBytes());
	dout3.flush();

	BufferedReader bin3 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str3=(String)bin3.readLine();
	System.out.println(str3);

        DataOutputStream dout4=new DataOutputStream(s.getOutputStream());
        dout4.write(("GETS Capable"+ attributeGetter(str3) +"\n").getBytes());
	dout4.flush();

	BufferedReader bin4 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str4=(String)bin4.readLine();
        System.out.println(str4);

        DataOutputStream dout5=new DataOutputStream(s.getOutputStream());
        dout5.write(("OK\n").getBytes());
	dout5.flush();


        BufferedReader bin6 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str6=(String)bin6.readLine();
        System.out.println(str6);

        DataOutputStream dout10=new DataOutputStream(s.getOutputStream());
        dout10.write(("OK\n").getBytes());
	dout10.flush();


        DataOutputStream dout11=new DataOutputStream(s.getOutputStream());
        dout11.write(("OK\n").getBytes());
	dout11.flush();

        DataOutputStream dout6=new DataOutputStream(s.getOutputStream());
        dout6.write(("SCHD super-silk 3 124\n").getBytes());
        dout6.flush();

//        DataOutputStream dout6=new DataOutputStream(s.getOutputStream());
//        dout6.write(("QUIT\n").getBytes());
//	dout6.flush();
	
	s.close();
	System.out.println("client disconnected...");

	}catch(Exception e){System.out.println(e);} 
}

}
