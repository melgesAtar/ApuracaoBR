package br.com.atardigital.apuracaoBr.repository;

import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import br.com.atardigital.apuracaoBr.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    List<QRCode> findByBoletimUrna(BoletimUrna boletimUrna);

    void deleteAllByBoletimUrna(BoletimUrna boletimUrna);

}
