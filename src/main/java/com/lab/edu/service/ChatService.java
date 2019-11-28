package com.lab.edu.service;

import com.lab.edu.model.Msg;
import com.lab.edu.model.User;

import java.util.List;

/**
 * @author ruin
 * @date 2019/9/14-10:58
 */
public interface ChatService {

    public List<User> getSendUserList(Integer receiveId);

    public List<Msg>getMsgBySendId(Integer otherId, Integer myId);

    public Integer saveSendMsg(Integer sendId,Integer receiveId,String content);

    public List<Msg> getMyMsg(Integer myId);

    public Integer deleteMsgById(Integer id);
}
