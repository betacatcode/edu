package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.VideoMapper;
import com.lab.edu.model.Video;
import com.lab.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2019/8/26-15:12
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Video> getAllVideo() {
        return videoMapper.getAllVideo();
    }

    @Override
    public List<Video> searchByTitle(String title) {
        return videoMapper.searchByTitle(title);
    }
}
