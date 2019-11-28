package com.lab.edu.service;

import com.lab.edu.model.Video;

import java.util.List;

/**
 * @author ruin
 * @date 2019/8/26-15:11
 */
public interface VideoService {

    public List<Video>getAllVideo();

    public List<Video>searchByTitle(String title);
}
