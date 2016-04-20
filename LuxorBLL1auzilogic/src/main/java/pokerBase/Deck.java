package pokerBase;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.DeckException;
import pokerEnums.eRank;
import pokerEnums.eSuit;

/**
 * 
 * @author Bert.Gibbons
 *
 */
public class Deck {

	/**
	 * 
	 */
	private ArrayList<Card> deckCards = new ArrayList<Card>();
	private ArrayList<Card> wildCards = new ArrayList<Card>();
	private ArrayList<eRank> wildRanks = new ArrayList<eRank>();

	/** 
	 * No arg constructor for deck, will return shuffled deck of 52 cards
	 */ 
	public Deck()
	{
		
		int iCardNbr = 1;
		for (eSuit eSuit : eSuit.values()) {
			for (eRank eRank : eRank.values()) { 
				if ((eRank != eRank.JOKER) && (eSuit != eSuit.JOKER))
					deckCards.add(new Card(eSuit, eRank, iCardNbr++));				
			}		
		}		
		Collections.shuffle(deckCards);
	} 
	
	public Deck(int iNbrOfJokers)
	{ 
		this();
		
		for (int i = 0; i<iNbrOfJokers;i++)
		{
			deckCards.add(new Card(eSuit.JOKER, eRank.JOKER, 53));
		}
		Collections.shuffle(deckCards);
	}
	
	
	public Deck (int NbrOfJokers, ArrayList<Card> wilds)//just an idea for now
	{
		this(NbrOfJokers);
		for(Card c : this.deckCards){
			if(wilds.contains(c)){
				c.setWild(true);
			}
		}
		
		
		// Work to do!  Make the existing Deck cards Wild...  
		
	}
	
	
	
	
	
	public ArrayList<Card> getWildCards() {
		return wildCards;
	}

	public void setWildCards(ArrayList<Card> wildCards) {
		this.wildCards = wildCards;
	}

	public ArrayList<eRank> getWildRanks() {
		return this.wildRanks;
	}

	public void setWildRanks(ArrayList<eRank> wildRanks) {
		this.wildRanks = wildRanks;
	}

	/**
	 * Draws a card from the instance of Deck
	 * @return
	 * @throws DeckException
	 */
	public Card Draw() throws DeckException 
	{
		if (deckCards.size() == 0)
		{
			throw new DeckException(this);
		}
		return deckCards.remove(0);
	}
	
	/**
	 * Returns the number of cards left in the deck
	 * @return
	 */
	private int GetDeckSize()
	{
		return deckCards.size();	
	}
}
