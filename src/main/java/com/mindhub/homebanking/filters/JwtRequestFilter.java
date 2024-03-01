package com.mindhub.homebanking.filters;

import com.mindhub.homebanking.securityServices.JwtUtilService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;


    // DE LA PETICIÓN VEO SI OBTUVE UN HEADER CON AUTHORIZATION Y SI COMIENZA CON BEARER
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String email = null;
        String token = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            email = jwtUtilService.extractUsername(token);
        }

        // SI NO TENGO GENERADO UN USUARIO EN EL CONTEXTO DE LA APLICACIÓN, GENERALO.
        // GENERAMOS UN OBJETO DE TIPO USERNAMEPASSWORD..... QUE VALIDÉ CON EL USERDETAILSERVICE Y EL TOKEN Y QUE ESTÁ EN
        // EL CONTEXTO DE LA APLICACIÓN
        // AL OBJETO CREADO LE SETEAMOS LOS DETALLES DE LO MODIFICADO ANTERIORMENTE
        // FINALMENTE DEL CONTEXTO DE SEGURIDAD DE LA APP, OBTENGO EL CONTEXTO Y LE SETEO LA AUTENTICACIÓN PASÁNDOLE EL OBJETO
        // USERNAMEPASSWORD...(USUARIO AUTENTICADO POR TOKEN, MAIL Y PASSWORD)
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            if(jwtUtilService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }




}
