package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.Idcode;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface IdcodeMapper {

    int insert(Idcode record);

    int insertSelective(Idcode record);

}