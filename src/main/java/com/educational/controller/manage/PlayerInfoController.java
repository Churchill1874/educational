package com.educational.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.educational.common.annotation.AdminLoginCheck;
import com.educational.common.constant.enums.UserStatusEnum;
import com.educational.common.exception.DataException;
import com.educational.common.tools.CheckReqTools;
import com.educational.common.tools.CodeTools;
import com.educational.common.tools.GenerateTools;
import com.educational.common.tools.TokenTools;
import com.educational.entity.PlayerInfo;
import com.educational.pojo.req.IdBase;
import com.educational.pojo.req.PageBase;
import com.educational.pojo.req.UpdateStatusBase;
import com.educational.pojo.req.player.PlayerInfoAddReq;
import com.educational.pojo.req.player.PlayerInfoPageReq;
import com.educational.pojo.req.player.PlayerInfoUpdateReq;
import com.educational.pojo.resp.player.PlayerTokenResp;
import com.educational.service.EhcacheService;
import com.educational.service.PlayerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "玩家")
@RequestMapping("/manage/playerInfo")
public class PlayerInfoController {

    @Autowired
    private EhcacheService ehcacheService;
    @Autowired
    private PlayerInfoService playerInfoService;

    @PostMapping("/onlinePlayerList")
    @ApiOperation(value = "在线玩家信息", notes = "在线玩家信息")
    public R<List<PlayerTokenResp>> onlinePlayerList() {
        List<PlayerTokenResp> list = new ArrayList<>();
        Iterator<Cache.Entry<String, PlayerTokenResp>> iterator = ehcacheService.onlineCountCache().iterator();

        while (iterator.hasNext()) {
            Cache.Entry<String, PlayerTokenResp> entry = iterator.next();
            list.add(entry.getValue());
        }

        return R.ok(list);
    }

    @PostMapping("/queryPage")
    @ApiOperation(value = "分页", notes = "分页")
    public R<IPage<PlayerInfo>> queryPage(@RequestBody @Valid PlayerInfoPageReq req) {
        PlayerInfo playerInfo = BeanUtil.toBean(req, PlayerInfo.class);
        PageBase pageBase = BeanUtil.toBean(req, PageBase.class);
        IPage<PlayerInfo> iPage = playerInfoService.queryPage(playerInfo, pageBase);
        return R.ok(iPage);
    }

    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public R updateStatus(@RequestBody @Valid UpdateStatusBase req) {
        playerInfoService.updateStatus(req.getId(), req.getStatus());
        return R.ok(null);
    }


    @PostMapping("/delete")
    @AdminLoginCheck
    @ApiOperation(value = "删除", notes = "删除")
    public R delete(@RequestBody @Valid IdBase req) {
        playerInfoService.removeById(req.getId());
        return R.ok(null);
    }

}
