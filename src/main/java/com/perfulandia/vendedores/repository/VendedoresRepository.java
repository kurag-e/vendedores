package com.perfulandia.vendedores.repository;


import com.perfulandia.vendedores.models.Vendedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedoresRepository extends JpaRepository<Vendedores, Integer> {
}

