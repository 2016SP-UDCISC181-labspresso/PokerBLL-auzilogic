package pokerBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class GamePlay implements Serializable   {

	//TODO Auzi - compare game w/gameplay, discuss any modifications we would like to make
	 
	
	private UUID GameID;
	private LinkedList lnkPlayerOrder = new LinkedList<UUID>();	
	private UUID PlayerID_NextToAct = null;
	private HashMap<UUID, Player> hmGamePlayers = new HashMap<UUID, Player>();
	private ArrayList<GamePlayPlayerHand> GamePlayerHand = new ArrayList<GamePlayPlayerHand>();
	private ArrayList<GamePlayPlayerHand> GameCommonHand = new ArrayList<GamePlayPlayerHand>();
	private Rule rle;
	private Deck GameDeck = null;
	private UUID GameDealer = null;
	
	public GamePlay(Rule rle, UUID GameDealerID)
	{
		this.setGameID(UUID.randomUUID());
		this.setGameDealer(GameDealer);
		this.rle = rle;
	}

	public UUID getGameID() {
		return GameID;
	}

	public void setGameID(UUID gameID) {
		GameID = gameID;
	}

	public Rule getRule()
	{
		return this.rle;
	}
	
	public HashMap<UUID, Player> getGamePlayers() {
		return hmGamePlayers;
	}

	public void setGamePlayers(HashMap<UUID, Player> gamePlayers) {
		this.hmGamePlayers = new HashMap<UUID, Player>(gamePlayers);
	}
	
	public void addPlayerToGame(Player p)
	{
		this.hmGamePlayers.put(p.getPlayerID(),p);
	}
	public Player getGamePlayer(UUID PlayerID)
	{
		return (Player) this.hmGamePlayers.get(PlayerID);
	}

	public Deck getGameDeck() {
		return GameDeck;
	}

	public void setGameDeck(Deck gameDeck) {
		GameDeck = gameDeck;
	}
	
	public UUID getGameDealer() {
		return GameDealer;
	}

	private void setGameDealer(UUID gameDealer) {
		GameDealer = gameDealer;
	}

	public void addGamePlayPlayerHand(GamePlayPlayerHand GPPH)
	{
		GamePlayerHand.add(GPPH);
	}
	
	public void addGamePlayCommonHand(GamePlayPlayerHand GPCH)
	{
		GameCommonHand.add(GPCH);
	}

	public void addLnkPlayerOrder(UUID PlayerID)
	{
		lnkPlayerOrder.add(PlayerID);
	}
	public LinkedList getLnkPlayerOrder() {
		return lnkPlayerOrder;
	}

	public UUID getPlayerID_NextToAct() {
		return PlayerID_NextToAct;
	}

	public void setPlayerID_NextToAct(UUID playerID_NextToAct) {
		PlayerID_NextToAct = playerID_NextToAct;
	}
	
	/*
	public GamePlayPlayerHand FindCommonHand(GamePlay gme)
	{
		GamePlayPlayerHand GPCH = null;
		for (GamePlayPlayerHand GPPH: GameCommonHand)
		{
			if (GPPH.getGame().getGameID() == gme.getGameID())
			{
				GPCH = GPPH;
			}
		}		
		return GPCH;
	}
	*/
	
/*	public GamePlayPlayerHand FindPlayerGame(GamePlay gme, Player p)
	{
		GamePlayPlayerHand GPPHReturn = null;
		
	
		for (GamePlayPlayerHand GPPH: GamePlayerHand)
		{
			if (p.getiPlayerPosition() == GPPH.getPlayer().getiPlayerPosition())
			{
				GPPHReturn = GPPH;
			}
		}
		return GPPHReturn;
	}*/
	
	
}
