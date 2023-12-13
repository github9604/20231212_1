package untitled.api;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import untitled.aggregate.*;
import untitled.command.*;

//<<< Clean Arch / Inbound Adaptor
@RestController
public class InventoryController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public InventoryController(
        CommandGateway commandGateway,
        QueryGateway queryGateway
    ) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @Autowired
    EventStore eventStore;

    @GetMapping(value = "/inventories/{id}/events")
    public ResponseEntity getEvents(@PathVariable("id") String id) {
        ArrayList resources = new ArrayList<InventoryAggregate>();
        eventStore.readEvents(id).asStream().forEach(resources::add);

        CollectionModel<InventoryAggregate> model = CollectionModel.of(
            resources
        );

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    EntityModel<InventoryAggregate> hateoas(InventoryAggregate resource) {
        EntityModel<InventoryAggregate> model = EntityModel.of(resource);

        model.add(Link.of("/inventories/" + resource.getId()).withSelfRel());

        model.add(
            Link
                .of("/inventories/" + resource.getId() + "/events")
                .withRel("events")
        );

        return model;
    }
}
//>>> Clean Arch / Inbound Adaptor
