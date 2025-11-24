package com.educational.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.educational.common.tools.TokenTools;
import com.educational.entity.CourseIntroduction;
import com.educational.mapper.CourseIntroductionMapper;
import com.educational.service.CourseIntroductionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseIntroductionServiceImpl extends ServiceImpl<CourseIntroductionMapper, CourseIntroduction> implements CourseIntroductionService {

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
    //todo 日后做缓存
    public List<CourseIntroduction> getList() {
        return list();
    }


}
