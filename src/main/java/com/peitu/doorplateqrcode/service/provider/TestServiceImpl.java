package com.peitu.doorplateqrcode.service.provider;

import com.peitu.doorplateqrcode.entity.Test;
import com.peitu.doorplateqrcode.mapper.TestMapper;
import com.peitu.doorplateqrcode.service.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public int insert(Test test) {
        return testMapper.insert(test);
    }

}
