package com.fundev.adt.query;

import com.fundev.adt.api.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientTreatmentViewProjector {
    private final PatientTreatmentViewQueryRepository repository;

    public PatientTreatmentViewProjector(PatientTreatmentViewQueryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(EventPatientAdmitted event) {
        PatientTreatmentView patientTreatmentView = new PatientTreatmentView(
                event.getPatientAdmission().getEpisodeOfCareId(),
                event.getPatientId(),
                event.getPatientAdmission().getStatus(),
                event.getPatientAdmission().getStart(),
                null);
        repository.save(patientTreatmentView);
    }

    @EventHandler
    public void on(EventPatientDischarged event) {
        PatientTreatmentView patientTreatmentView = repository.findById(event.getEpisodeOfCareId()).orElseThrow();
        patientTreatmentView.setEnd(event.getEnd());
        patientTreatmentView.setStatus(event.getStatus());
        repository.save(patientTreatmentView);
    }

    @QueryHandler
    public List<PatientTreatmentView> handle(QueryPatientTreatmentFindByPatientId query) {
        return repository.findByPatientId(query.getPatientId());
    }
}