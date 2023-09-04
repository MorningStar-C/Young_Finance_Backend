package com.youngfinance.front.controller;


import com.youngfinance.api.model.User;
import com.youngfinance.common.enums.RCode;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户功能")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController{

    // 手机号是否存在
    @ApiOperation(value = "手机号是否注册过", notes = "在注册功能中，判断手机号是否可以注册")
    @GetMapping("/phone/exists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult result = new RespResult();
        result.setRCode(RCode.PHONE_EXISTS);
        //1. 做校验
        if(CommonUtil.checkPhone(phone)) {
            User user = userService.queryByPhone(phone);
            if(user == null) {
                // 可以注册
                result = RespResult.ok();
            }
            // TODO: 将手机号在Redis中进行缓存
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }
}
