package com.ask.poker.service;

import com.ask.poker.BestHand;
import com.ask.poker.PokerException;

/* 
 * This will allow for the set of poker related service calls.
 * Right now there is only one operation, but this could change in the
 * future with additional operations.  
 */
public interface PokerService {
	
	BestHand findBestHand(String cards) throws PokerException;

}
