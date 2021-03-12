package com.project.recordPlayer.service;

import static org.assertj.core.api.Assertions.assertThat;

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
		Album newAlbum = new Album("uni of life", "river willow stone", "www.google.com", "www.google.com", 2001);
		Album savedAlbum = new Album(1L, "uni of life", "river willow stone", "www.google.com", "www.google.com", 2001);
		Mockito.when(this.repo.save(newAlbum)).thenReturn(savedAlbum);
		assertThat(this.service.createAlbum(newAlbum)).isEqualTo(savedAlbum);
		Mockito.verify(this.repo, Mockito.times(1)).save(newAlbum);
	}

	@Test
	void testUpdate() {
		Long id = 1L;
		Album newAlbum = new Album("whenben", "catshark and the flavorcrumpets", "www.google.com", "www.google.com", 2000);
		Optional<Album> optionalAlbum = Optional.of(new Album(id, null, null, null, null, 0));
		Album updatedAlbum = new Album(id, newAlbum.getTitle(), newAlbum.getArtist(),
		newAlbum.getImgSrc(), newAlbum.getPlaySrc(), newAlbum.getReleaseYear());
		Mockito.when(this.repo.findById(id)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.save(updatedAlbum)).thenReturn(updatedAlbum);
		assertThat(this.service.updateAlbum(id, newAlbum)).isEqualTo(updatedAlbum);

	}
}
