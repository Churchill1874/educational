package com.educational.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educational.common.tools.HttpTools;
import com.educational.common.tools.TokenTools;
import com.educational.entity.CourseIntroduction;
import com.educational.mapper.CourseIntroductionMapper;
import com.educational.mapper.VisitorsMapper;
import com.educational.service.CourseIntroductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CourseIntroductionServiceImpl extends ServiceImpl<CourseIntroductionMapper, CourseIntroduction> implements CourseIntroductionService {

    @Resource
    private VisitorsMapper visitorsMapper;

    @Override
    public IPage<CourseIntroduction> page(Integer pageNo, Integer pageSize) {
        IPage<CourseIntroduction> iPage = new Page<>(pageNo, pageSize);
        QueryWrapper<CourseIntroduction> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(CourseIntroduction::getCreateTime);
        return page(iPage, queryWrapper);
    }

    @Override
    public void addCourseIntroduction(CourseIntroduction courseIntroduction) {
        courseIntroduction.setCreateName(TokenTools.getAdminToken(true).getCreateName());
        courseIntroduction.setCreateTime(LocalDateTime.now());
        save(courseIntroduction);
    }

    @Override
    public void updateCourseIntroduction(CourseIntroduction courseIntroduction) {
        courseIntroduction.setCreateName(TokenTools.getAdminToken(true).getCreateName());
        updateById(courseIntroduction);
    }

    @Override
    @Cacheable(value = "course_introduction")
    public List<CourseIntroduction> getList() {
        return list();
    }


}
