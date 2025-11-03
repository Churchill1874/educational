package com.educational.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.educational.entity.ImageConfig;
import com.educational.pojo.req.image.ImageConfigPageReq;

public interface ImageConfigService extends IService<ImageConfig> {

    IPage<ImageConfig> queryPage(ImageConfigPageReq po);

    void add(ImageConfig imageConfig);

    void deleteById(Long id);

}
