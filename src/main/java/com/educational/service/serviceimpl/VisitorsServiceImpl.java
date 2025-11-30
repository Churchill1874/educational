package com.educational.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educational.common.tools.HttpTools;
import com.educational.entity.Visitors;
import com.educational.mapper.VisitorsMapper;
import com.educational.pojo.req.PageBase;
import com.educational.service.VisitorsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorsServiceImpl extends ServiceImpl<VisitorsMapper, Visitors> implements VisitorsService {

    @Override
    public IPage<Visitors> page(PageBase req) {
        IPage<Visitors> iPage = new Page<>(req.getPageNum(), req.getPageSize());

        QueryWrapper<Visitors> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(Visitors::getCreateTime);

        return page(iPage, queryWrapper);
    }

    @Override
    public void insert() {
        String ip = HttpTools.getIp();

        //最近十分钟之内如果已经访问过的ip 则不再次记录
        if(!CollectionUtils.isEmpty(findByIpLastMinutes(ip,10))){
            return;
        }

        Visitors visitors = new Visitors();
        visitors.setCreateTime(LocalDateTime.now());
        visitors.setCreateName("访客");
        visitors.setIp(ip);
        visitors.setAddress(HttpTools.getAddress());
        save(visitors);
    }

    @Override
    public List<Visitors> findByIpLastMinutes(String ip, int minutes) {
        QueryWrapper<Visitors> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(Visitors::getIp, ip)
                .ge(Visitors::getCreateTime, LocalDateTime.now().minusMinutes(minutes));
        return list(queryWrapper);
    }

}
