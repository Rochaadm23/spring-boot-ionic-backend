package com.rsecinformation.cursomc.repositories;

import com.rsecinformation.cursomc.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
