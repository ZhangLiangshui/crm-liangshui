package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);
    List<User> queryAllUsers();
}
