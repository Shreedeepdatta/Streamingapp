package com.springproj.streamingplatform.repo;

import com.springproj.streamingplatform.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findById(int id);
    List<Video> findByTitleContaining(String title);
}
