package com.peitu.doorplateqrcode.service.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peitu.commons.api.DemoForArea;
import com.peitu.commons.api.Response;
import com.peitu.doorplateqrcode.entity.AdministrativeCode;
import com.peitu.doorplateqrcode.mapper.AdministrativeCodeMapper;
import com.peitu.commons.result.ResultUtil;
import com.peitu.commons.result.ResultCode;
import com.peitu.doorplateqrcode.service.api.AdministrativeService;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Rising
 * @date 2019/6/12
 */
@Service
public class AdministrativeServiceImpl implements AdministrativeService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);

    @Autowired
    AdministrativeCodeMapper administrativeCodeMapper;

    @Override
    public Object insert(int parentId) {

        String content = DemoForArea.getAdByParentId(parentId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Response res = mapper.readValue(content, Response.class);
            for (AdministrativeCode code : res.getData()) {
                System.out.println("**" + code);
                administrativeCodeMapper.insert(code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultUtil.setMsg(ResultCode.成功);
    }
}
