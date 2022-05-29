import java.io.IOException;

public class SchLRR extends Client{

    static int jobIndex = 0;
	static int sIndex;
	static int sLimit;
	static Server[] serverRecs;
	static String[] servData;


public static void algorithm(){
	try{
		jobs = output.split(" ");
        if(jobs[0].equals("JOBN")){
			attributes = attributeGetter(output);
            getsInfo("Avail");

			if (nRecs == 0){
                getsInfo("Capable");
			}
			serverRecs = new Server[nRecs];
			System.out.println(nRecs);
			serverFinder();
			
			dout.write(("OK\n").getBytes());
			output = (String) bin.readLine();
			System.out.println(output);

			if(jobs[0].equals("JOBN")){
			printer();
			}

			jobIndex++;

		}
	} catch (IOException e){System.out.println(e);}
}

//Main LRR algorithm function that finds the largest server.
//If any are the same type of server, it will get the first
//of the servers kind.
public static void serverFinder(){
    try{
    	for(int i =0; i< nRecs; i++){
	        output = (String) bin.readLine();
	        System.out.println(output);
			servData=output.split(" ");

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
    }catch(IOException e){System.out.println(e);} 
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
 }

}
