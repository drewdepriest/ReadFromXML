package com.drew;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ReadFromXML
 * 
 * RFXListener: 
 * 	Main servlet listener for the ReadFromXML web app.
 *  Calls appropriate classes to read in XML, prints out bean info as a test.
 * 
 * Written by Drew DePriest
 * v1.0 | 2014.February
 *
 */

public class RFXListener implements ServletContextListener {

		/*
		 * Constants	
		 */	
		private Thread appThread = null;	// new instance of thread
		final ArrayList<StorageBean> sBeanList = new ArrayList<StorageBean>();	// dynamic list of StorageBeans
		private final static Logger logger = Logger.getLogger(RFXListener.class .getName()); 
		StorageBeanReader sbr;	
		int appDelay = 20;	// Set number of seconds between execution cycles
		
		/**
	     * @see ServletContextListener#contextInitialized(ServletContextEvent)
	     * This is where the web app recognizes the server is running. 
	     * Once it's alive, call the data sort/write functions.
	     * 
	     */
	    public void contextInitialized(ServletContextEvent sce) {

	    	// Find the realPath to the configs folder
	    	final String xmlPath = sce.getServletContext().getRealPath("/WEB-INF/configs");
	    	
	    	try {
	        				
				// When launching app, log the successful start with timestamp
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
				String formattedDate = sdf.format(date);
				logger.log(Level.INFO, "ReadFromXML app started at " + formattedDate);
				
		        // Read in the local XML file and store all pertinent information in a bean
		        sbr = new StorageBeanReader(xmlPath,sBeanList);
		        
		        // Get the execution cycle delay (every 'appDelay' seconds, run the servlet)
		        appDelay = sbr.getInterval();
		        
			} catch (SecurityException e1) {
				System.out.println("Error: " + e1.getMessage());
			} 
		    	  
	        // And finally, start running the read/sort/write routines
	    	this.appThread = new Thread(new Runnable() {
	  	     public void run() {
	  	    	
	  	        while (true) {
	  	            try {
	  	            	
	  	            	// Delay the thread from running (time delay value stored in local config XML)
	  	            	goToSleep();
	         	    	 
	  	            	// *** TESTING: used to print all values to the console
	  	            	beanLoopTest();
	      	    	  
	  	            }catch (Exception e)
	  	            {
	  	            	logger.log(Level.WARNING, "An exception occurred while running the ReadFromXML thread", e);
	  	            }
	  	          }
	  	      }
	    	}
	    	      , "ReadFromXML thread");

	        this.appThread.setDaemon(true);
	        this.appThread.start();
	    }

		/**
	     * Wait for the specified amount of time before trying to run the operation again
		 * @throws InterruptedException 
	     */
		public void goToSleep() throws InterruptedException {
			
			try {
				Thread.sleep(appDelay*1000);
			} catch (InterruptedException e) {
				logger.log(Level.WARNING, "An exception occurred while trying to go to sleep.", e);
			}
			
		}
	    
		/**
		 * beanLoopTest()
		 * 	- Loop through all of the beans
		 *  - Print pertinent information about each bean
		 *  - Only used for testing/troubleshooting purposes
		 *  - Pi is exactly 3
		 */
		 public void beanLoopTest() {
			 
			 for(int i=0; i<sBeanList.size(); i++) {
				 if(i==0){
					 System.out.println("There are " + sBeanList.size() + " tracks in playlist " + sBeanList.get(i).getTrackPlaylist());
				 }
				 System.out.println("Track: " + sBeanList.get(i).getTrackTitle());
				 System.out.println("Artist: " + sBeanList.get(i).getTrackArtist());
				 System.out.println("Duration: " + sBeanList.get(i).getTrackDuration());
				 
				 System.out.println("*******");
		    }
			 
		 }
	    
	    
		/**
		 * Kill the servlet whenever server shuts down
	     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	     */
	    public void contextDestroyed(ServletContextEvent sce) {
	    	
	    	System.out.println("Shutting down the ReadFromXML servlet...");

	    	if ((this.appThread != null) && (this.appThread.isAlive())) {
	        	this.appThread.interrupt();
	        	this.appThread = null;
	    	}

	    }

}
