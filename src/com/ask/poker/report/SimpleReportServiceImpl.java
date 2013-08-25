package com.ask.poker.report;

import java.util.ArrayList;
import java.util.List;

import com.ask.poker.BestHand;

/*
 * This class is a very simple implementation for writting 
 * small reports.  It uses an in memory list and batches the lines
 * together for the final report generation.  The final report
 * is simply written out to the console. 
 */
public class SimpleReportServiceImpl implements ReportService {
	
	private static int DEF_REPORT_LINES = 1024;
	private static int DEF_LINE_LEN = 100;
	
	private List<String> reportLines;
	
	public SimpleReportServiceImpl() {
		reportLines = new ArrayList<String>(DEF_REPORT_LINES);
	}
	
	public SimpleReportServiceImpl(int lines) {
		reportLines = new ArrayList<String>(lines);
	}

	@Override
	public boolean addBestHand(BestHand bestHand) {
		StringBuffer sb = new StringBuffer(DEF_LINE_LEN);
		for (String card : bestHand.getOrigCards()){
			sb.append(card);
			sb.append(" ");
		}
		sb.append(" => ");
		for (String card : bestHand.getBestCards()){
			sb.append(card);
			sb.append(" ");
		}
		sb.append(" - ");
		sb.append(bestHand.getRank());
		reportLines.add(sb.toString());
		return true;
	}
	
	@Override
	public void generateReport() {
		// FIXME: need to use discriminator for different reports
		System.out.println("Best Poker Hands:");
		System.out.println("Starting Hand            Best Hand         Type");
		System.out.println("--------------------     --------------    ---------");
		
		for(String line: reportLines){
			System.out.println(line);
		}
	}

	@Override
	public void clear() {
		reportLines.clear();
	}

}

