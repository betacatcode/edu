package com.lab.edu.utils;

import com.lab.edu.model.Comment;
import com.lab.edu.model.CommentInfo;
import com.lab.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruin
 * @date 2019/7/16-15:17
 */

@Component
public class CommentUtil {
    @Autowired
    UserService userService;

    public List<CommentInfo>commentToCommentInfo(List<Comment>comments){
        List<CommentInfo>commentInfos=new ArrayList<>();

        for(int i=0;i<comments.size();i++){
            Comment thisComment=comments.get(i);
            CommentInfo commentInfo=new CommentInfo(thisComment);
            commentInfo.setUserName(userService.getNameById(thisComment.getUserId()));
            commentInfo.setImg(userService.getImgById(thisComment.getUserId()));
            commentInfos.add(commentInfo);
        }

        return commentInfos;
    }

}
