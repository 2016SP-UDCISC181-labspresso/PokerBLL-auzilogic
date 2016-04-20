package pokerBase;
import java.util.UUID;
public class Player {
/////////////////////////////////////////////////////////////////////
//..........................ATTRIBUTES............................//                              
////////////////////////////////////////////////////////////////////
	
	private UUID playerID;
	private String playerName;
	private int playerPos;
	
/////////////////////////////////////////////////////////////////////
//........................CONSTRUCTORS............................//                        
////////////////////////////////////////////////////////////////////
public Player(UUID playerID, String playerName, int playerPos) {
super();
this.playerID = playerID;
this.playerName = playerName;
this.playerPos = playerPos;
}

	
/////////////////////////////////////////////////////////////////////
//.....................SETTERS & GETTERS.........................//                        
////////////////////////////////////////////////////////////////////
public UUID getPlayerID() {
	return playerID;
}

public void setPlayerID(UUID playerID) {
	this.playerID = playerID;
}

public String getPlayerName() {
	return playerName;
}

public void setPlayerName(String playerName) {
	this.playerName = playerName;
}

public int getPlayerPos() {
	return playerPos;
}

public void setPlayerPos(int playerPos) {
	this.playerPos = playerPos;
}

	
	

	


/////////////////////////////////////////////////////////////////////
//.......................... METHODS..............................//                       
////////////////////////////////////////////////////////////////////


	


	

}
