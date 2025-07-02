package com.perfulandia.vendedores.controller;

import com.perfulandia.vendedores.dto.VendedoresDTO;
import com.perfulandia.vendedores.services.Vendedoresservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/vendedores")
@CrossOrigin(origins = "*")
public class VendedoresController {

    @Autowired
    private Vendedoresservices service;

   
    @PostMapping
    public VendedoresDTO crear(@RequestBody VendedoresDTO dto) {
        VendedoresDTO creado = service.crear(dto);
        creado.add(linkTo(methodOn(VendedoresController.class).obtenerHATEOAS(creado.getId())).withSelfRel());
        creado.add(linkTo(methodOn(VendedoresController.class).obtenerTodosHATEOAS()).withRel("todos"));
        return creado;
    }

   
    @GetMapping("/hateoas/{id}")
    public VendedoresDTO obtenerHATEOAS(@PathVariable Integer id) {
        VendedoresDTO dto = service.obtenerPorId(id);
        if (dto != null) {
            dto.add(linkTo(methodOn(VendedoresController.class).obtenerHATEOAS(id)).withSelfRel());
            dto.add(linkTo(methodOn(VendedoresController.class).obtenerTodosHATEOAS()).withRel("todos"));
            dto.add(linkTo(methodOn(VendedoresController.class).eliminar(id)).withRel("eliminar"));
        }
        return dto;
    }

    @GetMapping("/hateoas")
    public List<VendedoresDTO> obtenerTodosHATEOAS() {
        List<VendedoresDTO> lista = service.listar();
        for (VendedoresDTO dto : lista) {
            dto.add(linkTo(methodOn(VendedoresController.class).obtenerHATEOAS(dto.getId())).withSelfRel());
        }
        return lista;
    }

    @GetMapping("/{id}")
    public VendedoresDTO getVendedor(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public VendedoresDTO actualizar(@PathVariable Integer id, @RequestBody VendedoresDTO dto) {
        VendedoresDTO actualizado = service.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(VendedoresController.class).obtenerHATEOAS(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(VendedoresController.class).obtenerTodosHATEOAS()).withRel("todos"));
        return actualizado;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
