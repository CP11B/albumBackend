package com.project.recordPlayer.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.project.recordPlayer.domain.Album;
import com.project.recordPlayer.repos.AlbumRepo;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumProjectDBUnitTest {
	@Autowired
	private AlbumServiceDB service;

	@MockBean
	private AlbumRepo repo;

	@Test
	void testCreate() {
		
		Album newAlbum = new Album("uni of life", "river willow stone", "askjeeves", "googly", 2000);
		Album savedAlbum = new Album(1L, "uni of life", "river willow stone", "askjeeves", "googly", 2000);
		
		Mockito.when(this.repo.save(newAlbum)).thenReturn(savedAlbum);
		
		assertThat(this.service.createAlbum(newAlbum)).isEqualTo(savedAlbum);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newAlbum);
	}

	@Test
	void testUpdate() {
		
		Long id = 1L;
		Album newAlbum = new Album("whenben", "catshark and the flavorcrumpets", "askjeeves", "googly", 2000);
		
		Optional<Album> optionalAlbum = Optional.of(new Album(id, null, null, null, null, 0));
		Album updatedAlbum = new Album(id, newAlbum.getTitle(), newAlbum.getArtist(),
		newAlbum.getImgSrc(), newAlbum.getPlaySrc(), newAlbum.getReleaseYear());
		
		Mockito.when(this.repo.findById(id)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.save(updatedAlbum)).thenReturn(updatedAlbum);
		
		assertThat(this.service.updateAlbum(id, newAlbum)).isEqualTo(updatedAlbum);
	}
	
	@Test
	void testDelete() {
		
		Long id = 1L;
		Mockito.when(this.repo.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.removeAlbum(id)).isTrue();
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);

	}
	
	@Test
	void testRead() {
		// Create a new piece of test data
		Album newAlbum = new Album("uni of life", "river willow stone", "askjeeves", "googly", 2000);
		
		// Add test data to a list
		List<Album> albums = new ArrayList<>();
		albums.add(newAlbum);

		// Return list
		Mockito.when(this.repo.findAll()).thenReturn(albums);

		// Check returned list is the same as the test data
		assertThat(this.service.getAlbum()).isEqualTo(albums);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();		
	}
}
