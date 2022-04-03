import java.io.*;  
import java.net.*;  
public class Client{

	public static String attributeGetter(String s){
		String[] s2= s.split(" ");
		String s3 = s2[4] + " " + s2[5] + " " + s2[6];
		return s3;
	}

	public static String findServer(String a, String b){
		String[] s1=a.split(" ");
		String[] s2=b.split(" ");
		if(Integer.parseInt(s1[4]) > Integer.parseInt(s2[4])){
			return s1[0] + " " + s1[1];

		}else
			return s2[0] + " " + s2[1];
		
	}
	
	public static void main(String[]args){
	try{
	Socket s= new Socket("127.0.0.1", 50000);
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());
  	BufferedReader bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
	dout.write(("HELO\n").getBytes());
	dout.flush();
        String str=(String)bin.readLine();
	System.out.println(str);

        dout.write(("AUTHAngus\n").getBytes());
	dout.flush();

        String str2=(String)bin.readLine();
	System.out.println(str2);

	do{
        dout.write(("REDY\n").getBytes());
		dout.flush();
	
        String str3=(String)bin.readLine();
		System.out.println(str3);

		String[] jobs = str3.split(" ");
		int jobID = Integer.parseInt(jobs[2]);

        dout.write(("GETS Capable "+ attributeGetter(str3) +"\n").getBytes());
		dout.flush();

        String str4=(String)bin.readLine();
        System.out.println(str4);

		String[] data = str4.split(" ");
		int nRecs = Integer.parseInt(data[1]);

        dout.write(("OK\n").getBytes());
		dout.flush();

		String largestServer= new String();
		String[] serverRecs = new String[nRecs];
		for(int i =0; i< nRecs; i++){
			serverRecs[i] = bin.readLine();
        	System.out.println(serverRecs[i]);
			if (i>1){
				largestServer=findServer(serverRecs[i-1], serverRecs[i]);
				}
        	}

    	dout.write(("OK\n").getBytes());
		dout.flush();

//		if(jobs[0]=="JOBN"){
       		 dout.write(("SCHD " + jobID + " " + largestServer + "\n").getBytes());
       		 dout.flush();
//		}
}while(bin.readLine() != "NONE");

        dout.write(("QUIT\n").getBytes());
		dout.flush();
	
	s.close();
	System.out.println("client disconnected...");

	}catch(Exception e){System.out.println(e);} 
}

}
