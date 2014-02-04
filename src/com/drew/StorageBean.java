package com.drew;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ReadFromXML
 * 
 * StorageBean: 
 * 	Place to store information about each track.
 * 
 * Written by Drew DePriest
 * v1.0 | 2014.February
 *
 */

public class StorageBean  implements Serializable {

	public StorageBean(String trackTitle, String trackArtist,
			String trackDuration, String trackPlaylist) {
		super();
		this.trackTitle = trackTitle;
		this.trackArtist = trackArtist;
		this.trackDuration = trackDuration;
		this.trackPlaylist = trackPlaylist;
	}

	/* Spotify playlist components */
	private String trackTitle;		
	private String trackArtist;		
	private String trackDuration;
	private String trackPlaylist;
	
	StorageBean(){}

	public String getTrackTitle() {
		return trackTitle;
	}

	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	public String getTrackArtist() {
		return trackArtist;
	}

	public void setTrackArtist(String trackArtist) {
		this.trackArtist = trackArtist;
	}

	public String getTrackDuration() {
		return trackDuration;
	}

	public void setTrackDuration(String trackDuration) {
		this.trackDuration = trackDuration;
	}

	public String getTrackPlaylist() {
		return trackPlaylist;
	}

	public void setTrackPlaylist(String trackPlaylist) {
		this.trackPlaylist = trackPlaylist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((trackArtist == null) ? 0 : trackArtist.hashCode());
		result = prime * result
				+ ((trackDuration == null) ? 0 : trackDuration.hashCode());
		result = prime * result
				+ ((trackPlaylist == null) ? 0 : trackPlaylist.hashCode());
		result = prime * result
				+ ((trackTitle == null) ? 0 : trackTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StorageBean other = (StorageBean) obj;
		if (trackArtist == null) {
			if (other.trackArtist != null)
				return false;
		} else if (!trackArtist.equals(other.trackArtist))
			return false;
		if (trackDuration == null) {
			if (other.trackDuration != null)
				return false;
		} else if (!trackDuration.equals(other.trackDuration))
			return false;
		if (trackPlaylist == null) {
			if (other.trackPlaylist != null)
				return false;
		} else if (!trackPlaylist.equals(other.trackPlaylist))
			return false;
		if (trackTitle == null) {
			if (other.trackTitle != null)
				return false;
		} else if (!trackTitle.equals(other.trackTitle))
			return false;
		return true;
	}



}
