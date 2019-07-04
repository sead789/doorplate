package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.DoorplateInfo;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface DoorplateInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DoorplateInfo record);

    int insertSelective(DoorplateInfo record);

    DoorplateInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DoorplateInfo record);

    int updateByPrimaryKey(DoorplateInfo record);
}