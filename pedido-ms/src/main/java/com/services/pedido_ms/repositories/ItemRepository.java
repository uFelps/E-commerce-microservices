package com.services.pedido_ms.repositories;

import com.services.pedido_ms.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
