package com.perennial.doctorappointmentbooking.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtUtil {
    private String SECRETKEY="secret";

    public String extractUserName(String token)
    {
        return  exctractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
        return exctractClaim(token,Claims::getExpiration);
    }
    public  <T> T exctractClaim(String token, Function<Claims,T> claimReasolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimReasolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        return  extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();
    }

    public Boolean validateToken(String token,UserDetails userDetails)
    {
        final String userName=extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }



}
