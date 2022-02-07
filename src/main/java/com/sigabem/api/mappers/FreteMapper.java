package com.sigabem.api.mappers;

import com.sigabem.api.models.dtos.FreteDTO;
import com.sigabem.api.models.entities.FreteEntity;

public class FreteMapper implements EntityMapper<FreteEntity, FreteDTO> {
    @Override
    public FreteEntity convertToEntity(FreteDTO dto) {
        return FreteEntity
                .builder()
                .cepOrigem(dto.getCepOrigem())
                .cepDestino(dto.getCepDestino())
                .nomeDestinatario(dto.getNomeDestinatario())
                .peso(dto.getPeso())
                .build();
    }
}
