package com.lab.edu.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lab.edu.model.Msg;
import com.lab.edu.model.User;
import com.lab.edu.service.ChatService;
import com.lab.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruin
 * @date 2019/9/14-10:49
 */
@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

    @RequestMapping("/manage/chat")
    public String toChat(HttpSession session, Model model){

        Integer userId= (Integer) session.getAttribute("userId");
        List<User> senders=chatService.getSendUserList(userId);

        String myImg=userService.getImgById(userId);
        String myName= (String) session.getAttribute("userName");

        model.addAttribute("myImg",myImg);
        model.addAttribute("myName",myName);
        model.addAttribute("senders",senders);

        return "/manage/chat";
    }

    @RequestMapping("/manage/getMsgById")
    public @ResponseBody Map<String,Object> getMsgById(Integer otherId, Integer myId, Model model){
        Map<String,Object> map=new HashMap<>();
        String otherImg,myImg;

        otherImg=userService.getImgById(otherId);
        myImg=userService.getImgById(myId);
        List<Msg> msgs=chatService.getMsgBySendId(otherId,myId);

        map.put("otherImg",otherImg);
        map.put("myImg",myImg);
        map.put("msgs",msgs);
        return map;
    }

    @RequestMapping("/getSendMsg")
    public String getSendMsg(Integer sendId,Integer receiveId,String content){
        chatService.saveSendMsg(sendId,receiveId,content);
        return "redirect:/manage/chat";
    }

    @RequestMapping("/manage/saveMsg")
    public @ResponseBody Map<String,Object> saveMsg(Integer sendId,Integer receiveId,String content,Model model){
        if(content!=null){
            chatService.saveSendMsg(sendId,receiveId,content);
        }
        return getMsgById(receiveId,sendId,model);
    }

    @RequestMapping("/manage/msg")
    public String msg(@RequestParam(defaultValue = "1") Integer pageNum, Model model,HttpSession session){

        Integer myId= (Integer) session.getAttribute("userId");

        Page page= PageHelper.startPage(pageNum,10);
        List<Msg> originMsgs=chatService.getMyMsg(myId);
        PageInfo info=new PageInfo<Msg>(page.getResult());

        List<Map<String,Object>> msgs=new ArrayList<Map<String,Object>>();

        for(int i=0;i<originMsgs.size();i++){
            Msg msg=originMsgs.get(i);
            Integer sendId=msg.getSendId();
            Integer receiveId=msg.getReceiveId();
            String sendName=userService.getNameById(sendId);
            String receiveName=userService.getNameById(receiveId);

            Map<String,Object> map=new HashMap<>();
            map.put("msg",msg);
            map.put("sendName",sendName);
            map.put("receiveName",receiveName);
            msgs.add(map);
        }

        model.addAttribute("msgs",msgs);
        model.addAttribute("info",info);

        return "/manage/msg";
    }

    @RequestMapping("/manage/delMsg")
    public @ResponseBody  Integer deleteMsgById(Integer id){
        return chatService.deleteMsgById(id);
    }

    @RequestMapping("/msg/deleteAllById")
    @ResponseBody
    public Integer deleteAllById(String ids){
        String []id=ids.split(",");
        for(String i:id){
            chatService.deleteMsgById(Integer.valueOf(i));
        }

        return 1;
    }
}
