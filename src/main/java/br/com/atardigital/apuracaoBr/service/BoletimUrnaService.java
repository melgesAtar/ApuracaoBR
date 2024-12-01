package br.com.atardigital.apuracaoBr.service;

import br.com.atardigital.apuracaoBr.dto.BoletimUrnaDTO;
import br.com.atardigital.apuracaoBr.dto.QRCodeDTO;
import br.com.atardigital.apuracaoBr.model.BoletimUrna;
import br.com.atardigital.apuracaoBr.repository.BoletimUrnaRepository;
import br.com.atardigital.apuracaoBr.repository.QRCodeRepository;
import br.com.atardigital.apuracaoBr.repository.VotosCandidatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BoletimUrnaService {

    @Autowired
    private BoletimUrnaRepository boletimUrnaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VotosCandidatoRepository votosCandidatoRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;
    @Transactional
    public BoletimUrna processarBoletim(BoletimUrnaDTO boletimDeUrnaDTO) throws Exception {


        try {


            validaBoletimUrna(boletimDeUrnaDTO);
            verificaSeEstaDuplicado(boletimDeUrnaDTO.getListaQRCodes().get(0).getIdue());


            BoletimUrna boletimUrna = new BoletimUrna();

            QRCodeDTO primeiroQrCode = boletimDeUrnaDTO.getListaQRCodes().get(0);
            QRCodeDTO ultimoQrCode = boletimDeUrnaDTO.getListaQRCodes().get(boletimDeUrnaDTO.getListaQRCodes().size() - 1);

            boletimUrna.setVrqr(primeiroQrCode.getVrqr());
            boletimUrna.setVrch(primeiroQrCode.getVrch());
            boletimUrna.setProc(primeiroQrCode.getProc());
            boletimUrna.setDtpl(primeiroQrCode.getDtpl());
            boletimUrna.setPlei(primeiroQrCode.getPlei());
            boletimUrna.setTurn(primeiroQrCode.getTurn());
            boletimUrna.setUnfe(primeiroQrCode.getUnfe());
            boletimUrna.setMuni(primeiroQrCode.getMuni());
            boletimUrna.setZona(primeiroQrCode.getZona());
            boletimUrna.setSeca(primeiroQrCode.getSeca());
            boletimUrna.setIdue(primeiroQrCode.getIdue());
            boletimUrna.setIdca(primeiroQrCode.getIdca());
            boletimUrna.setHiqt(primeiroQrCode.getHiqt());
            boletimUrna.setVers(primeiroQrCode.getVers());
            boletimUrna.setLoca(primeiroQrCode.getLoca());
            boletimUrna.setDtab(primeiroQrCode.getDtab());
            boletimUrna.setHrab(primeiroQrCode.getHrab());
            boletimUrna.setDtfc(primeiroQrCode.getDtfc());
            boletimUrna.setHrfc(primeiroQrCode.getHrfc());
            boletimUrna.setIdel(primeiroQrCode.getIdel());
            boletimUrna.setTipo(primeiroQrCode.getTipo());
            boletimUrna.setAssi(ultimoQrCode.getAssi());
            boletimUrna.setBran(ultimoQrCode.getBran());
            boletimUrna.setNulo(ultimoQrCode.getNulo());
            String[] qrbu = boletimDeUrnaDTO.getListaQRCodes().get(0).getQrbu().split(":");
            boletimUrna.setQrbuTotal(qrbu[1]);
            boletimUrna.setUsuario(usuarioService.obterUsuarioAutenticado());


            BoletimUrna boletimUrnaPersestido = boletimUrnaRepository.save(boletimUrna);



            return boletimUrnaPersestido;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }

    public void validaBoletimUrna(BoletimUrnaDTO boletimUrnaDTO) throws IllegalStateException, IllegalArgumentException {


        List<QRCodeDTO> listaQRCodesDTO = boletimUrnaDTO.getListaQRCodes();
        if (listaQRCodesDTO == null || listaQRCodesDTO.isEmpty()) {
            throw new IllegalArgumentException("A lista de QR Codes está vazia.");
        }


        int contadorDeQRCodesNulos = 0;
        for (QRCodeDTO qrCodeDTO : listaQRCodesDTO) {
            if (qrCodeDTO == null || qrCodeDTO.getQrbu() == null) {
                contadorDeQRCodesNulos++;
            }
        }

        if (contadorDeQRCodesNulos > 0) {
            throw new IllegalStateException("Encontrado " + contadorDeQRCodesNulos + " QR Codes nulos ou inválidos.");
        }


        String[] qrbu = listaQRCodesDTO.get(0).getQrbu().split(":");
        int totalEsperado = Integer.parseInt(qrbu[1]);
        if (listaQRCodesDTO.size() != totalEsperado) {
            throw new IllegalArgumentException("Número de QR Codes (" + listaQRCodesDTO.size() + ") não corresponde ao esperado (" + totalEsperado + ").");
        }


        for (int i = 0; i < listaQRCodesDTO.size(); i++) {
            String[] qrbuEach = listaQRCodesDTO.get(i).getQrbu().split(":");
            int numeroSequencia = Integer.parseInt(qrbuEach[0]);
            if (numeroSequencia != i + 1) {
                throw new IllegalStateException("QR Codes fora de ordem. Esperado " + (i + 1) + " mas encontrado " + numeroSequencia);
            }
            if (!Objects.equals(qrbuEach[1], qrbu[1])) {
                throw new IllegalStateException("QR Code " + (i + 1) + " não pertence a esta urna.");
            }
        }

    }



    @Transactional
    public ResponseEntity<String> verificaSeEstaDuplicado(Long idue) throws Exception {
        try {
            List<BoletimUrna> boletinsUrna = boletimUrnaRepository.findByIdue(idue);

            if (boletinsUrna != null && !boletinsUrna.isEmpty()) {
                for (BoletimUrna boletimUrna : boletinsUrna) {
                    votosCandidatoRepository.deleteAllByBoletimUrna(boletimUrna);
                    qrCodeRepository.deleteAllByBoletimUrna(boletimUrna);
                    boletimUrnaRepository.delete(boletimUrna);
                }
                return ResponseEntity.ok("Boletins Urna duplicados excluídos com sucesso");
            } else {
                return ResponseEntity.ok("Não há Boletins Urna duplicados");
            }
        }catch (Exception e ){
            System.err.println("Erro ao validar se existe duplicata" + e.getMessage());
            throw new RuntimeException("Erro ao validar se existe duplicata  ", e);
        }


    }




}
