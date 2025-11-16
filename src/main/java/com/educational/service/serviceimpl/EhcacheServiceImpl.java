package com.educational.service.serviceimpl;

import com.educational.common.constant.CacheKeyConstant;
import com.educational.common.exception.DataException;
import com.educational.common.exception.IpException;
import com.educational.common.tools.GenerateTools;
import com.educational.common.tools.HttpTools;
import com.educational.entity.Admin;
import com.educational.service.EhcacheService;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

/**
 * 该类对ehcache.xml配置文件里面已经配置的缓存容器进行实现获取，方便使用
 */
@Slf4j
@Service
public class EhcacheServiceImpl implements EhcacheService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void checkIp3SecondsClick(Integer limitCount, String remarks) {
        String ip = HttpTools.getIp();
        Cache<String, Integer> cache = lock3SecondCache();
        Integer reqCount = cache.get(ip);

        if (reqCount != null) {
            if (reqCount >= limitCount) {
                //如果ip存在黑名单就更新时间
                throw new IpException(ip);
            } else {
                cache.put(ip, reqCount + 1);
            }
        } else {
            cache.put(ip, 1);
        }
    }

    @Override
    public Cache<String, Admin> getAdminTokenCache() {
        return cacheManager.getCache(CacheKeyConstant.ADMIN_TOKEN, String.class, Admin.class);
    }

    @Override
    public Cache<String, Integer> lock3SecondCache() {
        return cacheManager.getCache(CacheKeyConstant.LOCK_3_SECOND, String.class, Integer.class);
    }

    @Override
    public void verification3SecondsRequest(String key) {
        Integer value = lock3SecondCache().get(key);
        if (value != null) {
            throw new DataException("操作过快,请稍后继续");
        }
        lock3SecondCache().put(key, 1);
    }

    @Override
    public Cache<String, String> verificationCache() {
        return cacheManager.getCache(CacheKeyConstant.VERIFICATION_CODE, String.class, String.class);
    }

    @Override
    public Cache<String, Integer> playerOnlineCount() {
        return cacheManager.getCache(CacheKeyConstant.PLAYER_ONLINE_COUNT, String.class, Integer.class);
    }


    @Override
    public String getVC(String key, Integer limitCount, String remarks) {
        //添加频繁点击校验 3秒内点击超过30次 检查警告日志 如果该ip已经存在警告则拉黑 不存在则新加警告日志
        this.checkIp3SecondsClick(limitCount, remarks);

        //获取验证码
        String codeImageStream = null;
        String code = null;
        try {
            code = GenerateTools.getCaptchaText(5);
            codeImageStream = GenerateTools.getCaptchaImage(code);
        } catch (IOException e) {
            log.error("生成验证码异常:{}", e.getMessage());
            throw new DataException(e.getMessage());
        }

        verificationCache().put(key, code);
        return codeImageStream;
    }

    @Override
    public Set<String> getBlacklistIpSetCache() {
        Cache<String, Set<String>> cache = cacheManager.getCache(CacheKeyConstant.BLACKLIST, String.class, (Class<Set<String>>) (Class<?>) Set.class);
        return cache.get(CacheKeyConstant.BLACKLIST_SET_KEY);
    }

    @Override
    public void setBlacklistIpSetCache(Set<String> blacklistIpSet) {
        cacheManager.getCache(CacheKeyConstant.BLACKLIST, String.class, (Class<Set<String>>) (Class<?>) Set.class)
                .put(CacheKeyConstant.BLACKLIST_SET_KEY, blacklistIpSet);
    }

}
