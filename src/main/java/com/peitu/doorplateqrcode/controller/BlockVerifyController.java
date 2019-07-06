package com.peitu.doorplateqrcode.controller;

import com.peitu.commons.image.SlidingBlock;
import com.peitu.doorplateqrcode.dto.SlidingBlockData;
import com.peitu.commons.result.ResultUtil;
import com.peitu.commons.result.ResultCode;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rising
 * @date 2019/6/20
 */
@RestController
public class BlockVerifyController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);

    @RequestMapping(value = "get-pic", method = RequestMethod.GET)
    public Object getPic() {
        SlidingBlockData blockData = SlidingBlock.slidingBlock();
        if (blockData == null) {
            return ResultUtil.setMsg(ResultCode.接口异常);
        }
        return ResultUtil.setMsg(blockData);
    }


}
