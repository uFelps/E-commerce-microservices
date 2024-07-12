package com.services.pedido_ms.services;

import com.services.pedido_ms.dtos.UsuarioDTO;
import com.services.pedido_ms.entities.Usuario;
import com.services.pedido_ms.repositories.UsuarioRepository;
import com.services.pedido_ms.services.exceptions.DadoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.nome(), dto.cpf(), dto.email());

        usuario = repository.save(usuario);

        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail());
    }

    public Usuario buscarReferenciaPeloId(Long id) {
        try{
            return repository.getReferenceById(id);
        }
        catch (Exception e){
            throw new DadoNaoEncontradoException("Usuario não encontrado! ID: " + id);
        }

    }
}
