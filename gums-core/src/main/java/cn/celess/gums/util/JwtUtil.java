package cn.celess.gums.util;

import cn.celess.gums.config.ApplicationConfig;
import cn.celess.gums.common.entity.User;
import cn.celess.gums.exception.CommonException;
import cn.celess.gums.common.response.Response;
import cn.celess.gums.common.response.ResponseConstant;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021/11/12
 *
 * @author 禾几海
 */
@Slf4j
public class JwtUtil {
    private static final String USER_ID = "uid";
    private static final String USER_NAME = "uname";

    public static String generateToken(User user, boolean isRemember) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(USER_ID, user.getId());
        claims.put(USER_NAME, user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + (
                        isRemember ? ApplicationConfig.getInstance().loginTokenExpirationTimeWithRememberMe
                                : ApplicationConfig.getInstance().loginTokenExpirationTime) * 1000
                ))
                .signWith(SignatureAlgorithm.HS512, EnvironmentUtil.getProperties("jwt.secret"))
                .compact();
    }

    public static String updateTokenDate(String token) {
        Claims claims = Jwts.parser().setSigningKey(EnvironmentUtil.getProperties("jwt.secret")).parseClaimsJws(token).getBody();
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(claims.getExpiration().getTime() + (ApplicationConfig.getInstance().loginTokenExpirationTime * 1000)))
                .signWith(SignatureAlgorithm.HS512, EnvironmentUtil.getProperties("jwt.secret"))
                .compact();
    }

    /**
     * 获取token是否过期
     */
    public static Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration == null || expiration.before(new Date());
    }

    /**
     * 根据token获取username
     */
    public static Integer getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims == null ? null : (Integer) claims.get(USER_ID);
    }

    /**
     * 获取token的过期时间
     */
    public static Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims == null ? null : claims.getExpiration();
    }

    /**
     * 解析JWT
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(EnvironmentUtil.getProperties("jwt.secret"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("JWT令牌过期");
            throw new CommonException(Response.error(ResponseConstant.JWT_EXPIRED));
        } catch (UnsupportedJwtException e) {
            log.info("不支持的JWT令牌");
            throw new CommonException(Response.error(ResponseConstant.JWT_NOT_SUPPORT));
        } catch (MalformedJwtException e) {
            log.info("JWT令牌格式错误");
            throw new CommonException(Response.error(ResponseConstant.JWT_MALFORMED));
        } catch (IllegalArgumentException e) {
            log.debug("JWT非法参数");
        }
        return claims;
    }
}