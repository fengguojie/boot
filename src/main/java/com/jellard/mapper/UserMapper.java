package com.jellard.mapper;

import java.util.List;

import com.jellard.model.User;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {
    public User findUserInfo(Integer id);
    
    public List<User> findAll();
}
