package com.services.pedido_ms.repositories;

import com.services.pedido_ms.entities.PedidoItem;
import com.services.pedido_ms.entities.PedidoItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, PedidoItemId> {
}
