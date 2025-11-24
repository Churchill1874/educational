package com.educational.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.educational.entity.CourseIntroduction;

import java.util.List;

public interface CourseIntroductionService extends IService<CourseIntroduction> {

    IPage<CourseIntroduction> page(Integer pageNo, Integer pageSize);

    void addCourseIntroduction(CourseIntroduction courseIntroduction);

    void updateCourseIntroduction(CourseIntroduction courseIntroduction);

    List<CourseIntroduction> getList();
}
