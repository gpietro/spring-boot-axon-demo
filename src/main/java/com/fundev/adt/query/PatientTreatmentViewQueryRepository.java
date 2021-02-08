package com.fundev.adt.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientTreatmentViewQueryRepository extends JpaRepository<PatientTreatmentView, UUID> {
    List<PatientTreatmentView> findByPatientId(UUID patientId);
}