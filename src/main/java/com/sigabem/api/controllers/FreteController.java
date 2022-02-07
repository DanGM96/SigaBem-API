package com.sigabem.api.controllers;

import com.sigabem.api.mappers.FreteMapper;
import com.sigabem.api.models.dtos.FreteDTO;
import com.sigabem.api.models.entities.FreteEntity;
import com.sigabem.api.services.CalculoFreteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/frete")
public class FreteController {
    @Autowired
    private CalculoFreteService calculoFreteService;

    private final FreteMapper freteMapper = new FreteMapper();

    @ApiOperation(value = "Calcula e retorna as informações do frete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, pode retornar uma breve explicação")
    })
    @PostMapping
    public FreteEntity calculoFrete(@RequestBody @Valid FreteDTO freteDTO){
        final FreteEntity baseEntity = freteMapper.convertToEntity(freteDTO);
        final FreteEntity resultEntity = calculoFreteService.CalcularFrete(baseEntity);
        return resultEntity;
    }
}
