package com.fundev.adt.modules.patient.api;

import java.util.UUID;

// immutable
public class EventPatientDeleted {
    private UUID patientId;

    public EventPatientDeleted(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getPatientId() {
        return patientId;
    }
}
