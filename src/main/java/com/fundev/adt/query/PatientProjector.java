package com.fundev.adt.query;

import com.fundev.adt.coreapi.EventPatientCreated;
import com.fundev.adt.coreapi.QueryPatientFind;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientProjector {
    private final PatientViewRepository repository;

    public PatientProjector(PatientViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(EventPatientCreated event) {
        PatientView patientView = new PatientView(event.getPatientId(), event.getFirstName(), event.getLastName(), event.getBirthDate());
        repository.save(patientView);
    }

    @QueryHandler
    public Optional<PatientView> handle(QueryPatientFind query) {
        return repository.findById(query.getPatientId());
    }
}
