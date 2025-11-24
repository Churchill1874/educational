package com.educational.pojo.req.courseintroduction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CourseIntroductionAdd implements Serializable {
    private static final long serialVersionUID = 5886535927455187432L;

    @ApiModelProperty("标签 比如 初级到高级/0基础入门/中级到高级")
    private String tag;

    @NotNull(message = "类型不能为空")
    @ApiModelProperty("类型 1前端 2后端 3cocos游戏引擎 4运维 5测试 6全栈")
    private Integer type;

    @NotBlank(message = "课程标题不能为空")
    @ApiModelProperty("课程标题")
    private String title;

    @NotBlank(message = "课程介绍不能为空")
    @ApiModelProperty("课程介绍")
    private String introduction;

    @NotBlank(message = "学习周期不能为空")
    @ApiModelProperty("学习周期时常")
    private String cycle;

    @NotBlank(message = "包括技术不能为空")
    @ApiModelProperty("技术内容 多个内容请用逗号隔开(,) 比如: HTML5,CSS3,Vue.js 展示时去掉逗号")
    private String technicalList;

    @NotBlank(message = "价格不能为空")
    @ApiModelProperty("价格")
    private String amount;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("当前人数")
    private Integer currentPeople;

    @ApiModelProperty("每个班最大人数  页面备注: (人少能照顾所有学生学懂,不落下一人)")
    private Integer number;

    @NotBlank(message = "推广图片不能为空")
    @ApiModelProperty("推广图片地址")
    private String banner;


}
