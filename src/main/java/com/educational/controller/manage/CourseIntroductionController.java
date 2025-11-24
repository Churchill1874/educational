package com.educational.controller.manage;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.educational.common.annotation.AdminLoginCheck;
import com.educational.entity.Admin;
import com.educational.entity.CourseIntroduction;
import com.educational.pojo.req.IdBase;
import com.educational.pojo.req.PageBase;
import com.educational.pojo.req.admin.AdminPage;
import com.educational.pojo.req.courseintroduction.CourseIntroductionAdd;
import com.educational.pojo.req.courseintroduction.CourseIntroductionUpdate;
import com.educational.service.CourseIntroductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(tags = "课程管理")
@RequestMapping("/manage/courseIntroduction")
public class CourseIntroductionController {

    @Autowired
    private CourseIntroductionService courseIntroductionService;

    @PostMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public R<IPage<CourseIntroduction>> page(@RequestBody PageBase dto) {
        return R.ok(courseIntroductionService.page(dto.getPageNum(), dto.getPageSize()));
    }

    @PostMapping("/addCourseIntroduction")
    @ApiOperation(value = "添加课程介绍", notes = "添加课程介绍")
    //@AdminLoginCheck
    public R addCourseIntroduction(@RequestBody @Valid CourseIntroductionAdd req) {
        courseIntroductionService.addCourseIntroduction(BeanUtil.toBean(req, CourseIntroduction.class));
        return R.ok(null);
    }


    @PostMapping("/updateCourseIntroduction")
    @ApiOperation(value = "添加课程介绍", notes = "添加课程介绍")
    //@AdminLoginCheck
    public R updateCourseIntroduction(@RequestBody @Valid CourseIntroductionUpdate req) {
        courseIntroductionService.updateCourseIntroduction(BeanUtil.toBean(req, CourseIntroduction.class));
        return R.ok(null);
    }


    @PostMapping("/deleteById")
    @ApiOperation(value = "删除一个课程", notes = "删除一个课程")
    //@AdminLoginCheck
    public R deleteById(@RequestBody @Valid IdBase req) {
        courseIntroductionService.removeById(req.getId());
        return R.ok(null);
    }




}
