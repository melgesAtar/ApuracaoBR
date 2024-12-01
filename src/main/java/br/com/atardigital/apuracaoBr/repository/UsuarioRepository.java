package br.com.atardigital.apuracaoBr.repository;

import br.com.atardigital.apuracaoBr.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    UserDetails findByEmail (String email);
}
