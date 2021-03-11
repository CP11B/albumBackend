package com.project.recordPlayer.service;

import java.util.List;

import com.project.recordPlayer.domain.Album;

public interface AlbumService {
	
	Album createAlbum(Album album);

	List<Album> getAlbum();

	Album getAlbumById(Long id);
	boolean removeAlbum(Long id);
	Album updateAlbum(Long id, Album newAlbum);
	Album getAlbumByTitle(String title);
}
