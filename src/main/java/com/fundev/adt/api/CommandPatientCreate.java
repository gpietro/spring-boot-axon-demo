package com.fundev.adt.api;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CommandPatientCreate {
    @TargetAggregateIdentifier
    UUID patientId;
    String firstName;
    String lastName;
    Date birthDate;
    String address;
}