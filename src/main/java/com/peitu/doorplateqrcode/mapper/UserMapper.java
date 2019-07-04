package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.User;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}