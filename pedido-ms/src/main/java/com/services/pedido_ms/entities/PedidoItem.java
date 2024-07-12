package com.services.pedido_ms.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_item")
public class PedidoItem {

    @EmbeddedId
    private PedidoItemId id;
    private Integer quantidade;

    public PedidoItem() {
    }

    public PedidoItem(PedidoItemId id, Integer quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public PedidoItemId getId() {
        return id;
    }

    public void setId(PedidoItemId id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
