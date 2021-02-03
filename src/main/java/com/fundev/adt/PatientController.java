package com.fundev.adt;

import com.fundev.adt.coreapi.*;
import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import com.fundev.adt.query.PatientView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    // TODO: move to EpisodeOfCareController?
    @PostMapping("{id}/admit")
    public CompletableFuture<PatientView> admit(@PathVariable(value = "id") String patientId) {
        // TODO: add wardId
        return commandGateway.send(new CommandPatientAdmit(UUID.randomUUID(), UUID.fromString(patientId), EpisodeOfCareStatus.ACTIVE, new Date(), null));
    }

    @PostMapping("{episodeOfCareId}/dismiss")
    public CompletableFuture<UUID> dismiss(@PathVariable(value = "id") String patientId, @PathVariable(value="episodeOfCareId") String episodeOfCareId) {
        // TODO: add wardId
        return commandGateway.send(new CommandPatientDismiss(UUID.fromString(episodeOfCareId), UUID.fromString(patientId), new Date()));
    }

//    @PutMapping("/patients/{id}")
//    public ResponseEntity<PatientView> update(@PathVariable(value = "id") Long patientId, @RequestBody PatientView patientViewData) throws ResourceNotFoundException {
//        PatientView patientView = patientViewRepository.findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
//
//        patientView.setFirstName(patientViewData.getFirstName());
//        patientView.setLastName(patientViewData.getLastName());
//        patientView.setBirthDate(patientViewData.getBirthDate());
//        final PatientView updatedPatientView = patientViewRepository.save(patientView);
//        return ResponseEntity.ok(updatedPatientView);
//    }

//    @DeleteMapping("/patients/{id}")
//    public Map<String, Boolean> delete(@PathVariable(value = "id") Long patientId)
//            throws ResourceNotFoundException {
//        PatientView patientView = patientViewRepository.findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
//
//        patientViewRepository.delete(patientView);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}
