package com.educational.controller.player;

import com.baomidou.mybatisplus.extension.api.R;
import com.educational.common.tools.HttpTools;
import com.educational.entity.CourseIntroduction;
import com.educational.pojo.resp.verification.VerificationCodeResp;
import com.educational.service.CourseIntroductionService;
import com.educational.service.VisitorsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "课程介绍")
@RequestMapping("/player/courseIntroduction")
public class CourseIntroductionApi {

    @Autowired
    private CourseIntroductionService courseIntroductionService;
    @Autowired
    private VisitorsService visitorsService;

    @PostMapping("/list")
    @ApiOperation(value = "课程介绍", notes = "课程介绍")
    public R<List<CourseIntroduction>> getList() {
        visitorsService.insert();
        return R.ok(courseIntroductionService.getList());
    }

}
