package com.lab.edu.service.serviceImpl;

import com.lab.edu.mapper.ChatMapper;
import com.lab.edu.model.Msg;
import com.lab.edu.model.User;
import com.lab.edu.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ruin
 * @date 2019/9/14-10:58
 */
@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatMapper chatMapper;

    @Override
    public List<User> getSendUserList(Integer receiveId) {
        return chatMapper.getSendUserList(receiveId);
    }

    @Override
    public List<Msg> getMsgBySendId(Integer otherId, Integer myId) {
        return chatMapper.getMsgBySendId(otherId,myId);
    }

    @Override
    public Integer saveSendMsg(Integer sendId, Integer receiveId, String content) {
        Date time= new Date(new Date().getTime());
        chatMapper.saveSendMsg(sendId,receiveId,content,time);
        return 1;
    }

    @Override
    public List<Msg> getMyMsg(Integer myId) {
        return chatMapper.getMyMsg(myId);
    }

    @Override
    public Integer deleteMsgById(Integer id) {
        return chatMapper.deleteMsgById(id);
    }
}
