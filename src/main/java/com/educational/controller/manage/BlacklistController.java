package com.educational.controller.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.educational.common.annotation.AdminLoginCheck;
import com.educational.common.annotation.SuperAdminLoginCheck;
import com.educational.entity.Blacklist;
import com.educational.pojo.req.IdListBase;
import com.educational.pojo.req.blacklist.BlacklistAddReq;
import com.educational.pojo.req.blacklist.BlacklistPageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "黑名单")
@RequestMapping("/manage/blacklist")
public class BlacklistController {


}
