package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author king
 * @version 2018-07-22 8:16 AM
 */
public class JwtHelper {
    
    //private final static String base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    private final static String base64Secret = "ajgfhabgjfafgbvabdhvfjadfkvab ag";
    private final static int expiresSecond = 172800000;
    
    public static void main(String[] args) {
        String a = createJWT("dfag", "dafg", "ADfg");
        System.out.println(a);
        System.out.println(parseJWT(a));
    }
    
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret)).parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }
    
    //public static Claims parseJWT(String jwt) throws Exception {
    //    SecretKey secretKey = null;
    //    return Jwts.parser()
    //            .setSigningKey(secretKey)
    //            .parseClaimsJws(jwt)
    //            .getBody();
    //}
    
    public static String createJWT(String username, String roles, String privileges) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setId("ksdfhghkdsfk").setHeaderParam("typ", "JWT")
                .claim("user_name", username)
                .claim("user_role", roles)
                .claim("user_privilege", privileges)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (expiresSecond >= 0) {
            long expMillis = nowMillis + expiresSecond;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        
        //生成JWT
        return builder.compact();
    }
    
}
