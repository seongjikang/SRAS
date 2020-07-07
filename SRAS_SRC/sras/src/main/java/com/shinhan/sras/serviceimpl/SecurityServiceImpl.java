package com.shinhan.sras.serviceimpl;

import com.shinhan.sras.service.SecurityService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityServiceImpl implements SecurityService {
    private String secretKey;

    @Override
    public String createToken(String subject, long ttlMillis) {
        if(ttlMillis <= 0)
            throw new RuntimeException("Expiry time must be greater than Zero : [" + ttlMillis + "]" );

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        issueKey(subject);

        if (secretKey != null) {
            byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
            Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder()
                    .setSubject(subject)
                    .signWith(signatureAlgorithm, signingKey);
            long nowMillis = System.currentTimeMillis();
            builder.setExpiration(new Date(nowMillis + ttlMillis));

            return builder.compact();
        } else {
            //Todo : 키 발급 실패시 예외 처리 필요, #16
        }
        return null;
    }

    @Override
    public String getSubject(String token) {
        if (secretKey != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(token).getBody();
            return claims.getSubject();
        } else {
            //Todo : Subject 획듯 실패 시 예외 처리 필요, #16
        }
        return null;
    }

    public void issueKey(String id) {
        // TODO : 골드윙 api 연동 하여 행번에 맞는 비밀번호 값을 가져와 secretKey 발급, #17
        this.secretKey = ConnectGolding(id);
    }

    public String ConnectGolding(String id) {
        // TODO : 골드윙 api 연동 하여 행번에 맞는 비밀번호 값을 가져와 secretKey 발급# 17
        // 지금은 임시값 리턴
        return "asdasdjlkjkl1kjasdkjk;as";
    }

}
