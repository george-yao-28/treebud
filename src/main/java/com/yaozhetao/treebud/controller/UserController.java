package com.yaozhetao.treebud.controller;

import com.yaozhetao.treebud.service.UserService;
import com.yaozhetao.treebud.vo.LoginInfo;
import com.yaozhetao.treebud.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final static String SUCCESS_CODE = "000000";
    private final static String ERROR_CODE = "999999";

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String login(@RequestBody LoginInfo loginInfo){
        try{
            if(userService.checkPassword(loginInfo)){
                int userId = userService.getUserId(loginInfo.getAccount(), loginInfo.getPassword());
                if(userId != -1){
                    redisTemplate.opsForValue().set(String.valueOf(userId),userId);
                    System.out.println(redisTemplate.opsForValue().get(String.valueOf(userId)));
                    return SUCCESS_CODE;
                }else{
                    return ERROR_CODE;
                }
            }else{
                return ERROR_CODE;
            }
        }catch (Exception e){
            return ERROR_CODE;
        }
    }

    @RequestMapping(value = "register" ,method = RequestMethod.POST)
    @Transactional
    public String register(@RequestBody RegisterInfo registerInfo){
        try{
            int newUserId = userService.insertUser(registerInfo);
            if(newUserId != -1){
                return userService.insertUserInfo(registerInfo,newUserId)?SUCCESS_CODE:ERROR_CODE;
            }else {
                return ERROR_CODE;
            }
        }catch (Exception e){
                return ERROR_CODE;
        }
    }

}
