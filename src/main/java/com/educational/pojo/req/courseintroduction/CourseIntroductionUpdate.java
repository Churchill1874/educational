package com.educational.pojo.req.courseintroduction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
public class CourseIntroductionUpdate extends CourseIntroductionAdd implements Serializable {
    private static final long serialVersionUID = 3927059900788845714L;

    @NotNull(message = "id不能为空")
    @ApiModelProperty("标识id")
    private Long id;

}
