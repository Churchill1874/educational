package com.educational.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class BlacklistCheckAspect {

    @Autowired
    //private BlacklistService blacklistService;

    @Pointcut("execution(* com.educational.controller.manage.*.*(..))")
    public void blacklistPointCut() {
    }

    @Before("blacklistPointCut()")
    public void beforeExecute() {
/*        String ip = HttpTools.getIp();
        Set<String> blacklistIpSet = blacklistService.getIpSet();
        if (blacklistIpSet.contains(ip)) {
            log.error("黑名单ip访问异常:{}", ip);
            throw new IpException(ip);
        }*/
    }

}
