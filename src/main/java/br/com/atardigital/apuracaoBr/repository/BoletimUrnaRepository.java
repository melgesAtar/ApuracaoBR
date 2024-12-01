package br.com.atardigital.apuracaoBr.repository;

import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoletimUrnaRepository extends JpaRepository<BoletimUrna, Integer> {
   List<BoletimUrna> findByIdue(Long idue);

   void deleteAllByIdue(Long idue);
}

