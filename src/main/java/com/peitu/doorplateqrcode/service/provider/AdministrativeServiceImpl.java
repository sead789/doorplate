package com.peitu.doorplateqrcode.service.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peitu.commons.api.DemoForArea;
import com.peitu.commons.api.Response;
import com.peitu.commons.result.ResultCode;
import com.peitu.commons.result.ResultUtil;
import com.peitu.doorplateqrcode.entity.AdministrativeCode;
import com.peitu.doorplateqrcode.mapper.AdministrativeCodeMapper;
import com.peitu.doorplateqrcode.service.api.AdministrativeService;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author Rising
 * @date 2019/6/12
 */
@Service
@Transactional
public class AdministrativeServiceImpl implements AdministrativeService {

    @Autowired
    AdministrativeCodeMapper administrativeCodeMapper;

    @Override
    public Object insert(int parentId) throws IOException{
        String content = DemoForArea.getAdByParentId(parentId);
        ObjectMapper mapper = new ObjectMapper();
        Response res = null;
        try {
            res = mapper.readValue(content, Response.class);
        } catch (IOException e) {
            throw e;
        }
        for (AdministrativeCode code : res.getData()) {
            administrativeCodeMapper.insert(code);
        }
        return ResultUtil.setMsg(ResultCode.成功);
    }
}
