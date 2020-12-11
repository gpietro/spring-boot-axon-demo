package com.fundev.adt;

import com.fundev.adt.coreapi.CommandPatientCreate;
import com.fundev.adt.coreapi.QueryPatientFind;
import com.fundev.adt.query.PatientView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public PatientController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

//    @GetMapping("/patients")
//    public List<PatientView> getPatients() {
//        return patientViewRepository.findAll();
//    }

    @GetMapping("/patients/{id}")
    public CompletableFuture<PatientView> getById(@PathVariable(value = "id") String patientId) {
        return queryGateway.query(new QueryPatientFind(UUID.fromString(patientId)), ResponseTypes.instanceOf(PatientView.class));
    }

    @PostMapping("/patients")
    public CompletableFuture<UUID> create(@RequestBody PatientView patientView) {
        return commandGateway.send(new CommandPatientCreate(UUID.randomUUID(), patientView.getFirstName(), patientView.getLastName(), patientView.getBirthDate()));
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
