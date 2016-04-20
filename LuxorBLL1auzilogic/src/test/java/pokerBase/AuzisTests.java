package pokerBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import exceptions.HandException;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class AuzisTests {
/*	private Hand SetHand(ArrayList<Card> cardsToLoad, Hand h)
	{
		Object handInst = null;
		 
		try {
			//	Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Hand");//c is an impostor of pokerBase.Hand Class
			handInst = c.newInstance();//t is a new instance of the impostor c
			Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[]{ArrayList.class});
			//	Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			//	invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(handInst, cardsToLoad);
			
			
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		h = (Hand)handInst;//cast handInst as a Hand
		return h;
	}
	 
	@Test
	public void testHand() throws HandException{
		ArrayList hand1 = new ArrayList<Card>(); 
		hand1.add(new Card(eSuit.CLUBS,eRank.ACE,0));
		hand1.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		hand1.add(new Card(eSuit.CLUBS,eRank.JACK,0));
		hand1.add(new Card(eSuit.JOKER,eRank.JOKER,0));  
		hand1.add(new Card(eSuit.JOKER,eRank.JOKER,0));
		Collections.sort(hand1);
		Hand hand = new Hand(); 
		hand = SetHand(hand1,hand);  
	}
		
		 
		
		

	@Test
	public void Straighttest(){
		HandScore handScore = new HandScore();
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card(eSuit.HEARTS,eRank.THREE,0)); 
		hand2.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		hand2.add(new Card(eSuit.SPADES,eRank.FOUR,0));
		hand2.add(new Card(eSuit.SPADES,eRank.SIX,0));   
		hand2.add(new Card(eSuit.JOKER,eRank.JOKER,0));
		Collections.sort(hand2);  
		Hand hand = new Hand(); 
		Card c = new Card();
		hand = SetHand(hand2,hand);
		assertTrue(Hand.isHandStraight(hand,handScore));
		try { 
			System.out.println(Hand.EvaluateHand(hand).getHandScore().getHandStrength());
		} catch (HandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}  
		
	 
	
		
//	   	int numWild = Hand.NumberWilds(hand2);// the number of wilds
//    	int gapsToFill = 0; // how many Wilds do we need to make this a straight?
//    	System.out.println("NumberWild: "+ numWild);
//    	int WildsNeeded = 0;
//    	for(int i = numWild; i < (hand2.size()  ); i++){ // from the first card following wilds:
//    		System.out.println("i: "+ i );
//    		System.out.println("geteRank.getiRankNbr: "+((hand2.get(i).geteRank().getiRankNbr())));
//    		//the gap between cards is equal to the value at index i minus the rank of the next card, if it's more than ONE,
//    		//we're gonna need some Wilds to fill that gap.
//    		int gap = ((hand2.get(i).geteRank().getiRankNbr())    -  (hand2.get(i+1).geteRank().getiRankNbr())); 
//    		System.out.println("GAP: "+ gap);                                                                    
//    		//if the gap is more than one, we need to collect how many Wilds we're going to need
//    		if( gap > 1 ){
//    			//this is equal to the gap minus 1 ....if the gap is 1, for example, gap - 1 = 0...therefore we need zero Wilds
//    			gapsToFill += (gap - 1);
//    		}
//    	} 
    	

	} */

	 
//@Test 
//public void TestFlushEval() throws HandException {
//	
////	HandScore hs = new HandScore();
////	ArrayList<Card> Flush = new ArrayList<Card>();
////	Flush.add(new Card(eSuit.CLUBS,eRank.ACE,0));
////	Flush.add(new Card(eSuit.CLUBS,eRank.KING,0));
////	Flush.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
////	Flush.add(new Card(eSuit.CLUBS,eRank.QUEEN,0));
////	Flush.add(new Card(eSuit.CLUBS,eRank.TWO,0));
////	Collections.sort(Flush); 
////	Hand h = new Hand();
////	
////	for(Card c : Flush){  
////		System.out.println(c.geteRank()); 
////		 
////		
////	}
////	System.out.print("Wilds: "+h.NumberWilds(h.getCardsInHand()) + "\n");
////	assertEquals(Hand.isStraight(Flush, new Card()),false);
//	HandScore hs1 = new HandScore();
//	ArrayList<Card> Straight = new ArrayList<Card>();
//	Straight.add(new Card(eSuit.HEARTS,eRank.THREE,0));
//	Straight.add(new Card(eSuit.CLUBS,eRank.FOUR,0));
//	Straight.add(new Card(eSuit.HEARTS,eRank.SEVEN,0));		
//	Straight.add(new Card(eSuit.JOKER,eRank.JOKER,0));
//	Straight.add(new Card(eSuit.JOKER,eRank.JOKER,0));
//	Collections.sort(Straight); 
//	Hand h1 = new Hand();  
//	
//	for(Card c : Straight){    
//		System.out.println(c.geteRank()); 
//		 
//		     
//	} 
//	assertTrue(Hand.isStraight(Straight,new Card()));
// 
//}  

private Hand SetHand(ArrayList<Card> setCards, Hand h)
{
	Object t = null;
	
	try {
		//	Load the Class into 'c'
		Class<?> c = Class.forName("pokerBase.Hand");
		//	Create a new instance 't' from the no-arg Deck constructor
		t = c.newInstance();
		//	Load 'msetCardsInHand' with the 'Draw' method (no args);
		Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[]{ArrayList.class});
		//	Change the visibility of 'setCardsInHand' to true *Good Grief!*
		msetCardsInHand.setAccessible(true);
		//	invoke 'msetCardsInHand'
		Object oDraw = msetCardsInHand.invoke(t, setCards);
		
		
	} catch (ClassNotFoundException x) {
		x.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		e.printStackTrace(); 
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	}
	 
	h = (Hand)t;
	return h;
	
}
//@Test
/*public void TestHighCardEval() {
	
	HandScore hs = new HandScore();
	ArrayList<Card> HighCard = new ArrayList<Card>();
	HighCard.add(new Card(eSuit.CLUBS,eRank.TWO,0));
	HighCard.add(new Card(eSuit.CLUBS,eRank.THREE,0));
	HighCard.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
	HighCard.add(new Card(eSuit.DIAMONDS,eRank.SEVEN,0));
	HighCard.add(new Card(eSuit.DIAMONDS,eRank.KING,0));
	Collections.sort(HighCard);
	Hand h = new Hand();
	h = SetHand(HighCard,h);
	for(Card c : HighCard){    
	System.out.println(c.geteRank());
	}
	
	try {
		h = Hand.EvaluateHand(h);
	} catch (HandException e) {			
		e.printStackTrace(); 
		fail("TestStraightEval failed"); 
	}		

	int iActualIsHandHighCard = h.getHandScore().getHandStrength(); 
	System.out.print(iActualIsHandHighCard + "\n"); 
	int iExpectedIsHandHighCard = eHandStrength.HighCard.getHandStrength();
	System.out.print(iExpectedIsHandHighCard);
	assertEquals(iActualIsHandHighCard,iExpectedIsHandHighCard);		
	assertEquals(h.getHandScore().getHiHand(),eRank.KING.getiRankNbr());		
	
	assertEquals(h.getHandScore().getLoHand(),0);

	assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.SEVEN);
	assertEquals(h.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.FIVE);
	assertEquals(h.getHandScore().getKickers().get(eCardNo.ThirdCard.getCardNo()).geteRank(), eRank.THREE);
	assertEquals(h.getHandScore().getKickers().get(eCardNo.FourthCard.getCardNo()).geteRank(), eRank.TWO);
	 
}

//@Test 
public void twoPairTest(){
		
		HandScore hs = new HandScore();
		
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.JOKER,eRank.JOKER,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
		TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));		
		TwoPair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
		//TwoPair.add(new Card(eSuit.DIAMONDS,eRank.THREE,0)); 
		Collections.sort(TwoPair); 
		
		
		Hand h = new Hand(); 
		
		h = SetHand(TwoPair,h);
		 
	
		
		boolean bActualIsTwoPair = Hand.isHandTwoPair(h, hs);
		
		boolean bExpectedIsTwoPair = true;
		 
		assertEquals(bActualIsTwoPair,bExpectedIsTwoPair);		
		assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
		hs.getKickers(); 
		assertEquals(hs.getLoHand(),eRank.TWO.getiRankNbr());
		//for(Card c: hs.getKickers()){
			//System.out.print("Kicker: " + c.geteRank() + "\n");
		//}
		
		assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.TWO);

	}
//@Test
public void TestTwoPairEval() {
	
	HandScore hs = new HandScore();
	ArrayList<Card> TwoPair = new ArrayList<Card>();
	TwoPair.add(new Card(eSuit.JOKER,eRank.JOKER,0));
	TwoPair.add(new Card(eSuit.CLUBS,eRank.TWO,0));
	TwoPair.add(new Card(eSuit.CLUBS,eRank.FIVE,0));		
	TwoPair.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
	TwoPair.add(new Card(eSuit.DIAMONDS,eRank.KING,0));
	Collections.sort(TwoPair);
	Hand h = new Hand();
	h = SetHand(TwoPair,h);
	
	try {
		h = Hand.EvaluateHand(h);
	} catch (HandException e) {			
		e.printStackTrace();
		fail("TestStraightEval failed");
	}		

	int iActualIsHandTwoPair = h.getHandScore().getHandStrength();
	int iExpectedIsHandTwoPair = eHandStrength.TwoPair.getHandStrength();
	
	assertEquals(iActualIsHandTwoPair,iExpectedIsHandTwoPair);		
	System.out.print("2p "+ iActualIsHandTwoPair + "\n");
	//assertEquals(h.getHandScore().getHiHand(),eRank.FIVE.getiRankNbr());		//fails
	 
	
	assertEquals(h.getHandScore().getLoHand(),eRank.TWO.getiRankNbr());
	
//	assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.KING);
	
	
	
}
//@Test 
public void TestThreeOfAKind1() {
	
	HandScore hs = new HandScore();
	ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
	ThreeOfAKind.add(new Card(eSuit.JOKER,eRank.JOKER,0));
	ThreeOfAKind.add(new Card(eSuit.SPADES,eRank.SIX,0));
	ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.SIX,0));		
	ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.EIGHT,0));
	ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.NINE,0)); 
	Collections.sort(ThreeOfAKind);
	Hand h = new Hand(); 
	h = SetHand(ThreeOfAKind,h);
	
	boolean bActualIsThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
	boolean bExpectedIsThreeOfAKind = true;
	
	assertEquals(bActualIsThreeOfAKind,bExpectedIsThreeOfAKind);		
	//assertEquals(hs.getHiHand(),eRank.FIVE.getiRankNbr());	
			
	//assertEquals(hs.getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.NINE);
	//assertEquals(hs.getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.EIGHT);
}	*/
@Test
public void TestThreeOfAKindEval() { 
	
	HandScore hs = new HandScore();
	ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
	ThreeOfAKind.add(new Card(eSuit.JOKER,eRank.JOKER,0));
	ThreeOfAKind.add(new Card(eSuit.JOKER,eRank.JOKER,0)); 
	ThreeOfAKind.add(new Card(eSuit.CLUBS,eRank.JACK,0));		
	ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.FIVE,0));
	ThreeOfAKind.add(new Card(eSuit.DIAMONDS,eRank.ACE,0));
	Collections.sort(ThreeOfAKind);
	Hand h = new Hand();
	h = SetHand(ThreeOfAKind,h);
	for(Card c : h.getCardsInHand()){    
		System.out.println(c.geteRank());
		}
	
	try {
		
		h = Hand.EvaluateHand(h);
		
	} catch (HandException e) {			  
		e.printStackTrace();   
		fail("TestStraightEval failed");    
	}	
	System.out.print( h.getHandScore().getHandStrength() + "\n");

	int iActualIsHandThreeOfAKind = h.getHandScore().getHandStrength();
	int iExpectedIsHandThreeOfAKind = eHandStrength.ThreeOfAKind.getHandStrength();
	
	assertEquals(iActualIsHandThreeOfAKind,iExpectedIsHandThreeOfAKind);		
	//assertEquals(h.getHandScore().getHiHand(),eRank.FIVE.getiRankNbr());		
	 
	//assertEquals(h.getHandScore().getKickers().get(eCardNo.FirstCard.getCardNo()).geteRank(), eRank.THREE);
	//assertEquals(h.getHandScore().getKickers().get(eCardNo.SecondCard.getCardNo()).geteRank(), eRank.TWO);		
}	
} 
  
 

 
