package com.yaozhetao.treebud.dao;

import com.yaozhetao.treebud.mapper.TbUserinfoMapper;
import com.yaozhetao.treebud.model.TbUserinfo;
import com.yaozhetao.treebud.vo.RegisterInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TbUserInfoDao {

    @Resource
    TbUserinfoMapper tbUserinfoMapper;

    public boolean insertUserInfo(RegisterInfo registerInfo,int userId){
        TbUserinfo tbUserinfo = new TbUserinfo();
        tbUserinfo.setUserid(userId);
        tbUserinfo.setEmail(registerInfo.getEmail());
        tbUserinfo.setUsername(registerInfo.getNickName());
        return tbUserinfoMapper.insertSelective(tbUserinfo) == 1;
    }
}
