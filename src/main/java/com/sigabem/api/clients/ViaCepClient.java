package com.sigabem.api.clients;

import com.sigabem.api.models.dtos.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "viacep", url = "https://viacep.com.br")
public interface ViaCepClient {
     @RequestMapping("/ws/{cep}/json")
     ViaCepDTO getCepData(@PathVariable String cep);

}
