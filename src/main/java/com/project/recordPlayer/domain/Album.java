package com.project.recordPlayer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String artist;
	private String imgSrc;
	private String playSrc;
	private int releaseYear;
	
	public Album(Long id, String title, String artist, String imgSrc, String playSrc, int releaseYear) {
		super();
		this.id = id;
		this.setTitle(title);
		this.setArtist(artist);
		this.setImgSrc(imgSrc);
		this.setPlaySrc(playSrc);
		this.setReleaseYear(releaseYear);
	}
	
	public Album(String title, String artist, String imgSrc, String playSrc, int releaseYear) {
		super();
		this.setTitle(title);
		this.setArtist(artist);
		this.setImgSrc(imgSrc);
		this.setPlaySrc(playSrc);
		this.setReleaseYear(releaseYear);
	}
		
	public Album() {
		super();
	}
	
	// getters and setters for each thing in album

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getPlaySrc() {
		return playSrc;
	}

	public void setPlaySrc(String playSrc) {
		this.playSrc = playSrc;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + releaseYear;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
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
		Album other = (Album) obj;
		if (releaseYear != other.releaseYear)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		return true;
	}
	
}
