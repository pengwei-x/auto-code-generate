package com.yunzhitx.medical.controller.utils.jwt;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;

/**

 * @Description: TODO
 * @date 2017/11/21$ 10:05$
 */
@Component
public class JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private JwtConfigProperties jwtConfigProperties;

    public JwtTokenProvider(JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
    }

    private SecretKeySpec getSecretKeySpec() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(jwtConfigProperties.getSecret().getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return secretKeySpec;
    }

//    public static void main(String[] args) {
//        JwtTokenProvider provider = new JwtTokenProvider();
//        LoginUser user = new LoginUser();
//        user.setUserId(1);
//        user.setUserName("123");
//
//        CommunityUser c=new CommunityUser();
//        c.setCommunity(true);
//        user.setCommunityUser(c);
//        String token = provider.createToken(user);
//
//        UAAClaims claims = provider.getClaims(token);
//        System.out.println("args = [" + args + "]");
//
//    }

    /**
     * 生成token
     *
     * @return
     */
    public String createToken(LoginUser user) {
        UaaClaims claims = new UaaClaims(user);
        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.SECOND, jwtConfigProperties.getExpiration());
        String compactJws = Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(exp.getTime())
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, getSecretKeySpec())
                .compact();
        return compactJws;
    }

    /**
     * token校验
     */
    public UaaClaims getClaims(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        try {
            return UaaClaims.parse(Jwts.parser().setSigningKey(getSecretKeySpec())
                    .parseClaimsJws(token).getBody());
        } catch (MalformedJwtException e) {
            logger.info("jwt MalformedJwtException:{}", token);
        } catch (ExpiredJwtException e) {
            logger.info("token expiration: {}", token);
        } catch (Exception e) {
            logger.warn("parse token has error:" + token, e);
        }
        return null;
    }
}