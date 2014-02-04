package com.drew;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ReadFromXML
 * 
 * StorageBeanReader: 
 * 	Handles all of the processing of incoming XML reads.
 * 
 * Written by Drew DePriest
 * v1.0 | 2014.February
 *
 */

public class StorageBeanReader {

	private String path;
	private static ArrayList<StorageBean> sbList;
	private static int interval;
	
	StorageBeanReader(String xmlPath, ArrayList<StorageBean> sBeanList){
		path = xmlPath;
		sbList = sBeanList;
		main(path, sbList);
	}
	
	public String getPath(){
		return path;	
	}
	
	public int getInterval(){
		return interval;	
	}

	/**
	 * @param args
	 */
	public static void main(String xmlPath, ArrayList<StorageBean> sbList) {
		
        /*
         * Read in XML values to get all config data
         */
        		
    	try{
    		
        	File configXmlFile = new File(xmlPath + "/music.xml");
        	
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(configXmlFile);
	    	doc.getDocumentElement().normalize();
	    	
	    	// read in the timer delay interval from the root tag
	    	Element spaces = doc.getDocumentElement();
	    	interval = Integer.parseInt(spaces.getAttribute("interval"));
	    	
	    	// Find all the "track" tags
	    	NodeList sl = doc.getElementsByTagName("track");

	    		// Loop through the XML file and process all of the child tags
		    	for (int i=0; i<sl.getLength(); i++) {
		    		
		    		// create storage bean for all config information for each track
	        		StorageBean sBean = new StorageBean();
	        		
		    		Node sNode = sl.item(i);
		    		if (sNode.getNodeType() == Node.ELEMENT_NODE) {	 
		    			
		    			// identify the root "track" tag
		    			Element eElement = (Element) sNode;
		    			
		    			// get the playlist to which this track belongs
		    			Element playlistNode = (Element) eElement.getParentNode();
		    			sBean.setTrackPlaylist(playlistNode.getAttribute("playlist"));
		    			
		    			// Get the text content & add each track's title
		    			NodeList titleList = eElement.getElementsByTagName("title");
		    			Element titleElement = (Element)titleList.item(0);
		    			NodeList textTitleList = titleElement.getChildNodes();
		    			sBean.setTrackTitle(textTitleList.item(0).getNodeValue().trim());
		    			
		    			// Get the text content & add each track's artist
		    			NodeList artistList = eElement.getElementsByTagName("artist");
		    			Element artistElement = (Element)artistList.item(0);
		    			NodeList textArtistList = artistElement.getChildNodes();
		    			sBean.setTrackArtist(textArtistList.item(0).getNodeValue().trim());
		    			
		    			// Get the text content & add each track's duration
		    			NodeList durationList = eElement.getElementsByTagName("duration");
		    			Element durationElement = (Element)durationList.item(0);
		    			NodeList textDurationList = durationElement.getChildNodes();
		    			sBean.setTrackDuration(textDurationList.item(0).getNodeValue().trim());

		    		}
		    		
		    		// add the new bean to the existing list of beans
		    		sbList.add(sBean);
 		
		    	} // close FOR loop (done processing the XML config)
		   
    	} catch (Exception e) {
    		System.out.println("Exception caught while trying to read in XML config.");
    		e.printStackTrace();
        }

	}

}