package com.project.recordPlayer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.recordPlayer.domain.Album;


@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {
	Album findByTitle(String title);
}

