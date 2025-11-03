package com.educational.pojo.req.admin;

import com.educational.pojo.req.IdBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AdministratorsUpdate extends IdBase implements Serializable {
    private static final long serialVersionUID = -7884649139919074869L;

    @NotNull(message = "角色不能为空")
    @ApiModelProperty(value = "角色", required = true)
    private ManageRoleEnum role;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
