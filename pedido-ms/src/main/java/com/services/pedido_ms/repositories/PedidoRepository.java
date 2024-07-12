package com.services.pedido_ms.repositories;

import com.services.pedido_ms.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
