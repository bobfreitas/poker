package com.ask.poker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ask.poker.BestHand;
import com.ask.poker.Constants;
import com.ask.poker.PokerException;
import com.sampullara.poker.Card;
import com.sampullara.poker.Cards;
import com.sampullara.poker.HandRank;

/*
 * This is an implemetation of the PokerService that is based upon
 * an open source library, http://code.google.com/p/pokerengine/source/checkout.
 * That library is included in the project, because it needed to be 
 * modified to address some shortcommings:
 *   1) needed to expose the best hand from HandRank
 *   2) needed to add a single card parser to Card
 *   3) needed to change the toString() in HandRank.Rank to be prettier
 *   
 * However, the pokerengine should be pulled out into a separate artifact.
 * 
 */
public class PokerServiceImpl implements PokerService {
	
	private static final CardComparator cardComparator = new CardComparator();

	@Override
	public BestHand findBestHand(String cardsSingleStr) throws PokerException {
		// first separate the string into separate cards
		String[] cardsStrArr = cardsSingleStr.split(" ");
		if (cardsStrArr.length != Constants.DEF_ORIG_HAND_SIZE){
			throw new PokerException("Starting hand has the wrong number of cards");
		}
		
		// next create Card objects and need to sort them by card rank in descending
		// order for the Sampullara pokerengine to process it correctly
		Cards cards = new Cards(Constants.DEF_ORIG_HAND_SIZE);
		for (String cardStr : cardsStrArr){
			Card card = Card.parseSingle(cardStr);
			cards.add(card);
		}
		Collections.sort(cards, cardComparator);
		
		// find the best hand in the set of card and return it
		HandRank handRank = new HandRank(cards);
		BestHand bestHand = new BestHand();
		bestHand.setOrigCards(Arrays.asList(cardsStrArr));
		bestHand.setBestCards(convertCardsToString(handRank.getHand()));
		bestHand.setRank(handRank.getRank().toString());
		return bestHand;
	}
	
	private List<String> convertCardsToString(Cards cards){
		List<String> strCards = new ArrayList<String>(cards.size());
		for (Card card : cards) {
			strCards.add(card.toString());
		}
		return strCards;
	}

}

