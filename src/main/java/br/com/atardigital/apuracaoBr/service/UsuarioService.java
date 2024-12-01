package br.com.atardigital.apuracaoBr.service;

import br.com.atardigital.apuracaoBr.model.Usuario;
import br.com.atardigital.apuracaoBr.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public int obterUsuarioAutenticado() {
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
        if (autenticacao != null && autenticacao.isAuthenticated() && !(autenticacao.getPrincipal() instanceof String)) {
            Usuario userDetails = (Usuario) autenticacao.getPrincipal();
            return userDetails.getId();
        }
        throw new IllegalStateException("Usuário não autenticado");
    }
}
