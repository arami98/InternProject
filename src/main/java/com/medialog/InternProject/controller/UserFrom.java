package com.medialog.InternProject.controller;

import com.medialog.InternProject.model.Auth;
import com.medialog.InternProject.model.User;
import lombok.Getter;
import lombok.Setter;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Getter
@Setter
public class UserFrom {

    private String name;
    private String ssn1;
    private String ssn2;
    private String tel1;
    private String tel2;
    private String tel3;
    private String userId;
    private String password;
    private String address;
    private String email;
    private String type;
    private String regNo;
    private String regDate;

    public User extractUser() throws ParseException {
        User user = new User();

        user.setLoginId(userId);
        user.setLoginPwd(password);
        user.setName(name);
        user.setSsn(ssn1+ssn2);
        user.setTel(tel1+tel2+tel3);

        if(type.equals("agent")){
            user.setAuth(Auth.agent);
            user.setAgentRegNo(regNo);
        }else if(type.equals("user")){
            user.setAuth(Auth.user);
        }

        user.setAddr(address);

        user.setRegDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS").parse(regDate));
        user.setUpdateDt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS").parse(regDate));
        return user;
    }
}
