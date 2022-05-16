//Class to specify the attributes of the server gathered from the data
//provided by ds-sim
public class Server {
    String servType;
    int servID;
	int servCores;

	public Server(String type, int id, int cores){
		servType = type;
        servID = id;
		servCores = cores;
	}
}
