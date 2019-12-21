package com.yudear.mooc.auth.shiro;

import com.fasterxml.jackson.databind.node.BooleanNode;
import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

    private String jwtToken;

    public JWTToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return jwtToken;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }
}
