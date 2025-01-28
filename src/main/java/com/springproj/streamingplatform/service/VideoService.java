package com.springproj.streamingplatform.service;

import com.springproj.streamingplatform.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    Video save(Video video, MultipartFile file);
    Video get(String id);
    List<Video> getAll();
    Video getbyTitle(String title);

}
