package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.PolicemanInfo;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface PolicemanInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PolicemanInfo record);

    int insertSelective(PolicemanInfo record);

    PolicemanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PolicemanInfo record);

    int updateByPrimaryKey(PolicemanInfo record);
}