package pokerBase;

import java.util.ArrayList;
import java.util.UUID;

public class Table {
	
	
	
	
/////////////////////////////////////////////////////////////////////
//..........................ATTRIBUTES............................//                              
////////////////////////////////////////////////////////////////////
	private UUID TableID;
	private ArrayList<Player> tablePlayers = new ArrayList<Player>();
	
/////////////////////////////////////////////////////////////////////
//........................CONSTRUCTORS............................//                        
////////////////////////////////////////////////////////////////////

public Table(UUID tableID, ArrayList<Player> tablePlayers) {
super();
TableID = tableID;
this.tablePlayers = tablePlayers;
}	

/////////////////////////////////////////////////////////////////////
//......................SETTERS & GETTERS.........................//                        
////////////////////////////////////////////////////////////////////
	public UUID getTableID() {
		return TableID;
	}
	public void setTableID(UUID tableID) {
		TableID = tableID;
	}
	public ArrayList<Player> getTablePlayers() {
		return tablePlayers;
	}
	public void setTablePlayers(ArrayList<Player> tablePlayers) {
		this.tablePlayers = tablePlayers;
	}



/////////////////////////////////////////////////////////////////////
//.......................... METHODS..............................//                       
////////////////////////////////////////////////////////////////////
	public void addPlayerToTable(Player player){
		
		tablePlayers.add(player);
		
		
		
	}
	
}
