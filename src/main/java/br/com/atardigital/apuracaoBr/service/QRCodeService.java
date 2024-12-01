package br.com.atardigital.apuracaoBr.service;

import br.com.atardigital.apuracaoBr.dto.BoletimUrnaDTO;
import br.com.atardigital.apuracaoBr.dto.QRCodeDTO;
import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import br.com.atardigital.apuracaoBr.model.QRCode;
import br.com.atardigital.apuracaoBr.repository.QRCodeRepository;
import br.com.atardigital.apuracaoBr.repository.UsuarioRepository;
import br.com.atardigital.apuracaoBr.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class QRCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private BoletimUrnaService boletimUrnaService;

    @Autowired
    private VotosCandidatoService votosCandidatoService;





    @Transactional
    public void processarQRCodeEBoletim(BoletimUrnaDTO boletimDeUrnaDTO) throws Exception{
        try {
            BoletimUrna boletimUrna = boletimUrnaService.processarBoletim(boletimDeUrnaDTO);

            if (boletimUrna != null) {
                List<CompletableFuture<Void>> futures = new ArrayList<>();

                for (QRCodeDTO qrcodeDTO : boletimDeUrnaDTO.getListaQRCodes()) {
                    QRCode qrCode = new QRCode();
                    qrCode.setBoletimUrna(boletimUrna);
                    qrCode.setHash(qrcodeDTO.getHash());
                    qrCode.setQrbu(qrcodeDTO.getQrbu());

                    QRCode qrCodePersistido = qrCodeRepository.save(qrCode);

                    CompletableFuture<Void> future = votosCandidatoService
                            .processarVotos(qrCodePersistido, qrcodeDTO.getRelacaoVotos(), boletimUrna);
                    futures.add(future);
                }

                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                        .thenRun(() -> System.out.println("Todos os processamentos de votos foram concluídos."))
                        .exceptionally(ex -> {
                            System.err.println("Erro ao processar um ou mais votos: " + ex.getMessage());
                            throw new RuntimeException("Erro ao processar os votos.", ex); // Lança novamente a exceção
                        });
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar QRCode e Boletim: " + e.getMessage());
            throw new RuntimeException(e.getMessage()); // Lança para a função chamadora
        }
    }



}

