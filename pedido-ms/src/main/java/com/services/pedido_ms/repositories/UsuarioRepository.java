package com.services.pedido_ms.repositories;

import com.services.pedido_ms.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
