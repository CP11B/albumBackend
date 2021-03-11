package com.project.recordPlayer.service;

import java.util.List;

import com.project.recordPlayer.domain.Album;

public class AlbumServiceList implements AlbumService{
	
	private List<Album> albums;

	public AlbumServiceList(List<Album> albums) {
		super();
		this.albums = albums;
	}

	@Override
	public Album createAlbum(Album album) {
		this.albums.add(album);
		Album added = this.albums.get(this.albums.size() - 1);
		return added;
	}

	@Override
	public List<Album> getAlbum() {
		return this.albums;
	}

	@Override
	public Album getAlbumById(Long id) {
		return this.albums.get(id.intValue());
	}

	@Override
	public boolean removeAlbum(Long id) {
		Album album = this.albums.get(id.intValue());
		this.albums.remove(id.intValue());
		return !this.albums.contains(album);
	}
	
	
	@Override
	public Album updateAlbum(Long id, Album newAlbum) {
		this.removeAlbum(id);
		this.albums.add(id.intValue(), newAlbum);
		return this.albums.get(id.intValue());
	}

	@Override
	public Album getAlbumByTitle(String title) {
		for (Album t : this.albums) {
			if (t.getTitle().equals(title)) {
				return t;
			}
		}
		return null;
	}	
}
