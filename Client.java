import java.io.*;  
import java.net.*;  

public class Client{

	static Socket s;
	static BufferedReader bin;
	static DataOutputStream dout;
	static String output = new String();
	static Server[] serverRecs;
	static int sIndex;
	static int sLimit;

	public static void main(String[]args){
	try{
		s= new Socket("127.0.0.1", 50000);
		dout=new DataOutputStream(s.getOutputStream());
  		bin = new BufferedReader(new InputStreamReader(s.getInputStream()));
		int jobIndex = 0;
		
		dout.write(("HELO\n").getBytes());
        String str=(String)bin.readLine();
		System.out.println(str);
        dout.write(("AUTHAngus\n").getBytes());
        String str2=(String)bin.readLine();
		System.out.println(str2);

		while(!output.equals("NONE")){
        	dout.write(("REDY\n").getBytes());
        	output=(String)bin.readLine();
			System.out.println(output);
			String[] jobs = output.split(" ");

			if(serverRecs == null)
				serverFind();

			if(jobs[0].equals("JOBN")){
       			dout.write(("SCHD " + jobs[2] + " " + 
				   			serverRecs[sIndex].servType + " " + 
							jobIndex % sLimit + "\n").getBytes());
				output = (String) bin.readLine();
				System.out.println(output);
				jobIndex++;
			}
}
    dout.write(("QUIT\n").getBytes());
	dout.flush();
	dout.close();
	s.close();
	System.out.println("client disconnected...");
	}catch(Exception e){System.out.println(e);} 
}

public static void serverFind(){
try{
	dout.write(("GETS Capable " + attributeGetter(output) +"\n").getBytes());
	output=(String)bin.readLine();
	System.out.println(output);
	String[] data = output.split(" ");
	int nRecs = 0;
	nRecs = Integer.parseInt(data[1]);

	serverRecs = new Server[nRecs];
	dout.write(("OK\n").getBytes());

	for(int i =0; i< nRecs; i++){
		output = (String) bin.readLine();
		System.out.println(output);
		String[] servData=output.split(" ");
		serverRecs[i]=new Server(servData[0],
							Integer.parseInt(servData[1]),
							Integer.parseInt(servData[4]));


		if(serverRecs[i].servCores> serverRecs[sIndex].servCores){
			sIndex = i;
			sLimit = 1;
		}else if(serverRecs[i].servType.equals(serverRecs[sIndex].servType)){
			sLimit++;
		}
	}

	dout.write(("OK\n").getBytes());
	output = (String) bin.readLine();
	System.out.println(output);
	
	}catch(Exception e){System.out.println(e);} 
}

public static String attributeGetter(String s){
	String[] s2= s.split(" ");
	String s3 = s2[4] + " " + s2[5] + " " + s2[6];
	return s3;
	}
}
