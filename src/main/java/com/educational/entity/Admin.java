package com.educational.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.educational.entity.base.BaseInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("admin")
public class Admin extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 8633321552782077838L;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("登录ip")
    private String loginIp;

    @ApiModelProperty("是否可用")
    private Boolean status;


}
