package com.yaozhetao.treebud.dao;

import com.yaozhetao.treebud.mapper.TbUserMapper;
import com.yaozhetao.treebud.model.TbUser;
import com.yaozhetao.treebud.model.TbUserExample;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TbUserDao {

    private final static String EMPTY = "";

    @Resource
    TbUserMapper tbUserMapper;

    public String getPassword(String account) throws Exception {
        TbUserExample tbUserExample = new TbUserExample();
        tbUserExample.or().andAccountEqualTo(account);
        List<TbUser> tbUserList = tbUserMapper.selectByExample(tbUserExample);
        int userCount = tbUserList.size();
        if(userCount == 1){
            return tbUserList.get(0).getPassword();
        }else if (userCount <1 ){
            return EMPTY;
        }else {
            throw new Exception();
        }
    }

    public TbUser getUser(String account,String password) throws Exception {
        TbUserExample example = new TbUserExample();
        example.or().andAccountEqualTo(account);
        example.or().andPasswordEqualTo(password);
        List<TbUser> tbUserList = tbUserMapper.selectByExample(example);
        int userCount = tbUserList.size();
        if(userCount == 1){
            return tbUserList.get(0);
        }else{
            throw new Exception();
        }
    }

    public boolean insertUser(String account,String password){
        TbUser tbUser = new TbUser();
        tbUser.setAccount(account);
        tbUser.setPassword(password);
        tbUser.setToken(password);
        return tbUserMapper.insertSelective(tbUser) == 1;
    }
}
