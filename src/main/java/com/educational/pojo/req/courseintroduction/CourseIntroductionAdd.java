package com.educational.pojo.req.courseintroduction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CourseIntroductionAdd implements Serializable {
    private static final long serialVersionUID = 5886535927455187432L;

    @ApiModelProperty("技术课程类型")
    private String category;

    @ApiModelProperty("标签 比如 初级到高级/0基础入门/中级到高级")
    private String level;

    @ApiModelProperty("技术课程类型")
    private String backend;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程介绍")
    private String description;

    @ApiModelProperty("学习周期时常")
    private String duration;

    @ApiModelProperty("技术内容 多个内容请用逗号隔开(,) 比如: HTML5,CSS3,Vue.js 展示时去掉逗号")
    private String technologies;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("当前人数")
    private Integer currentPeople;

    @ApiModelProperty("每个班风顶人数 (人少能照顾所有学生学懂,不落下一人)")
    private Integer number;

    @ApiModelProperty("推广图片地址")
    private String image;
}
