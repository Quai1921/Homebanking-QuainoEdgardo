package com.mindhub.homebanking.securityServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtilService {

    // Jwts(JSON WEB TOKEN, QUEREMOS GENERAR LA FIRMA, MEDIANTE ALGORITMO DE ENCRIPTADO HS256 Y QUE SE GENERE LA CLAVE.
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();


    // TIEMPO DE EXPIRACIÓN DEL TOKEN EN MILISEGUNDOS.
    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60;


    //  EXTRAEMOS TODOS LOS CLAIMS, QUE ES LO QUE ESTÁ EN EL CUERPO DEL TOKEN -INFO DEL USUARIO-), PARA LUEGO QUEDARME
    //  CON EL QUE NECESITE. HAGO UN PARSER PARA VERIFICAR LA SECRET KEY (PORQUE NO ES EL MISMO TIPO DE DATO).
    //  DESPUÉS LO CONSTRUYO. PARSEO LOS CLAIMS CON EL TOKEN Y ME LO DEVUELVE DEL PAYLOAD (CUERPO DEL JWT)
    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
    }

    // <T> TIPO DE DATO GENÉRICO. A LOS CLAIMS, EXTRAIGO TODOS Y ME QUEDO CON UNO EN PARTICULAR
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // UNA FUNCIÓN DE CLAIMS (OBJETO QUE USO AL TRABAJAR CON JWT) ES UNA INTERFAZ CLAIMS Y USAMOS SU MÉTODO GET SUBJECT
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ESTE MÉTODO NOS DEVUELVE EL TOKEN. USERDETAILS ES UNA INTERFAZ. MAP PORQUE ES CLAVE/VALOR Y GUARDO ESPACIO EN MEMORIA
    // ACÁ AGREGO LOS DATOS QUE CONSIDERE ÚTILES
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        var role = userDetails.getAuthorities().stream().toList().get(0);
        claims.put("role", role);
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts
                .builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SECRET_KEY)
                .compact();
    }

}
