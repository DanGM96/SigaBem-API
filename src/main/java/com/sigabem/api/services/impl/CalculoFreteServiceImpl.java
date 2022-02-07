package com.sigabem.api.services.impl;

import com.sigabem.api.clients.ViaCepClient;
import com.sigabem.api.models.dtos.ViaCepDTO;
import com.sigabem.api.models.entities.FreteEntity;
import com.sigabem.api.repositories.FreteRepository;
import com.sigabem.api.services.CalculoFreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

@Service
public class CalculoFreteServiceImpl implements CalculoFreteService {
    public final static BigDecimal VALOR_POR_KG = BigDecimal.valueOf(1.0);

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private FreteRepository freteRepository;

    @Override
    public FreteEntity CalcularFrete(FreteEntity freteEntity) {
        ViaCepDTO dadosOrigem = viaCepClient.getCepData(freteEntity.getCepOrigem());
        ViaCepDTO dadosDestino = viaCepClient.getCepData(freteEntity.getCepDestino());

        final BigDecimal valorTotalFrete = getValorTotalFrete(freteEntity.getPeso(), dadosOrigem, dadosDestino);
        final Date dataPrevistaEntrega = getPrevisaoEntrega(dadosOrigem, dadosDestino);
        final Date dataConsulta = new Date();

        FreteEntity resultEntity = FreteEntity
                .builder()
                .peso(freteEntity.getPeso())
                .cepOrigem(freteEntity.getCepOrigem())
                .cepDestino(freteEntity.getCepDestino())
                .nomeDestinatario(freteEntity.getNomeDestinatario())
                .vlTotalFrete(valorTotalFrete)
                .dataPrevistaEntrega(dataPrevistaEntrega)
                .dataConsulta(dataConsulta)
                .build();

        return freteRepository.save(resultEntity);
    }

    private float getDesconto(ViaCepDTO origem, ViaCepDTO destino){
        if(origem.getDdd().equals(destino.getDdd())){
            return 0.5F;
        } else if (origem.getUf().equals(destino.getUf())){
            return 0.75F;
        }
        else {
            return 1F;
        }
    }

    private int getDiasEntrega(ViaCepDTO origem, ViaCepDTO destino){
        if(origem.getDdd().equals(destino.getDdd())){
            return 1;
        } else if (origem.getUf().equals(destino.getUf())){
            return 3;
        }
        else {
            return 10;
        }
    }

    private Date getPrevisaoEntrega(ViaCepDTO origem, ViaCepDTO destino){
        final int diasEntrega = getDiasEntrega(origem, destino);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, diasEntrega);
        return calendar.getTime();
    }

    private BigDecimal getValorFrete(BigDecimal peso){
        return peso.multiply(VALOR_POR_KG)
                .setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal getValorTotalFrete(BigDecimal peso, ViaCepDTO origem, ViaCepDTO destino){
        final BigDecimal valorFrete = getValorFrete(peso);
        final BigDecimal desconto = BigDecimal.valueOf(getDesconto(origem, destino));
        return valorFrete.multiply(desconto)
                .setScale(2, RoundingMode.CEILING);
    }
}
