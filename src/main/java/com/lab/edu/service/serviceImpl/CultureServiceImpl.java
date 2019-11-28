package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.CultureMapper;
import com.lab.edu.model.Culture;
import com.lab.edu.service.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/8-15:51
 */
@Service
public class CultureServiceImpl implements CultureService {

    @Autowired
    CultureMapper cultureMapper;
    @Override
    public List<Culture> getAllCulture() {
        return cultureMapper.getAllCulture();
    }

    @Override
    public Culture getCultureById(Integer id) {
        return cultureMapper.getCultureById(id);
    }
}
