package br.com.atardigital.apuracaoBr.service;

import br.com.atardigital.apuracaoBr.dto.VotosCandidatoDTO;
import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import br.com.atardigital.apuracaoBr.model.QRCode;
import br.com.atardigital.apuracaoBr.model.VotosCandidato;
import br.com.atardigital.apuracaoBr.repository.VotosCandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class VotosCandidatoService {

    @Autowired
    private VotosCandidatoRepository votosCandidatoRepository;
    @Async("taskExecutor")
    public CompletableFuture<Void> processarVotos(QRCode qrCode, List<VotosCandidatoDTO> relacaoVotos, BoletimUrna boletimUrna) {
        try {
            List<VotosCandidato> votosCandidatos = converterListaVotos(relacaoVotos, qrCode, boletimUrna);
            votosCandidatoRepository.saveAll(votosCandidatos);
            System.out.println("Processamento de votos concluído com sucesso.");
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            System.err.println("Erro ao processar votos: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    public List<VotosCandidato> converterListaVotos(List<VotosCandidatoDTO> votosCandidatoDTOList, QRCode qrCode, BoletimUrna boletimUrna) {
        List<VotosCandidato> votosCandidatos = new ArrayList<>();

        for (VotosCandidatoDTO votosCandidatoDTO : votosCandidatoDTOList) {
            for (Map.Entry<String, Integer> entry : votosCandidatoDTO.getVotos().entrySet()) {
                String numeroCandidato = entry.getKey();
                Integer quantidadeVotos = entry.getValue();

                VotosCandidato votosCandidato = new VotosCandidato();
                votosCandidato.setCandidatoNumero(Integer.valueOf(numeroCandidato));
                votosCandidato.setVotos(quantidadeVotos);
                votosCandidato.setBoletimUrna(boletimUrna);
                votosCandidato.setQrCode(qrCode);

                votosCandidatos.add(votosCandidato);
            }
        }

        return votosCandidatos;
    }
}



