package pokerBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import exceptions.DeckException;
import exceptions.HandException;
import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class Hand {
 
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;
	private HandScore HandScore;
	private ArrayList<eRank> WildRanks;
	private boolean bScored = false;

	public Hand() {
		CardsInHand = new ArrayList<Card>();
		BestCardsInHand = new ArrayList<Card>();
		WildRanks = new ArrayList<eRank>();
		WildRanks.add(eRank.JOKER);
		
	}
	private Hand(ArrayList<Card> cardsToLoad){
		this();
		this.setCardsInHand(cardsToLoad);
		
	}
	
	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}

	private void setCardsInHand(ArrayList<Card> cardsInHand) {
		CardsInHand = cardsInHand;
	}

	private ArrayList<Card> getBestCardsInHand() {
		return BestCardsInHand;
	}

	private void setBestCardsInHand(ArrayList<Card> bestCardsInHand) {
		BestCardsInHand = bestCardsInHand;
	}

	public HandScore getHandScore() {
		return HandScore;
	}

	private void setHandScore(HandScore handScore) {
		HandScore = handScore;
	}
	public ArrayList<eRank> getWilds(){
		return this.WildRanks; 
	}
	private void setWilds(ArrayList<eRank> wildRanks){
		WildRanks = wildRanks;
	}

	public boolean isbScored() {
		return bScored;
	}

	private void setbScored(boolean bScored) {
		this.bScored = bScored;
	}

	private Hand AddCardToHand(Card c) {
		CardsInHand.add(c);
		return this;
	}

	public Hand Draw(Deck d) throws DeckException {
		CardsInHand.add(d.Draw());
		return this;
	}

	/**
	 * EvaluateHand is a static method that will score a given Hand of cards
	 * 
	 * @param h
	 * @return
	 * @throws HandException
	 */
	public static Hand EvaluateHand(Hand h) throws HandException {

		Collections.sort(h.getCardsInHand());

		// Collections.sort(h.getCardsInHand(), Card.CardRank);

		if (h.getCardsInHand().size() != 5) {
			throw new HandException(h); 
		}

		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pokerBase.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pokerBase.Hand.class;
				cArg[1] = pokerBase.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				System.out.print(meth+ "\n"); 
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bScored = true;
			h.HandScore = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}  
	public static int NumberWilds(ArrayList<Card> hand){
		int numWild = 0;
		for(Card c : hand){
			if ((c.geteRank() == eRank.JOKER) | (c.isWild())){
				numWild ++;
			}
		}
		 
			 
		
		return numWild;
	}
	public static void convertWilds(ArrayList<Card> hand){
		for (Card c : hand){
			
			if(c.isWild()){
				c.seteRank(eRank.JOKER);
			}
		}
		
	}
	   public static boolean isHandFlush(ArrayList<Card> cards){
	        boolean bIsFlush = false;// set the boolean to false as usual 
	        
	        
	        eSuit compairTo = cards.get(NumberWilds(cards)).geteSuit();//compair subsequent cards' suits to the first card's suit
	        Hand tempHand = new Hand(cards);
	  
	        		
	        
	        for (Card c : cards){
	            
	            if ((c.geteSuit()==compairTo) | (c.geteRank() == eRank.JOKER)){
	                continue;
	            }
	            else{ 
	                return bIsFlush;//this will return false 
	                }
	        }
	        bIsFlush = true;
	        
	         
	        
	        return bIsFlush;//if it gets all the way through the loop, then bIsFlush will be true
	    }
		

	public static boolean isStraight(ArrayList<Card> cards, Card highCard) {
		boolean bIsStraight = false;
		boolean bAce = false;
		int wilds = NumberWilds(cards);//the number of wilds in the hand
		int gapToFill = 0;//how many wilds we need

		int iStartCard = wilds;//start right after the wilds
		highCard.seteRank(cards.get(eCardNo.FirstCard.getCardNo()).geteRank());//not sure if these need to be adjusted
		highCard.seteSuit(cards.get(eCardNo.FirstCard.getCardNo()).geteSuit());

		if ((cards.get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE) | (cards.get(eCardNo.FirstCard.getCardNo()).geteRank()==eRank.JOKER)) {
			// First card is an 'ace', handle aces 
			bAce = true; 
			
		}
   
		for (int a = iStartCard; a < cards.size() - 1; a++) {//starting after the wilds
			//System.out.print("gapsToFill " + gapToFill + "\n"); 
			int gap = (cards.get(a).geteRank().getiRankNbr() - cards.get(a + 1).geteRank().getiRankNbr());
			if(gap == 1){ //
				gapToFill += (gap -1);//useless, should be zero
				bIsStraight = true; 
				//System.out.print("first if statement"+ "\n");
				
			} 
			else if((gap > 1) && (gapToFill <= wilds)){// if the gap is greater than 1
				gapToFill += (gap -1);//adds to the total number of Wilds needed to fill the gap
				bIsStraight = true;
				    
				    
			} 
			if((gapToFill > wilds) | (gap == 0)){ //if we don't have enough wilds
				//System.out.print("not enough wilds!" + "\n"); 
				bIsStraight = false;   
				break; 
			}
			  
			 
				 
			 
			} 
	
		 
		

		if ((bAce) && (bIsStraight)) {
			bIsStraight = true;
			if ((cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.KING )|(cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.JOKER)) {
				highCard.seteRank(cards.get(eCardNo.FirstCard.getCardNo()).geteRank());
				highCard.seteSuit(cards.get(eCardNo.FirstCard.getCardNo()).geteSuit());
			} else if ((cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.FIVE)|(cards.get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.JOKER)) {
				highCard.seteRank(cards.get(eCardNo.SecondCard.getCardNo()).geteRank());
				highCard.seteSuit(cards.get(eCardNo.SecondCard.getCardNo()).geteSuit());
			} else {
				bIsStraight = false; 
			}
		}
		return bIsStraight; 
	} 

//	   public static boolean isStraight(ArrayList<Card> hand ,Card card,String s){
//	    	boolean isStraight = false;
//		   	int numWild = Hand.NumberWilds(hand);// the number of wilds
//		   	//System.out.println("NUMBER OF WILD"+numWild); 
//		   
//	    	int gapsToFill = 0; // how many Wilds do we need to make this a straight?
//	    	for(int i = numWild; i < (hand.size()-1)  ; i++){ // from the first card following wilds:
//	    		if (hand.get(i).geteRank() == eRank.JOKER){
//	    			continue;
//	    		} 
//	    		
//	    		//the gap between cards is equal to the value at index i minus the rank of the next card, if it's more than ONE, 
//	    		//we're gonna need some Wilds to fill that gap.
//	    		
//	    		int gap = ((hand.get(i).geteRank().getiRankNbr())    -  (hand.get(i+1).geteRank().getiRankNbr())); 
//	    	    //System.out.println("GAP Between"+ i +" And "+ i++ );                                                               
//	    		
//	    		//if the gap is more than one, we need to collect how many Wilds we're going to need
//	    		
//	    		if( gap > 1 ){
//	    		
//	    			//this is equal to the gap minus 1 ....if the gap is 1, for example, gap - 1 = 0...therefore we need zero Wilds
//	    			
//	    			gapsToFill += (gap - 1);
//	    			}
//	    	}
//	    	if(gapsToFill <= numWild){ 
//	    		
//	    		isStraight = true;
//	    		
//	    		
//	    		}
//	    	return isStraight;  
//	 }  

	public static boolean isHandFiveOfAKind(Hand h, HandScore hs) {

		boolean isFiveOfAKind = false;
		int wilds = NumberWilds(h.getCardsInHand());
		eRank compairTo = h.getCardsInHand().get(wilds).geteRank();
		
		for(int i = wilds; i < h.getCardsInHand().size(); i++){
			if(( h.getCardsInHand().get(i).geteRank()==compairTo)){
				continue;
			}else{
				return isFiveOfAKind;
			}
	
			
		}
		isFiveOfAKind = true;
		hs.setHiHand(h.getCardsInHand().get(wilds).geteRank().getiRankNbr());
		hs.setHandStrength(eHandStrength.FiveOfAKind.getHandStrength());
		return isFiveOfAKind;
		
		 
	}

	public static boolean isHandRoyalFlush(Hand h, HandScore hs) {

		Card c = new Card();
		boolean isRoyalFlush = false;
		if ((isHandFlush(h.getCardsInHand())) && (isStraight(h.getCardsInHand(), c))) {
			if (c.geteRank() == eRank.ACE) {
				isRoyalFlush = true;
				hs.setHandStrength(eHandStrength.RoyalFlush.getHandStrength());
				hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
				hs.setLoHand(0);
			}

		}
 
		return isRoyalFlush; 
	}

	public static boolean isHandStraightFlush(Hand h, HandScore hs) {
		Card c = new Card();
		boolean isRoyalFlush = false;
		if ((isHandFlush(h.getCardsInHand())) && (isStraight(h.getCardsInHand(), c))) {
			isRoyalFlush = true;
			hs.setHandStrength(eHandStrength.StraightFlush.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
		}

		return isRoyalFlush;
	}

	public static boolean isHandFourOfAKind(Hand h, HandScore hs) {

		boolean bHandCheck = false;
		int wilds = NumberWilds(h.getCardsInHand()); 
		if ((h.getCardsInHand().get(wilds) == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo())) & 
			(h.getCardsInHand().get(wilds) != h.getCardsInHand().get((eCardNo.FifthCard.getCardNo())))){
			
		}
		

		/*if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.FourOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			hs.setKickers(kickers);

		} else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.FourOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		} else if(NumberWilds(h.getCardsInHand()))*/
		
		


		return bHandCheck;
	}

	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
		}

		return isFullHouse;

	}

	public static boolean isHandFlush(Hand h, HandScore hs) {

		boolean bIsFlush = false;
		if (isHandFlush(h.getCardsInHand())) {
			hs.setHandStrength(eHandStrength.Flush.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			hs.setKickers(kickers);
			bIsFlush = true;
		}

		return bIsFlush;
	}

	public static boolean isHandStraight(Hand h, HandScore hs) {

		boolean bIsStraight = false;
		Card highCard = new Card();
		if (isStraight(h.getCardsInHand(), highCard)) {
			hs.setHandStrength(eHandStrength.Straight.getHandStrength());
			hs.setHiHand(highCard.geteRank().getiRankNbr());
			hs.setLoHand(0);
			bIsStraight = true;
		}
		return bIsStraight;
	} 

	public static boolean isHandThreeOfAKind(Hand h, HandScore hs) {
		int wilds = NumberWilds(h.getCardsInHand());
		boolean isThreeOfAKind = false;
		ArrayList<Card> kickers = new ArrayList<Card>(); 
		System.out.print("threeoak "+ wilds);
		if(wilds == 0){
			if(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()) == h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo())){
				isThreeOfAKind = true;
			}else if(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()) == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo())){
				isThreeOfAKind = true;
			}else if(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()) == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()) ){
				isThreeOfAKind = true;
			}  
		}
		else if(wilds == 1){
			//System.out.print("-------wilds = 1 ----------" +"\n");
			if((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo())) == (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()))){
				//System.out.print("\n" +"SECOND_CARD: "+ h.getCardsInHand().get(eCardNo.SecondCard.getCardNo())+ "\n");
				isThreeOfAKind = true;  
				
			}else if(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()){
				
				isThreeOfAKind = true;
				
			}else if(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank()){
				isThreeOfAKind = true;
			} 
			
				
			}
		else if(wilds == 2){
			if((h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()) != (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()) &
				(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()) !=(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank())){
				isThreeOfAKind = true;
				
			}
		}
				
		

		if (isThreeOfAKind) {
			hs.setHandStrength(eHandStrength.ThreeOfAKind.getHandStrength());
			hs.setLoHand(0);
			hs.setKickers(kickers);
		} 

		return isThreeOfAKind;
	} 
	
	public static boolean isHandTwoPair(Hand h, HandScore hs) { 
		int wilds = NumberWilds(h.getCardsInHand());
		boolean isTwoPair = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FourthCard.getCardNo()).geteRank())) {
			isTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr());
			kickers.add(h.getCardsInHand().get((eCardNo.FifthCard.getCardNo())));
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr());
			kickers.add(h.getCardsInHand().get((eCardNo.ThirdCard.getCardNo())));
			hs.setKickers(kickers);
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr());
			kickers.add(h.getCardsInHand().get((eCardNo.FirstCard.getCardNo())));
			hs.setKickers(kickers);
		} else if(NumberWilds(h.getCardsInHand()) >= 2){  
			isTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(wilds).geteRank().getiRankNbr()); 
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr());
			kickers.add(h.getCardsInHand().get(h.getCardsInHand().size()));
			hs.setKickers(kickers);
		
		} else if((NumberWilds(h.getCardsInHand()) == 1) & (isHandPair(h, hs))){
			isTwoPair = true; 
			hs.setKickers(null);
			hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(wilds).geteRank().getiRankNbr());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr());
			kickers.add(h.getCardsInHand().get(4));
			hs.setKickers(kickers);
			 
		} 
		 
		return isTwoPair;
	}  

	public static boolean isHandPair(Hand h, HandScore hs) {
		boolean isPair = false; 
		int wilds = NumberWilds(h.getCardsInHand());
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank()) {
			isPair = true;
			hs.setHandStrength(eHandStrength.Pair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			kickers.add(h.getCardsInHand().get((eCardNo.ThirdCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.FourthCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.FifthCard.getCardNo())));
			hs.setKickers(kickers);
		} else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank()) {
			isPair = true;
			hs.setHandStrength(eHandStrength.Pair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr()); 
			hs.setLoHand(0);
			kickers.add(h.getCardsInHand().get((eCardNo.FirstCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.FourthCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.FifthCard.getCardNo()))); 
			hs.setKickers(kickers);
		} else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank()) {
			isPair = true;
			hs.setHandStrength(eHandStrength.Pair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			kickers.add(h.getCardsInHand().get((eCardNo.FirstCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.SecondCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.FifthCard.getCardNo())));
			hs.setKickers(kickers);
		} else if (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank()) {
			isPair = true;
			hs.setHandStrength(eHandStrength.Pair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			kickers.add(h.getCardsInHand().get((eCardNo.FirstCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.SecondCard.getCardNo())));
			kickers.add(h.getCardsInHand().get((eCardNo.ThirdCard.getCardNo())));
			hs.setKickers(kickers);
		} else if(NumberWilds(h.getCardsInHand()) == 1){//if you have a wild, you always have a pair
			isPair = true;
			hs.setHandStrength(eHandStrength.Pair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(wilds).geteRank().getiRankNbr());
			hs.setLoHand(0);
			kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
			hs.setKickers(kickers);
			
		}
		return isPair;
	}

	public static boolean isHandHighCard(Hand h, HandScore hs) {
		hs.setHandStrength(eHandStrength.HighCard.getHandStrength());
		hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr());
		hs.setLoHand(0);
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
		kickers.add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		hs.setKickers(kickers);
		return true;
	}

	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandScore().getHandStrength() - h1.getHandScore().getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHandScore().getHiHand() - h1.getHandScore().getHiHand();
			if (result != 0) {
				return result;
			}

			result = h2.getHandScore().getLoHand() - h1.getHandScore().getLoHand();
			if (result != 0) {
				return result;
			}

			if (h2.getHandScore().getKickers().size() > 0) {
				if (h1.getHandScore().getKickers().size() > 0) {
					result = h2.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr()
							- h1.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank()
									.getiRankNbr();
				}
				if (result != 0) {
					return result;
				}
			}

			if (h2.getHandScore().getKickers().size() > 1) {
				if (h1.getHandScore().getKickers().size() > 1) {
					result = h2.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr()
							- h1.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank()
									.getiRankNbr();
				}
				if (result != 0) {
					return result;
				}
			}

			if (h2.getHandScore().getKickers().size() > 2) {
				if (h1.getHandScore().getKickers().size() > 2) {
					result = h2.getHandScore().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr()
							- h1.getHandScore().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank()
									.getiRankNbr();
				}
				if (result != 0) {
					return result;
				}
			}

			if (h2.getHandScore().getKickers().size() > 3) {
				if (h1.getHandScore().getKickers().size() > 3) {
					result = h2.getHandScore().getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr()
							- h1.getHandScore().getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank()
									.getiRankNbr();
				}
				if (result != 0) {
					return result;
				}
			}
			return 0;
		}
	};

}