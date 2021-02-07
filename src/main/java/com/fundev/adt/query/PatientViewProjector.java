package com.fundev.adt.query;

import com.fundev.adt.api.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.SequenceNumber;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientViewProjector {
    private final PatientViewQueryRepository repository;

    public PatientViewProjector(PatientViewQueryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(EventPatientCreated event) {
        PatientView patientView = new PatientView(event.getPatientId(), event.getFirstName(), event.getLastName(), event.getBirthDate());
        patientView.setVersion(1L);
        repository.save(patientView);
    }

    @EventHandler
    public void on(EventPatientUpdated event, @SequenceNumber Long version) {
        PatientView patientView = repository.findById(event.getPatientId()).orElseThrow();
        patientView.setFirstName(event.getFirstName());
        patientView.setLastName(event.getLastName());
        patientView.setBirthDate(event.getBirthDate());
        patientView.setVersion(version);
        repository.save(patientView);
    }

    @EventHandler
    public void on(EventPatientDeleted event) {
        repository.deleteById(event.getPatientId());
    }

    @QueryHandler
    public Optional<PatientView> handle(QueryPatientFindById query) {
        return repository.findById(query.getPatientId());
    }

    @QueryHandler
    public List<PatientView> handle(QueryPatientFindAll query) {
        return repository.findAll();
    }
}