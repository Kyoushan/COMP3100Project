import java.io.IOException;

public class SchFATC extends Client {

    static String[] serverRecs;
    
    public static void algorithm(){
        try {
            jobs = output.split(" ");
            if(jobs[0].equals("JOBN")){
				attributes = attributeGetter(output);
                getsInfo("Avail");

				if (nRecs == 0){
                    getsInfo("Capable");
				}
				serverRecs=output.split(" ");

				for (int i = 1; i < nRecs; i++) {
					output = (String) bin.readLine();
				}
				dout.write(("OK\n").getBytes());
				output = (String)bin.readLine();

				dout.write(("SCHD " + jobs[2] + " " + 
				serverRecs[0] + " " + 
				serverRecs[1] + "\n").getBytes());
				output = (String)bin.readLine();
			}
        } catch (IOException e){System.out.println(e);}
    }
}
