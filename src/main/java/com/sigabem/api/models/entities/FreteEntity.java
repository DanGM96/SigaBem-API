package com.sigabem.api.models.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreteEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal vlTotalFrete;
    private Date dataPrevistaEntrega;
    private Date dataConsulta;
    private BigDecimal peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
}
