package com.perfulandia.vendedores.controller;


import com.perfulandia.vendedores.dto.VendedoresDTO;
import com.perfulandia.vendedores.services.Vendedoresservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedoresController {

    @Autowired
    private Vendedoresservices service;

    @PostMapping
    public ResponseEntity<VendedoresDTO> crear(@RequestBody VendedoresDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendedoresDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedoresDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedoresDTO> actualizar(@PathVariable Integer id, @RequestBody VendedoresDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
