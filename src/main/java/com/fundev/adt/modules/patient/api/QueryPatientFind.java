package com.fundev.adt.modules.patient.api;

import java.util.UUID;

public class QueryPatientFind {
    private UUID patientId;

    public QueryPatientFind(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getPatientId() {
        return patientId;
    }
}
