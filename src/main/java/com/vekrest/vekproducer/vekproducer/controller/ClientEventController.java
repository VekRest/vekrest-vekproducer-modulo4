package com.vekrest.vekproducer.vekproducer.controller;

import com.vekrest.vekproducer.vekproducer.controller.adapter.ClientControllerAdapter;
import com.vekrest.vekproducer.vekproducer.controller.dto.request.ClientRequest;
import com.vekrest.vekproducer.vekproducer.entities.Client;
import com.vekrest.vekproducer.vekproducer.event.ClientEventProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ClientEventController {
    private final ClientEventProducer producer;

    public ClientEventController(ClientEventProducer producer) {
        this.producer = producer;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/client")
    public void register(@Valid @RequestBody ClientRequest request) throws Exception {
        Client client = ClientControllerAdapter.cast(request);
        producer.send(client);
    }
}
