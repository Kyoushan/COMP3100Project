import java.io.IOException;

//In order to minimise the turnaround time of a particular job
//the job must be completed as quickly as possible.
//This can be achieved by allocating the smallest jobs to the smallest
//servers and the largest jobs to the smallest large server that can 
//accomodate it. 
//
//
//Understanding with what compromise to create this algorithm is key in
//justifying what to prioritise. 
//
//
//
//It may be wise to use the TERM command to kill the servers that are not
//actively in use on ds-sim
//
//
//
//After each job has effectively been allocated, do a check on all the servers
//to see if there are any servers idle

//SCHEDULE THE JOBS FROM THE LARGESET AVAILABLE SERVERS TO THE SMALLEST AND CHECK 
public class CustomAlg extends Client {

    static int jobIndex = 0;
	static int sIndex;
	static int sLimit;
    static String[] servData;
    
    public static void algorithm(){
        try {
            for(int i =0; i< nRecs; i++){
                output = (String) bin.readLine();
                System.out.println(output);
                servData=output.split(" ");
                serverRecs[i]=new Server(servData[0],
                            Integer.parseInt(servData[1]),
                            Integer.parseInt(servData[4]));
    
    
                if(serverRecs[i].servCores > serverRecs[sIndex].servCores){
                    sIndex = i;
                    sLimit = 1;
                }else if(serverRecs[i].servType.equals(serverRecs[sIndex].servType)){
                    sLimit++;
                }
            }



            
            
    } catch (IOException e){System.out.println(e);}
    }

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
