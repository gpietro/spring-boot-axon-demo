package com.fundev.adt.modules.patient;

import com.fundev.adt.modules.patient.api.CommandPatientCreate;
import com.fundev.adt.modules.patient.api.CommandPatientDelete;
import com.fundev.adt.modules.patient.api.CommandPatientUpdate;
import com.fundev.adt.modules.patient.api.QueryPatientFind;
import com.fundev.adt.modules.patient.query.PatientView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public CompletableFuture<PatientView> getById(@PathVariable(value = "id") String patientId) {
        return queryGateway.query(new QueryPatientFind(UUID.fromString(patientId)), ResponseTypes.instanceOf(PatientView.class));
    }

    // TODO: define form data
    @PostMapping
    public CompletableFuture<UUID> create(@RequestBody PatientView patient) {
        return commandGateway.send(new CommandPatientCreate(UUID.randomUUID(), patient.getFirstName(), patient.getLastName(), patient.getBirthDate(), "Via monda 13"));
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
}
