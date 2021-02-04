package com.fundev.adt.modules.patient.api;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CommandPatientDelete {
    @TargetAggregateIdentifier
    UUID patientId;
}