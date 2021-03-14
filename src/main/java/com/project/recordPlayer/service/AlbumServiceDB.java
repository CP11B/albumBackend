package com.project.recordPlayer.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.project.recordPlayer.domain.Album;
import com.project.recordPlayer.repos.AlbumRepo;

@Service
public class AlbumServiceDB implements AlbumService{

	private AlbumRepo repo;
	
	public AlbumServiceDB(AlbumRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Album createAlbum(Album album) {
		Album saved = this.repo.save(album);
		return saved;
	}

	@Override
	public List<Album> getAlbum() {
		return this.repo.findAll();
	}

	@Override
	public Album getAlbumById(Long id) {
		Optional<Album> optAlbum = this.repo.findById(id);
		return optAlbum.get();
	}
	
	@Override
	public Album getAlbumByTitle(String title) {
		return this.repo.findByTitle(title);
	}

	public boolean removeAlbum(Long id) {
		this.repo.deleteById(id);
		//System.out.println(this.repo.existsById(id));
		return this.repo.existsById(id);
	}

	@Override
	public Album updateAlbum(Long id, Album newAlbum) {
		Album existing = this.getAlbumById(id);

		existing.setTitle(newAlbum.getTitle());
		existing.setArtist(newAlbum.getArtist());
		existing.setImgSrc(newAlbum.getImgSrc());
		existing.setPlaySrc(newAlbum.getPlaySrc());
		existing.setReleaseYear(newAlbum.getReleaseYear());
		Album updated = this.repo.save(existing);
		return updated;
	}
}
