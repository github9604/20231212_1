package untitled.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import untitled.aggregate.*;
import untitled.command.*;
import untitled.event.*;

//<<< Clean Arch / Inbound Adaptor
//<<< EDA / Event Handler
@Service
@ProcessingGroup("supporting")
public class PolicyHandler {

    @Autowired
    CommandGateway commandGateway;

    @EventHandler
    //@DisallowReplay
    public void wheneverOrderPlaced_StartDelivery(
        OrderPlacedEvent orderPlaced
    ) {
        System.out.println(orderPlaced.toString());

        StartDeliveryCommand command = new StartDeliveryCommand();
        //TODO: mapping attributes (anti-corruption)
        commandGateway.send(command);
    }

    @EventHandler
    //@DisallowReplay
    public void wheneverDeliveryStarted_DecreaseInventory(
        DeliveryStartedEvent deliveryStarted
    ) {
        System.out.println(deliveryStarted.toString());

        DecreaseInventoryCommand command = new DecreaseInventoryCommand();
        //TODO: mapping attributes (anti-corruption)
        commandGateway.send(command);
    }

    @EventHandler
    //@DisallowReplay
    public void wheneverOrderCanceled_CancelDelivery(
        OrderCanceledEvent orderCanceled
    ) {
        System.out.println(orderCanceled.toString());

        CancelDeliveryCommand command = new CancelDeliveryCommand();
        //TODO: mapping attributes (anti-corruption)
        commandGateway.send(command);
    }

    @EventHandler
    //@DisallowReplay
    public void wheneverDeliveryCanceled_IncreaseInventory(
        DeliveryCanceledEvent deliveryCanceled
    ) {
        System.out.println(deliveryCanceled.toString());

        IncreaseInventoryCommand command = new IncreaseInventoryCommand();
        //TODO: mapping attributes (anti-corruption)
        commandGateway.send(command);
    }
}
//>>> EDA / Event Handler
//>>> Clean Arch / Inbound Adaptor
