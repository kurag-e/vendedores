package com.perfulandia.vendedores.controller;

import com.perfulandia.vendedores.dto.VendedoresDTO;
import com.perfulandia.vendedores.services.Vendedoresservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/vendedores")
public class VendedoresController {

    @Autowired
    private Vendedoresservices service;

    @PostMapping
    public ResponseEntity<VendedoresDTO> crear(@RequestBody VendedoresDTO dto) {
        VendedoresDTO creado = service.crear(dto);
        creado.add(linkTo(methodOn(VendedoresController.class).obtener(creado.getId())).withSelfRel());
        creado.add(linkTo(methodOn(VendedoresController.class).listar()).withRel("todos-los-vendedores"));
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<VendedoresDTO>> listar() {
        List<VendedoresDTO> lista = service.listar();

        lista.forEach(v ->
            v.add(linkTo(methodOn(VendedoresController.class).obtener(v.getId())).withSelfRel())
        );

        CollectionModel<VendedoresDTO> modelo = CollectionModel.of(lista);
        modelo.add(linkTo(methodOn(VendedoresController.class).listar()).withSelfRel());

        return ResponseEntity.ok(modelo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedoresDTO> obtener(@PathVariable Integer id) {
        VendedoresDTO vendedor = service.obtenerPorId(id);

        vendedor.add(linkTo(methodOn(VendedoresController.class).obtener(id)).withSelfRel());
        vendedor.add(linkTo(methodOn(VendedoresController.class).listar()).withRel("todos-los-vendedores"));

        return ResponseEntity.ok(vendedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedoresDTO> actualizar(@PathVariable Integer id, @RequestBody VendedoresDTO dto) {
        VendedoresDTO actualizado = service.actualizar(id, dto);
        actualizado.add(linkTo(methodOn(VendedoresController.class).obtener(id)).withSelfRel());
        actualizado.add(linkTo(methodOn(VendedoresController.class).listar()).withRel("todos-los-vendedores"));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
