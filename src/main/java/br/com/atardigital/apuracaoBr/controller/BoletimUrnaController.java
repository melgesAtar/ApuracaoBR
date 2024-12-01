package br.com.atardigital.apuracaoBr.controller;


import br.com.atardigital.apuracaoBr.dto.BoletimUrnaDTO;
import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import br.com.atardigital.apuracaoBr.repository.BoletimUrnaRepository;
import br.com.atardigital.apuracaoBr.service.BoletimUrnaService;
import br.com.atardigital.apuracaoBr.service.QRCodeService;
import br.com.atardigital.apuracaoBr.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/boletim-urna")
public class BoletimUrnaController {

    @Autowired
    BoletimUrnaService boletimUrnaService;

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private BoletimUrnaRepository boletimUrnaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/processar")
    public ResponseEntity<String> processarBoletim(@RequestBody BoletimUrnaDTO boletimDeUrnaDTO) {
        try {
            qrCodeService.processarQRCodeEBoletim(boletimDeUrnaDTO);
            return ResponseEntity.ok("Boletim processado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processar boletim: " + e.getMessage());
        }
    }



    @GetMapping("/boletins")
    public ResponseEntity<List<BoletimUrna>> findAllBooks(){
        List<BoletimUrna> boletins =  boletimUrnaRepository.findAll();
        return ResponseEntity.ok(boletins);
    }



}
