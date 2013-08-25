package com.ask.poker.service;

import java.util.Comparator;

import com.sampullara.poker.Card;

/*
 * Used to sort the cards in descending order.  Needed for 
 * the poker hand evaluator that needs the cards to be sorted
 * first.  
 */
public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		if (o1.getRank().ordinal() == o2.getRank().ordinal()){
			return 0;
		}
		if (o1.getRank().ordinal() > o2.getRank().ordinal()) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
