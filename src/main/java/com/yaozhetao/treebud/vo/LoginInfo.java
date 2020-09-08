package com.yaozhetao.treebud.vo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author George
 */
@Data
public class LoginInfo {
    /**
     *  账号
     */
    private String account;
    /**
     *  密码
     */
    private String password;

}
