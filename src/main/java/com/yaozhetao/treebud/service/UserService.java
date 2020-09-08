package com.yaozhetao.treebud.service;

import com.yaozhetao.treebud.dao.TbUserDao;
import com.yaozhetao.treebud.dao.TbUserInfoDao;
import com.yaozhetao.treebud.model.TbUser;
import com.yaozhetao.treebud.vo.LoginInfo;
import com.yaozhetao.treebud.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final static int ERROR_CODE = -1;

    @Autowired
    TbUserDao tbUserDao;
    @Autowired
    TbUserInfoDao tbUserInfoDao;

    public boolean checkPassword(LoginInfo loginInfo) {
        String password = loginInfo.getPassword();
        String account = loginInfo.getAccount();
        try {
            String correct_password = tbUserDao.getPassword(account);
            return password.equals(correct_password);
        }catch (Exception e){
            return false;
        }
    }

    public int insertUser(RegisterInfo registerInfo){
        if(tbUserDao.insertUser(registerInfo.getAccount(),registerInfo.getPassword())) {
            try{
                TbUser tbUser = tbUserDao.getUser(registerInfo.getAccount(),registerInfo.getPassword());
                return tbUser.getId();
            }catch (Exception e){
                return ERROR_CODE;
            }
        }else{
            return ERROR_CODE;
        }
    }

    public boolean insertUserInfo(RegisterInfo registerInfo,int userId){
        return tbUserInfoDao.insertUserInfo(registerInfo,userId);
    }

    public int getUserId(String account,String password){
        try{
            TbUser tbUser = tbUserDao.getUser(account,password);
            return tbUser.getId();
        }catch (Exception e){
            return ERROR_CODE;
        }
    }

}
