package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.AdminMapper;
import com.lab.edu.model.Admin;
import com.lab.edu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ruin
 * @date 2019/7/12-10:15
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin getAdminByAccount(String Account) {
        return adminMapper.getAdminByAccount(Account);
    }
}
