package com.services.pedido_ms.services;

import com.services.pedido_ms.dtos.ItemDTO;
import com.services.pedido_ms.dtos.PedidoDTO;
import com.services.pedido_ms.dtos.PedidoItemDTO;
import com.services.pedido_ms.dtos.PedidoNotificacaoDTO;
import com.services.pedido_ms.entities.Item;
import com.services.pedido_ms.entities.Pedido;
import com.services.pedido_ms.entities.PedidoItem;
import com.services.pedido_ms.entities.PedidoItemId;
import com.services.pedido_ms.entities.enuns.PedidoStatus;
import com.services.pedido_ms.repositories.ItemRepository;
import com.services.pedido_ms.repositories.PedidoItemRepository;
import com.services.pedido_ms.repositories.PedidoRepository;
import com.services.pedido_ms.services.exceptions.DadoNaoEncontradoException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO dto) {

        Pedido pedido = new Pedido(null, LocalDateTime.now(), null, PedidoStatus.CRIADO, usuarioService.buscarReferenciaPeloId(dto.usuarioId()));

        pedido.setTotal(dto.itens().stream()
                .map(
                        x -> x.item().preco().multiply(BigDecimal.valueOf(x.quantidade()))
                )
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

        repository.save(pedido);


        List<PedidoItemDTO> itens = dto.itens().stream().map(x -> {
                    Item item = itemRepository.save(new Item(null, x.item().nome(), x.item().preco()));

                    pedidoItemRepository.save(new PedidoItem(
                            new PedidoItemId(pedido, item), x.quantidade()
                    ));

                    return new PedidoItemDTO(
                            new ItemDTO(item.getId(), item.getNome(), item.getPreco()), x.quantidade());
                }
        ).toList();


        PedidoDTO novoPedido = new PedidoDTO(pedido.getId(), pedido.getCriadoEm(), pedido.getTotal(), pedido.getStatus(), pedido.getUsuario().getId(), itens);

        rabbitTemplate.convertAndSend("ex.pedidos-criados", "",
                new PedidoNotificacaoDTO(
                        pedido.getId(),
                        pedido.getTotal(),
                        dto.usuarioId(),
                        pedido.getUsuario().getEmail(),
                        "O pedido foi feito com sucesso! Efetue o pagamento para confirma-lo."));

        return novoPedido;
    }

    public PedidoDTO buscarPedidoPorId(Long id) {


        Pedido pedido = repository.findById(id).orElseThrow(() -> new DadoNaoEncontradoException("Pedido não encontrado! ID:" + id));

        List<PedidoItemDTO> itens = pedido.getItens().stream().map(x -> {
            return new PedidoItemDTO(new ItemDTO(
                    x.getId().getItem().getId(),
                    x.getId().getItem().getNome(),
                    x.getId().getItem().getPreco()), x.getQuantidade());
        }).toList();

        return new PedidoDTO(
                pedido.getId(),
                pedido.getCriadoEm(),
                pedido.getTotal(),
                pedido.getStatus(),
                pedido.getUsuario().getId(),
                itens
        );
    }
}
