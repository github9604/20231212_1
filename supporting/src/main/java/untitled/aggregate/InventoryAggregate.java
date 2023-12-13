package untitled.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import untitled.command.*;
import untitled.event.*;
import untitled.query.*;

//<<< DDD / Aggregate Root
@Aggregate
@Data
@ToString
public class InventoryAggregate {

    @AggregateIdentifier
    private Long id;

    private Integer qty;
    private String name;

    public InventoryAggregate() {}

    //<<< Etc / ID Generation
    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    //>>> Etc / ID Generation

    //<<< EDA / Event Sourcing

    @EventSourcingHandler
    public void on(InventoryDecreasedEvent event) {
        //TODO: business logic here

    }

    @EventSourcingHandler
    public void on(InventoryIncreasedEvent event) {
        //TODO: business logic here

    }
    //>>> EDA / Event Sourcing

}
//>>> DDD / Aggregate Root
