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
public class CustomAlg extends Client {
    public static void algorithm(){
        try {
            dout.write(("GETS Capable " + attributeGetter(output) +"\n").getBytes());
            output=(String)bin.readLine();
            System.out.println(output);
            
    } catch (IOException e){System.out.println(e);}
    }
}
