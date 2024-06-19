package com.example.myrok.dto.security;

import com.example.myrok.type.LoginProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

//spring seucrity가 사용하는 DTO Type으로 만들어 주어야함
public class MemberSecurityDTO extends User {

    private String socialId, name, password;
    private List<String> memberRoles = new ArrayList<>();


    public MemberSecurityDTO(String socialId, String password, List<String> memberRoles, String name) {
        super(socialId, password,  memberRoles.stream().map(str ->
                        //spring security가 사용하는 권한으로 사용해야한다
                        //권한을 만들어주는 type이 있다
                        new SimpleGrantedAuthority("ROLE_"+str))
                .collect(Collectors.toList()));

        this.socialId = socialId;
        this.password = password;
        this.name = name;
        this.memberRoles = memberRoles;
    }

    //jwt문자열을 만들어서 주고 받는다
    //jwt의 내용물을 Claims라 한다
    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", name);
        dataMap.put("password",password);
        dataMap.put("socialId", socialId);
        dataMap.put("memberRole", memberRoles);
        return dataMap;
    }
}
