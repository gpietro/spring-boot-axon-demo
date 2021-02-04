package com.fundev.adt.modules.episodeOfCare.controller.v1;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import com.fundev.adt.modules.episodeOfCare.api.CommandEpisodeOfCareAdmit;
import com.fundev.adt.modules.episodeOfCare.api.CommandEpisodeOfCareDischarge;
import com.fundev.adt.modules.episodeOfCare.api.QueryEpisodeOfCareFind;
import com.fundev.adt.modules.episodeOfCare.query.EpisodeOfCareView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/episodes-of-care")
public class EpisodeOfCareController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public EpisodeOfCareController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("{id}")
    public CompletableFuture<EpisodeOfCareView> getById(@PathVariable(value = "id") String episodeOfCareId) {
        return queryGateway.query(new QueryEpisodeOfCareFind(UUID.fromString(episodeOfCareId)), ResponseTypes.instanceOf(EpisodeOfCareView.class));
    }

    @PostMapping
    public CompletableFuture<UUID> create(@RequestParam String patientId) {
        return commandGateway.send(new CommandEpisodeOfCareAdmit(UUID.randomUUID(), UUID.fromString(patientId), EpisodeOfCareStatus.ACTIVE, new Date(), null));
    }

    // TODO: remove patientId
    @PostMapping("{id}/dismiss")
    public CompletableFuture<UUID> dismiss(@PathVariable(value = "id") String episodeOfCareId, @RequestParam(value="patientId") String patientId, @RequestParam(value="version") Long version) {
        return commandGateway.send(new CommandEpisodeOfCareDischarge(UUID.fromString(episodeOfCareId), UUID.fromString(patientId), version, new Date()));
    }
}
