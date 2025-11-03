package com.educational.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.educational.entity.PlayerToken;

public interface PlayerTokenService extends IService<PlayerToken> {

    void updateToken(PlayerToken playerToken);

    void addOrUpdate(Long playerId, String token);

    PlayerToken findByTokenId(String token);

    PlayerToken findByPlayerId(Long playerId);

}
