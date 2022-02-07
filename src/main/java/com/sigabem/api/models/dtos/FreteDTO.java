package com.sigabem.api.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreteDTO {
    @NotNull(message = "peso cannot be null")
    @DecimalMin(value = "0", inclusive = false, message = "peso should be greater than zero")
    private BigDecimal peso;

    @NotBlank(message = "cepOrigem cannot be blank")
    @Pattern(regexp = "\\d{8}", message = "cepOrigem is not a valid cep")
    private String cepOrigem;

    @NotBlank(message = "cepDestino cannot be blank")
    @Pattern(regexp = "\\d{8}", message = "cepDestino is not a valid cep")
    private String cepDestino;

    @NotBlank(message = "nomeDestinatario cannot be blank")
    private String nomeDestinatario;
}
