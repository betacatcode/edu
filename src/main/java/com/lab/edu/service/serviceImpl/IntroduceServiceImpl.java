package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.IntroduceMapper;
import com.lab.edu.model.Introduce;
import com.lab.edu.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/8-15:14
 */
@Service
public class IntroduceServiceImpl implements IntroduceService {

    @Autowired
    IntroduceMapper introduceMapper;

    @Override
    public List<Introduce> getAllIntroduce() {
        return introduceMapper.getAllIntroduce();
    }
}
