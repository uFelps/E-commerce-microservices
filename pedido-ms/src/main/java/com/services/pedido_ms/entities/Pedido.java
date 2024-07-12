package com.services.pedido_ms.entities;

import com.services.pedido_ms.entities.enuns.PedidoStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime criadoEm;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "id.pedido")
    private List<PedidoItem> itens;


    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime criadoEm, BigDecimal total, PedidoStatus status) {
        this.id = id;
        this.criadoEm = criadoEm;
        this.total = total;
        this.status = status;
    }

    public Pedido(Long id, LocalDateTime criadoEm, BigDecimal total, PedidoStatus status, Usuario usuario) {
        this.status = status;
        this.total = total;
        this.criadoEm = criadoEm;
        this.id = id;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
