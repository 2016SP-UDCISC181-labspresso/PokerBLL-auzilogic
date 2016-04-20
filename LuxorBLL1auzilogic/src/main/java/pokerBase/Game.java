package pokerBase;

import java.util.ArrayList;
import java.util.UUID;

public class Game {
/////////////////////////////////////////////////////////////////////
//..........................ATTRIBUTES............................//                              
//////////////////////////////////////////////////////////////////// 
	
	 
	private UUID gameID;
	private ArrayList<Player> gamePlayers = new ArrayList<Player>();
	

/////////////////////////////////////////////////////////////////////
//........................CONSTRUCTORS............................//                        
////////////////////////////////////////////////////////////////////

public Game(UUID gameID, ArrayList<Player> tablePlayers) {
super();
this.gameID = gameID;
this.gamePlayers = tablePlayers;
}

	

/////////////////////////////////////////////////////////////////////
//......................SETTERS & GETTERS.........................//                        
////////////////////////////////////////////////////////////////////
	
	public UUID getGameID() {
		return gameID;
	}
	public void setGameID(UUID gameID) {
		this.gameID = gameID;
	}
	public ArrayList<Player> getTablePlayers() {
		return gamePlayers;
	}
	public void setTablePlayers(ArrayList<Player> tablePlayers) {
		this.gamePlayers = tablePlayers;
	}


	
/////////////////////////////////////////////////////////////////////
//...........................METHODS..............................//                       
////////////////////////////////////////////////////////////////////
	public void addPlayerToGame(Table table, Player player){
		
		
		}
	




}
