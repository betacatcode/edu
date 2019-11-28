package com.lab.edu.mapper;

import com.lab.edu.model.Msg;
import com.lab.edu.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ruin
 * @date 2019/9/14-10:52
 */

@Mapper
@Repository
public interface ChatMapper {

    @Select("SELECT * from tb_message where sendid=#{myId} or receiveid=#{myId}")
    public List<Msg> getMyMsg(Integer myId);

    @Select("SELECT * from tb_user where userId IN" +
            "(select sendid from tb_message where receiveid=#{receiveId} " +
            "UNION select receiveid from tb_message where sendid=#{receiveId})")
    public List<User> getSendUserList(Integer receiveId);

    @Select("SELECT * from tb_message where (sendid=#{otherId} and receiveId=#{myId})" +
            "or(sendid=#{myId} and receiveId=#{otherId})")
    public List<Msg>getMsgBySendId(@Param("otherId") Integer otherId,@Param("myId") Integer myId);

    @Insert("INSERT into tb_message (sendId,receiveId,content,sendTime) " +
            "values(#{sendId},#{receiveId},#{content},#{sendTime})")
    public Integer saveSendMsg(@Param("sendId") Integer sendId,@Param("receiveId") Integer receiveId,
                               @Param("content")String content,@Param("sendTime") Date sendTime);

    @Delete("delete from tb_message where Id=#{id}")
    public Integer deleteMsgById(Integer id);

}
