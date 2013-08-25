package com.ask.poker;

import java.util.ArrayList;
import java.util.List;

/*
 * This is a container object to hold the best hand that was
 * found by the findBestHand() service call. 
 */
public class BestHand {
	
	private List<String> origCards;
	private List<String> bestCards;
	private String rank;
	
	public BestHand() {
		origCards = new ArrayList<String>(Constants.DEF_ORIG_HAND_SIZE);
		bestCards = new ArrayList<String>(Constants.DEF_BEST_HAND_SIZE);
	}
	
	public BestHand(int origHandSize, int bestHandSize) {
		origCards = new ArrayList<String>(origHandSize);
		bestCards = new ArrayList<String>(bestHandSize);
	}
	
	public List<String> getOrigCards() {
		return origCards;
	}
	public void setOrigCards(List<String> origCards) {
		this.origCards = origCards;
	}
	
	public List<String> getBestCards() {
		return bestCards;
	}
	public void setBestCards(List<String> bestCards) {
		this.bestCards = bestCards;
	}
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}

