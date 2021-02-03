package com.fundev.adt.query;

import com.fundev.adt.coreapi.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.SequenceNumber;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientProjector {
    private final QueryPatientRepository repository;

    public PatientProjector(QueryPatientRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(EventPatientCreated event) {
        PatientView patientView = new PatientView(event.getPatientId(), event.getFirstName(), event.getLastName(), event.getBirthDate());
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

    @EventHandler
    public void on(EventPatientAdmitted event) {
        // TODO: update patient view with event.getStatus();
        PatientView patientView = repository.findById(event.getPatientId()).orElseThrow();
        patientView.setStatus(event.getStatus());
        patientView.setStart(event.getStart());
        repository.save(patientView);
    }

    @EventHandler
    public void on(EventPatientDismissed event) {
        // TODO: update patient view with event.getStatus();
        PatientView patientView = repository.findById(event.getPatientId()).orElseThrow();
        patientView.setStatus(event.getStatus());
        patientView.setEnd(event.getEnd());
        repository.save(patientView);
    }

    @QueryHandler
    public Optional<PatientView> handle(QueryPatientFind query) {
        Optional<PatientView> patientView = repository.findById(query.getPatientId());
        return patientView;
    }
}
