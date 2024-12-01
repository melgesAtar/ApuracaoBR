package br.com.atardigital.apuracaoBr.security;

import br.com.atardigital.apuracaoBr.repository.UsuarioRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.debug("Iniciando SecurityFilter para a requisição: {}", request.getRequestURI());
        try {
            String token = this.recoveryToken(request);
            if (token != null && !token.isEmpty()) {
                logger.debug("Token encontrado: {}", token);
                String login = tokenService.validateToken(token);
                logger.debug("Login extraído do token: {}", login);
                if (login != null && !login.isEmpty()) {
                    UserDetails usuario = usuarioRepository.findByEmail(login);
                    if (usuario != null) {
                        logger.debug("Usuário encontrado: {}", usuario.getUsername());
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        logger.warn("Usuário não encontrado para o login: {}", login);
                        throw new JWTVerificationException("Usuário não encontrado.");
                    }
                }
            } else {
                logger.debug("Nenhum token encontrado na requisição.");
            }
        } catch (JWTVerificationException e) {
            logger.error("Erro de autenticação: {}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token inválido ou expirado.\"}");
            return; // Interrompe a execução do filtro
        } catch (Exception e) {
            logger.error("Erro interno: {}", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Erro interno no servidor.\"}");
            return; // Interrompe a execução do filtro
        }
        filterChain.doFilter(request, response);
    }


    private String recoveryToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7); // Remove "Bearer " prefix
    }
}
