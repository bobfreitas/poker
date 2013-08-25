package com.ask.poker;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ask.poker.service.PokerService;
import com.ask.poker.service.PokerServiceImpl;

import com.sampullara.poker.HandRank;

public class PokerServiceTest {
	
	private PokerService pokerService = new PokerServiceImpl();
	
	@Test
	public void testSingleCardParse() throws Exception {
		BestHand bestHand = pokerService.findBestHand("AH KH 3D 6S JS JD 3C");
		assertEquals(HandRank.Rank.TWOPAIR.toString(), bestHand.getRank());
	}
	
	@Test
	public void testBadCardParse(){
		//FIXME: want to use enumeration instead of string compare
		String expErrMsg = "Starting hand has the wrong number of cards";
		try {
			pokerService.findBestHand("AH KH 3D 6S JS JD");
			fail("Expected an exception");
		} catch (PokerException pe) {
			assertEquals(expErrMsg, pe.getMessage());
		}
		
	}
	

}

