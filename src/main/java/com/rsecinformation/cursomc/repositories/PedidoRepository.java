package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
