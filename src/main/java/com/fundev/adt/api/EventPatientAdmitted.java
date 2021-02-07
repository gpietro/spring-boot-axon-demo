package com.fundev.adt.api;

import com.fundev.adt.command.PatientAdmission;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class EventPatientAdmitted {
    UUID patientId;
    PatientAdmission patientAdmission;
}