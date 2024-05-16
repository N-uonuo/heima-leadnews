package com.heima.user.controller.v1;


import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//标识为controller层
@RequestMapping("/api/v1/login")//请求路径
@Api(value = "app端登录模块", tags = "app端登录模块")//swagger注解
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    @ApiOperation("登录")
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDto dto) {
        //RequestBody注解用于接收前端传递的json数据
        return apUserService.login(dto);
    }

}
