package com.educational.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.educational.entity.Admin;
import com.educational.pojo.req.admin.AdminPage;

public interface AdminService extends IService<Admin> {

    IPage<Admin> page(AdminPage dto);

    Admin findByAccount(String account);

}
