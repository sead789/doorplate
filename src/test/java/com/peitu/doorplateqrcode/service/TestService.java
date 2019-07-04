package com.peitu.doorplateqrcode.service;

import com.peitu.doorplateqrcode.entity.Test;
import com.peitu.doorplateqrcode.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public int insert(Test test){
        return testMapper.insert(test);
    }

}
