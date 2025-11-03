package com.educational.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import com.educational.common.exception.TokenException;
import com.educational.common.tools.GenerateTools;
import com.educational.entity.PlayerInfo;
import com.educational.entity.PlayerToken;
import com.educational.pojo.resp.player.PlayerInfoResp;
import com.educational.pojo.resp.player.PlayerTokenResp;
import com.educational.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
public class PlayerHelperImpl implements PlayerHelper {

    @Autowired
    private PlayerTokenService playerTokenService;
    @Autowired
    private PlayerInfoService playerInfoService;
    @Autowired
    private EhcacheService ehcacheService;
    //创建登录token
    public PlayerTokenResp createLoginToken(PlayerInfo playerInfo) {
        String tokenId = GenerateTools.createTokenId();
        PlayerTokenResp playerTokenResp = BeanUtil.toBean(playerInfo, PlayerTokenResp.class);
        playerTokenResp.setId(playerInfo.getId());
        playerTokenResp.setTokenId(tokenId);
        playerTokenResp.setLoginTime(LocalDateTime.now());
        playerTokenResp.setAvatarPath(playerInfo.getAvatarPath());
        playerTokenResp.setLevel(playerInfo.getLevel());
        playerTokenResp.setAddress(playerInfo.getAddress());
        ehcacheService.playerTokenCache().put(tokenId, playerTokenResp);
        log.info("生成 tokenCache:{}", tokenId);

        PlayerInfoResp playerInfoResp = BeanUtil.toBean(playerInfo, PlayerInfoResp.class);

        ehcacheService.playerInfoCache().put(playerInfo.getId().toString(), playerInfoResp);
        return playerTokenResp;
    }

    @Override
    public PlayerTokenResp updateLoginToken(String token, Long playerId) {
        PlayerInfo playerInfo = playerInfoService.getById(playerId);

        PlayerTokenResp playerTokenResp = BeanUtil.toBean(playerInfo, PlayerTokenResp.class);
        playerTokenResp.setId(playerInfo.getId());
        playerTokenResp.setTokenId(token);
        playerTokenResp.setLoginTime(LocalDateTime.now());
        playerTokenResp.setAvatarPath(playerInfo.getAvatarPath());
        playerTokenResp.setLevel(playerInfo.getLevel());
        playerTokenResp.setAddress(playerInfo.getAddress());
        ehcacheService.playerTokenCache().put(token, playerTokenResp);
        log.info("更新tokenCache:{}", token);

        PlayerInfoResp playerInfoResp = BeanUtil.toBean(playerInfo, PlayerInfoResp.class);

        ehcacheService.playerInfoCache().put(playerInfo.getId().toString(), playerInfoResp);
        return playerTokenResp;
    }

    @Override
    public PlayerTokenResp checkAndUpdate(String token) {
        PlayerToken playerToken = playerTokenService.findByTokenId(token);
        if (playerToken == null) {
            throw new TokenException();
        }

        //如果已经超过了令牌的过期时间
        if (LocalDateTime.now().isAfter(playerToken.getExpirationTime())) {
            throw new TokenException();
        }

        //更新磁盘存储的token过期时间 过期时间在ehcache配置里面
        playerTokenService.updateToken(playerToken);

        //更新缓存时间
        return updateLoginToken(token, playerToken.getPlayerId());
    }
}
