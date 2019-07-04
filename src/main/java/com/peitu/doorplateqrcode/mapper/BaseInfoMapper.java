package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.BaseInfo;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface BaseInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BaseInfo record);

    int insertSelective(BaseInfo record);

    BaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseInfo record);

    int updateByPrimaryKey(BaseInfo record);
}