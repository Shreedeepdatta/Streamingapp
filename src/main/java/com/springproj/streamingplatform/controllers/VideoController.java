package com.springproj.streamingplatform.controllers;

import com.springproj.streamingplatform.entity.Video;
import com.springproj.streamingplatform.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("videos")
@CrossOrigin("*")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @PostMapping("")
    public ResponseEntity<?> addVideo(@RequestPart("file")MultipartFile file,
                                                  @RequestPart("title")String title,
                                                  @RequestPart("description") String desc) {
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(desc);
        video.setVideoId(UUID.randomUUID().toString());
        Video savedvideo = videoService.save(video, file);
        return (savedvideo!=null)?ResponseEntity.status(HttpStatus.CREATED).body(video): ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't upload the video 😔");

    }
    @GetMapping("")
    public ResponseEntity<?> getAllVideos() {
        List<Video> videos = videoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(videos);
    }
}