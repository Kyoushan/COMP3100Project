import java.io.IOException;

public class LRR extends Client{

    static int jobIndex = 0;
	static int sIndex;
	static int sLimit;

//Main LRR algorithm function
    public static void algorthim(){
try{
	dout.write(("GETS Capable " + attributeGetter(output) +"\n").getBytes());
	output=(String)bin.readLine();
	System.out.println(output);
	
    data = output.split(" ");
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

//Printer function to display the SCHD commands to the server
public static void printer(){
    try {
        dout.write(("SCHD " + jobs[2] + " " + 
                    serverRecs[sIndex].servType + " " + 
                     jobIndex % sLimit + "\n").getBytes());
        output = (String) bin.readLine();
        System.out.println(output);
    } catch (IOException e) {System.out.println(e);}
     jobIndex++;
 }
}
