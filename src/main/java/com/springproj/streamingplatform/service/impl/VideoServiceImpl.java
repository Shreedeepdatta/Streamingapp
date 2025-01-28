package com.springproj.streamingplatform.service.impl;

import com.springproj.streamingplatform.entity.Video;
import com.springproj.streamingplatform.repo.VideoRepo;
import com.springproj.streamingplatform.service.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Value("${files.video}")
    String DIR;

    @Autowired
    private VideoRepo videoRepo;

    @PostConstruct
    public void init(){
        File file=new File(DIR);
        if(!file.exists()){
            file.mkdir();
            System.out.println("Directory created");
        }
    }
    @Override
    public Video save(Video video, MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();


            assert filename != null;
            String cleanfilename= StringUtils.cleanPath(filename);
            String cleanpath=StringUtils.cleanPath(DIR);


            Path path=Paths.get(cleanpath,cleanfilename);
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            video.setContentType(contentType);
            video.setFilepath(path.toString());

            videoRepo.save(video);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return video;
    }

    @Override
    public Video get(String id) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return videoRepo.findAll();
    }

    @Override
    public Video getbyTitle(String title) {
        return null;
    }
}
