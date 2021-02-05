package com.fundev.adt.modules.patient.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@Getter
@AllArgsConstructor
public class EventPatientUpdated {
    UUID patientId;
    String firstName;
    String lastName;
    Date birthDate;
}
