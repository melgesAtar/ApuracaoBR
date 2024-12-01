package br.com.atardigital.apuracaoBr.controller;

import  br.com.atardigital.apuracaoBr.dto.AuthenticationDTO;
import br.com.atardigital.apuracaoBr.dto.LoginResponseDTO;
import br.com.atardigital.apuracaoBr.dto.RegisterDTO;
import br.com.atardigital.apuracaoBr.model.Usuario;
import br.com.atardigital.apuracaoBr.repository.UsuarioRepository;
import br.com.atardigital.apuracaoBr.security.TokenService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.GenerateToken(((Usuario) auth.getPrincipal()));


            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch (AuthenticationException authEx){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
        }

        }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        try {
            if(data.email().isEmpty() || data.email().isEmpty() || data.senha().isEmpty() || data.senha().isEmpty()) return ResponseEntity.badRequest().body("Ta vazio os campos");
            if(usuarioRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().body("Dados inválidos");

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
            Usuario novoUsuario = new Usuario(data.email(),encryptedPassword, data.role());

            this.usuarioRepository.save(novoUsuario);

            return ResponseEntity.ok().body("Usuário criado com sucesso");
        }catch (Exception e ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos fornecidos");
        }
    }
}
