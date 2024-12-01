package br.com.atardigital.apuracaoBr.repository;

import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import br.com.atardigital.apuracaoBr.model.VotosCandidato;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotosCandidatoRepository extends JpaRepository<VotosCandidato, Integer> {
    Optional<VotosCandidato> findByBoletimUrna(BoletimUrna boletimUrna);


    void deleteAllByBoletimUrna(BoletimUrna boletimUrna);

}
