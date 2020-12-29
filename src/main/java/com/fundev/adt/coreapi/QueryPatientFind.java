package com.fundev.adt.coreapi;

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
