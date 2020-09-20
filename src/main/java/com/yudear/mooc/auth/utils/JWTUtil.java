package com.yudear.mooc.auth.utils;

import io.jsonwebtoken.*;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    public static final String SECRETKEY="admin";

    @Autowired
    private JWTProperties ossConfigProperties;

    public static JWTProperties configProperties;
    @PostConstruct
    public void init(){
        configProperties = this.ossConfigProperties;
    }


    public static String createToken(int subject,String roles){
        JwtBuilder jwt = Jwts.builder();
        jwt.setId(subject+"");
        jwt.setIssuer(configProperties.getHeader());
        jwt.setSubject(roles);
        jwt.setIssuedAt(new Date());
        Date expiration =new Date(System.currentTimeMillis()+configProperties.getExpire()*1000);
        jwt.setExpiration(expiration);
        byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(configProperties.getSecret());
        jwt.signWith(SignatureAlgorithm.HS256,secretkeyBytes);
        return  jwt.compact();
    }


    public static Claims getClaimByToken(String token) {
        try {
            return Jwts.parser().setSigningKey(configProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }



}
