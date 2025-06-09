package com.perfulandia.vendedores.services;

import com.perfulandia.vendedores.dto.VendedoresDTO;
import com.perfulandia.vendedores.models.Vendedores;  
import com.perfulandia.vendedores.repository.VendedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class Vendedoresservices {

    
    @Autowired
    private VendedoresRepository repository;

    public VendedoresDTO crear(VendedoresDTO dto) {
        Vendedores vendedor = toEntity(dto);
        Vendedores saved = repository.save(vendedor);
        return toDTO(saved);
    }

    public List<VendedoresDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VendedoresDTO obtenerPorId(Integer id) {
        Vendedores vendedor = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vendedor no encontrado con id " + id));
        return toDTO(vendedor);
    }

    public VendedoresDTO actualizar(Integer id, VendedoresDTO dto) {
        Vendedores vendedor = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vendedor no encontrado con id " + id));
        vendedor.setSucursal(dto.getSucursal());
        vendedor.setMetaMensual(dto.getMetaMensual());
        Vendedores updated = repository.save(vendedor);
        return toDTO(updated);
    }

    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Vendedor no encontrado con id " + id);
        }
        repository.deleteById(id);
    }

    private VendedoresDTO toDTO(Vendedores vendedor) {
        VendedoresDTO dto = new VendedoresDTO();
        dto.setId(vendedor.getId());
        dto.setSucursal(vendedor.getSucursal());
        dto.setMetaMensual(vendedor.getMetaMensual());
        return dto;
    }

    private Vendedores toEntity(VendedoresDTO dto) {
        Vendedores vendedor = new Vendedores();
        vendedor.setId(dto.getId());
        vendedor.setSucursal(dto.getSucursal());
        vendedor.setMetaMensual(dto.getMetaMensual());
        return vendedor;
    }


}