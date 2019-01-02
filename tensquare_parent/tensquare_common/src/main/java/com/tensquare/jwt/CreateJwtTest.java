package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-31 15:06
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        JwtBuilder builder= Jwts.builder()
                .setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(new Date().getTime()+60000))
                .claim("role","admin");
        System.out.println(builder.compact());

    }
}
