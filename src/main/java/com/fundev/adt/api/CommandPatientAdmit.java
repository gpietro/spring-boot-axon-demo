package com.fundev.adt.api;

import com.fundev.adt.command.PatientAdmission;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
@AllArgsConstructor
public class CommandPatientAdmit {
    @TargetAggregateIdentifier
    UUID patientId;
    PatientAdmission patientAdmission;
}