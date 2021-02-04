package com.fundev.adt.modules.patient.api;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateVersion;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CommandPatientUpdate {
    @TargetAggregateVersion
    Long version;
    @TargetAggregateIdentifier
    UUID patientId;
    String firstName;
    String lastName;
    Date birthDate;
}