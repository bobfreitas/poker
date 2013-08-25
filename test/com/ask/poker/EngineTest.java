package com.ask.poker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

import com.ask.poker.service.CardComparator;
import com.sampullara.poker.Card;
import com.sampullara.poker.Cards;
import com.sampullara.poker.HandRank;

public class EngineTest {

	@Test 
	public void testCreateCards() {
		Cards cards = Card.parse("AHKH3D6SJSJD3C");
		assertEquals(Card.Rank.ACE, cards.get(0).getRank());
		assertEquals(Card.Suit.HEARTS, cards.get(0).getSuit());
		assertEquals(Card.Rank.THREE, cards.get(2).getRank());
		assertEquals(Card.Suit.DIAMONDS, cards.get(2).getSuit());
		assertEquals(Card.Rank.THREE, cards.get(6).getRank());
		assertEquals(Card.Suit.CLUBS, cards.get(6).getSuit());
	}
	
	@Test
	public void testSingleCardParse() {
		Card card = Card.parseSingle("3D");
		assertEquals(Card.Rank.THREE, card.getRank());
		assertEquals(Card.Suit.DIAMONDS, card.getSuit());
	}
	
	
	@Test 
	public void testFlush() {
		Cards cards = new Cards(5);
		cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
		HandRank handRank = new HandRank(cards);
		HandRank.Rank rank = handRank.getRank();
		if (rank != HandRank.Rank.FLUSH) {
			fail("This should be a flush, instead it is a " + rank);
		}
	}
	
	@Test 
	public void testStraight() {
		Cards cards = new Cards(7);
		cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
		cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
		HandRank handRank = new HandRank(cards);
		HandRank.Rank rank = handRank.getRank();
		assertEquals(HandRank.Rank.STRAIGHT, rank);
		
		cards = new Cards(7);
		cards.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
		cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS));
		handRank = new HandRank(cards);
		rank = handRank.getRank();
		assertEquals(HandRank.Rank.STRAIGHT, rank);
		
	}
	
	
	@Test
	public void testSortCards(){
		Cards cards = new Cards(7);
		cards.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
		cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS));
		
		Collections.sort(cards, new CardComparator());
		StringBuffer sb = new StringBuffer();
		for (Card card: cards){
			sb.append(card.toString());
			sb.append(" ");
		}
		System.out.println("sorted cards: " + sb.toString());
//		assertEquals("Ad Kh Qd Jc Td 9h 9c", sb.toString());
	}
	
	
	
	@Test 
	public void testSevenCardHand() {
		Cards cards = new Cards(7);
		cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
		cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
		cards.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
		
		HandRank handRank = new HandRank(cards);
		HandRank.Rank rank = handRank.getRank();
		assertEquals(HandRank.Rank.FLUSH, rank);
		Cards highHand = handRank.getHand();
		
		assertEquals("QhJh9h5h2h", highHand.toString());
	}
	
	

}

