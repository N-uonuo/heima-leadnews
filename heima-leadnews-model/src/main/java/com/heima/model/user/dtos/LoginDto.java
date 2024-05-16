package com.heima.model.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data//lombok插件注解，自动生成get、set方法
public class LoginDto {

        @ApiModelProperty(value = "手机号", required = true)//required是用来标识是否必填
        private String phone;

        @ApiModelProperty(value = "密码", required = true)
        private String password;
}
