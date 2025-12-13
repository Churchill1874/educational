package com.educational.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.educational.entity.Blacklist;
import com.educational.pojo.req.blacklist.BlacklistPageReq;

import java.util.Set;

public interface BlacklistService extends IService<Blacklist> {

    IPage<Blacklist> queryPage(BlacklistPageReq req);

    Blacklist findByIp(String ip);

    Set<String> getBlacklistIpAll();

    void checkIp(String ip);

}
