package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.PicInfo;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface PicInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PicInfo record);

    int insertSelective(PicInfo record);

    PicInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PicInfo record);

    int updateByPrimaryKey(PicInfo record);
}