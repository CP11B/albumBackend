package com.project.recordPlayer.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.recordPlayer.domain.Album;
import com.project.recordPlayer.service.AlbumService;

@CrossOrigin
@RestController
public class AlbumController {

	private AlbumService service;
	
	public AlbumController(AlbumService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/album")
	public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
		System.out.println(album);
		return new ResponseEntity<Album>(this.service.createAlbum(album), HttpStatus.CREATED);
	}

	@GetMapping("/albums")
	public ResponseEntity<List<Album>> getAlbum() {
		return ResponseEntity.ok(this.service.getAlbum());
	}

	@GetMapping("/album/{id}")
	public Album getAlbumById(@PathVariable Long id) {
		return this.service.getAlbumById(id);
	}
	
	@GetMapping("/album/title/{title}")
	public Album getAlbumByName(@PathVariable String title) {
		return this.service.getAlbumByTitle(title);
	}

	@DeleteMapping("/album/{id}")
	public boolean removeAlbum(@PathVariable Long id) {
		return this.service.removeAlbum(id);
	}
	
	@PutMapping("/album/{id}")
	public Album updateAlbum(@PathVariable Long id, @RequestBody Album newAlbum) {
		return this.service.updateAlbum(id, newAlbum);
	}
}