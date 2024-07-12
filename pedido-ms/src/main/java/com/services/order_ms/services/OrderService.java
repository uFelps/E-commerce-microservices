package com.services.order_ms.services;

import com.services.order_ms.dtos.ItemDTO;
import com.services.order_ms.dtos.OrderDTO;
import com.services.order_ms.dtos.OrderItemDTO;
import com.services.order_ms.dtos.OrderNotificationDTO;
import com.services.order_ms.entities.Item;
import com.services.order_ms.entities.Order;
import com.services.order_ms.entities.OrderItem;
import com.services.order_ms.entities.OrderItemId;
import com.services.order_ms.entities.enuns.OrderStatus;
import com.services.order_ms.repositories.ItemRepository;
import com.services.order_ms.repositories.OrderItemRepository;
import com.services.order_ms.repositories.OrderRepository;
import com.services.order_ms.services.exceptions.DataNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public OrderDTO createOrder(OrderDTO dto) {

        Order order = new Order(null, LocalDateTime.now(), null, OrderStatus.CREATED, customerService.getCustomerReferenceById(dto.customerId()));

        order.setTotal(dto.items().stream()
                .map(
                        x -> x.item().price().multiply(BigDecimal.valueOf(x.quantity()))
                )
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

        repository.save(order);


        List<OrderItemDTO> items = dto.items().stream().map(x -> {
                    Item item = itemRepository.save(new Item(null, x.item().name(), x.item().price()));

                    orderItemRepository.save(new OrderItem(
                            new OrderItemId(order, item), x.quantity()
                    ));

                    return new OrderItemDTO(
                            new ItemDTO(item.getId(), item.getName(), item.getPrice()), x.quantity());
                }
        ).toList();


        OrderDTO newPedido = new OrderDTO(order.getId(), order.getCreatedOn(), order.getTotal(), order.getStatus(), order.getUsuario().getId(), items);

        rabbitTemplate.convertAndSend("ex.orders-created", "",
                new OrderNotificationDTO(
                        order.getId(),
                        order.getTotal(),
                        dto.customerId(),
                        order.getUsuario().getEmail(),
                        "The order was successfully placed! Make the payment to confirm it."));

        return newPedido;
    }

    public OrderDTO findOrderById(Long id) {


        Order order = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Order not found! ID:" + id));

        List<OrderItemDTO> itens = order.getItems().stream().map(x -> {
            return new OrderItemDTO(new ItemDTO(
                    x.getId().getItem().getId(),
                    x.getId().getItem().getName(),
                    x.getId().getItem().getPrice()), x.getQuantity());
        }).toList();

        return new OrderDTO(
                order.getId(),
                order.getCreatedOn(),
                order.getTotal(),
                order.getStatus(),
                order.getUsuario().getId(),
                itens
        );
    }
}
