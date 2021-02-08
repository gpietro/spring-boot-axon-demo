package com.fundev.adt;

import com.fundev.adt.api.*;
import com.fundev.adt.command.PatientAdmission;
import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import com.fundev.adt.query.PatientTreatmentView;
import com.fundev.adt.query.PatientView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    // TODO: split read and write?

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public PatientController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    // TODO: define form data
    @PostMapping
    public CompletableFuture<UUID> create(@RequestBody PatientView patient) {
        return commandGateway.send(new CommandPatientCreate(UUID.randomUUID(), patient.getFirstName(), patient.getLastName(), patient.getBirthDate(), "Route 66"));
    }

    // TODO: replace patientView with post data model
    @PutMapping("{id}")
    public CompletableFuture<UUID> update(@PathVariable(value = "id") String patientId, @RequestBody PatientView patient) {
        return commandGateway.send(new CommandPatientUpdate(patient.getVersion(), UUID.fromString(patientId), patient.getFirstName(), patient.getLastName(), patient.getBirthDate()));
    }

    @DeleteMapping("{id}")
    public CompletableFuture<UUID> delete(@PathVariable(value = "id") String patientId) {
        // add flag deleted
        return commandGateway.send(new CommandPatientDelete(UUID.fromString(patientId)));
    }

    @PostMapping("{id}/admit")
    public CompletableFuture<UUID> admit(@PathVariable(value = "id") String patientId) {
        PatientAdmission patientAdmission = new PatientAdmission( EpisodeOfCareStatus.ACTIVE, new Date());
        return commandGateway.send(new CommandPatientAdmit(UUID.fromString(patientId), patientAdmission));
    }

    @PostMapping("{id}/discharge")
    public CompletableFuture<UUID> discharge(@PathVariable(value = "id") String patientId, @RequestParam String episodeOfCareId) {
        return commandGateway.send(new CommandPatientDischarge(UUID.fromString(patientId), UUID.fromString(episodeOfCareId), new Date()));
    }

    /**
     * QUERY
     */
    @GetMapping("{id}")
    public CompletableFuture<PatientView> getPatientById(@PathVariable(value = "id") String patientId) {
        return queryGateway.query(new QueryPatientFindById(UUID.fromString(patientId)), ResponseTypes.instanceOf(PatientView.class));
    }

    @GetMapping
    public CompletableFuture<List<PatientView>> getPatients() {
        return queryGateway.query(new QueryPatientFindAll(), ResponseTypes.multipleInstancesOf(PatientView.class));
    }

    @GetMapping("{id}/treatments")
    public CompletableFuture<List<PatientTreatmentView>> treatments(@PathVariable(value = "id") String patientId) {
        return queryGateway.query(new QueryPatientTreatmentFindByPatientId(UUID.fromString(patientId)), ResponseTypes.multipleInstancesOf(PatientTreatmentView.class));
    }
}