package com.peitu.doorplateqrcode.mapper;

import com.peitu.doorplateqrcode.entity.AdministrativeCode;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface AdministrativeCodeMapper {

    int insert(AdministrativeCode record);

    AdministrativeCode selectByPrimaryKey(Integer id);

}