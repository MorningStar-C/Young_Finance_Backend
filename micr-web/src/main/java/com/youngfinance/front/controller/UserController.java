package com.youngfinance.front.controller;


import com.youngfinance.api.model.User;
import com.youngfinance.api.service.RealNameService;
import com.youngfinance.api.service.SmsService;
import com.youngfinance.common.enums.RCode;
import com.youngfinance.common.util.CommonUtil;
import com.youngfinance.common.util.JwtUtil;
import com.youngfinance.front.view.RespResult;
import com.youngfinance.front.vo.RealNameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户功能")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController{

    @Resource
    private JwtUtil jwtUtil;

    @DubboReference(interfaceClass = RealNameService.class, version = "1.0")
    private RealNameService realNameService;

    // 手机号注册用户
    @ApiOperation(value = "手机号注册用户")
    @PostMapping("/register")
    public RespResult userRegister(@RequestParam String phone,@RequestParam String pword, @RequestParam String scode) {
        RespResult result = RespResult.fail();
        //1. 校验
        if(CommonUtil.checkPhone(phone)) {
            if(pword != null && pword.length() == 32) {
                // 检查短信验证码
                if(smsRegisterService.checkSmsCode(phone, scode)) {
                    // 注册
                    int registerResult = userService.userRegister(phone, pword);
                    if(registerResult == 1) {
                        result = RespResult.ok();
                    } else if(registerResult == 2) {
                        result.setRCode(RCode.PHONE_EXISTS);
                    } else {
                        result.setRCode(RCode.REQUEST_PARAM_ERROR);
                    }
                } else {
                    result.setRCode(RCode.SMS_CODE_INVALID);
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }

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

    @ApiOperation(value = "用户登录,获取访问系统的token")
    @PostMapping("/login")
    public RespResult userLogin(@RequestParam String phone,@RequestParam String pword, @RequestParam String scode) throws Exception {
        RespResult result = RespResult.fail();
        if(CommonUtil.checkPhone(phone) && (pword != null && pword.length() == 32)) {
            if(smsLoginService.checkSmsCode(phone, scode)) {
                User user = userService.userLogin(phone, pword);
                if(user != null) {
                    // 登录成功，生成token
                    Map<String, Object> data = new HashMap<>();
                    data.put("uid", user.getId());
                    String jwtToken = jwtUtil.createJwt(data, 120);

                    result = RespResult.ok();
                    result.setAccessToken(jwtToken);

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("uid", user.getId());
                    userInfo.put("phone", user.getPhone());
                    userInfo.put("name", user.getName());
                    result.setData(userInfo);
                } else {
                    result.setRCode(RCode.PHONE_LOGIN_PASSWORD_INVALID);
                }
            } else {
                result.setRCode(RCode.SMS_CODE_INVALID);
            }
        } else {
            result.setRCode(RCode.REQUEST_PARAM_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "实名认证", notes = "提供身份证号、手机号、姓名进行认证")
    @PostMapping("/realname")
    public RespResult userRealName(@RequestBody RealNameVO realNameVO) {
        RespResult result = RespResult.fail();
        result.setRCode(RCode.REQUEST_PARAM_ERROR);
        // 校验
        if(CommonUtil.checkPhone(realNameVO.getPhone())) {
            if(StringUtils.isNotBlank(realNameVO.getName()) && StringUtils.isNotBlank(realNameVO.getIdCard())) {
                // 调用第三方接口，判断认证结果
                boolean realNameResult = realNameService.handleRealName(realNameVO.getPhone(), realNameVO.getName(), realNameVO.getIdCard());
                if(realNameResult == true) {
                    result = RespResult.ok();
                } else {
                    result.setRCode(RCode.REALNAME_FAILED);
                }
            }
        }
        return result;
    }
}
