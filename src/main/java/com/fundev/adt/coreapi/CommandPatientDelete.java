package com.fundev.adt.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

// immutable
public class CommandPatientDelete {
    @TargetAggregateIdentifier
    private UUID patientId;

    public CommandPatientDelete(UUID patientId) {
        this.patientId = patientId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getPatientId() {
        return patientId;
    }
}