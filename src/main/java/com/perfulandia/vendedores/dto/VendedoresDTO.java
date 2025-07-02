package com.perfulandia.vendedores.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedoresDTO extends RepresentationModel<VendedoresDTO>{
    private Integer id;
    private String sucursal;
    private Double metaMensual;
} 

