package com.educational.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.educational.entity.Visitors;
import com.educational.pojo.req.PageBase;
import com.educational.pojo.resp.visitors.VisitorsStatisticsResp;

import java.util.List;

public interface VisitorsService extends IService<Visitors> {

    IPage<Visitors> page(PageBase req);

    void insert(String ip, String address);

    List<Visitors> findByIpLastMinutes(String ip, int minutes);

    VisitorsStatisticsResp statistics();

}
