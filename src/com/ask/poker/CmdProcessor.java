package com.ask.poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ask.poker.report.ReportService;
import com.ask.poker.report.SimpleReportServiceImpl;
import com.ask.poker.service.PokerService;
import com.ask.poker.service.PokerServiceImpl;

/*
 * This class is the entry point for using the poker service.
 * This will prompt the user for an input file and display the
 * results to the console.  
 */
public class CmdProcessor {
	
	//FIXME: want to instantiate these differently, 
	// something like dependency injection instead
	static PokerService pokerService = new PokerServiceImpl();
	static ReportService reportService = new SimpleReportServiceImpl();

	public static void main(String[] args) { 
		// get the file name from the user
		BufferedReader in = 
			new BufferedReader(new InputStreamReader(System.in)); 
		String fileName = null; 
		while (fileName == null || fileName.isEmpty()){
			System.out.print("Enter the file name with poker hands: "); 
			try {
				fileName = in.readLine();}
			catch(Exception e) {
				System.out.println("Unable to recieve your file name");
			}
		}
		
		// make the file is okay and process it
		if (!checkInputFile(fileName)){
			System.out.println("Unable to process your file");
		}
		readInputFile(fileName);
	}
	
	private static boolean checkInputFile(String fileName) {
		
		//FIXME: do some checking here to make sure the file exists
		// and things like that
		
		return true;
	}
	
	private static void readInputFile(String fileName){
		BufferedReader br = null;
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((currentLine = br.readLine()) != null) {
				BestHand bestHand = null;
				try {
					bestHand = pokerService.findBestHand(currentLine);
					reportService.addBestHand(bestHand);
				} catch (PokerException pe) {
					System.out.println("Invalid poker hand, " + currentLine +
							" because: " + pe.getMessage());
				}
				
			}
			reportService.generateReport();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	

}

