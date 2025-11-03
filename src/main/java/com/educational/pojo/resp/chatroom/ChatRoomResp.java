package com.educational.pojo.resp.chatroom;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class ChatRoomResp extends ChatRoom implements Serializable {
    private static final long serialVersionUID = 2230140262005849196L;

    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("头像路径")
    private String avatarPath;
    @ApiModelProperty("等级")
    private LevelEnum level;

}
