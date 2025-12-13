package com.educational.common.aspect;

import com.educational.common.exception.IpException;
import com.educational.common.tools.HttpTools;
import com.educational.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Aspect
@Component
public class BlacklistCheckAspect {

    @Autowired
    private BlacklistService blacklistService;

    @Pointcut("execution(* com.educational.controller.manage.*.*(..))")
    public void blacklistPointCut() {
    }

    @Before("blacklistPointCut()")
    public void beforeExecute() {
        String ip = HttpTools.getIp();
        Set<String> blacklistIpSet = blacklistService.getBlacklistIpAll();
        if (blacklistIpSet.contains(ip)) {
            log.error("黑名单ip访问了:{}", ip);
            throw new IpException(ip);
        } else {
            blacklistService.checkIp(ip);
        }
    }

}
