package com.lab.edu.service;

import com.lab.edu.model.Culture;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/8-15:50
 */
public interface CultureService {
    public List<Culture>getAllCulture();

    public Culture getCultureById(Integer id);
}
