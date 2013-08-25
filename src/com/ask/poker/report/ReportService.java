package com.ask.poker.report;

import com.ask.poker.BestHand;

/*
 * This interface will be used for any of the implementations 
 * that need to offer services for poker related actions.  
 */
public interface ReportService {
	
	boolean addBestHand(BestHand bestHand);
	
	void generateReport();
	
	void clear();

}