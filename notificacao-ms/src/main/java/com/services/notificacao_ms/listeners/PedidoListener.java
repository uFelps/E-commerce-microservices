package com.services.notificacao_ms.listeners;
import com.services.notificacao_ms.dtos.PedidoNotificacaoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    @RabbitListener(queues = "notificacao.pedido")
    public void recebeMensagem(@Payload PedidoNotificacaoDTO notificacao){
        String mensagem = """
                Pedido Id: %s
                Total: %s
                Usuario Id R$: %s
                Email: %s
                Mensagem: %s
                """.formatted(notificacao.pedidoId(),
                notificacao.total(),
                notificacao.usuarioId(),
                notificacao.email(),
                notificacao.mensagem());
        System.out.println("Recebi a mensagem:" + mensagem);
    }
}
