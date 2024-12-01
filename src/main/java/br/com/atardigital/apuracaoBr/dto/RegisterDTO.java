package br.com.atardigital.apuracaoBr.dto;

import br.com.atardigital.apuracaoBr.config.UserRole;

public record RegisterDTO(String email, String senha, UserRole role) {
}
