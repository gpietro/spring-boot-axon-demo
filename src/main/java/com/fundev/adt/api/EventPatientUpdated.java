package com.fundev.adt.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class EventPatientUpdated {
    UUID patientId;
    String firstName;
    String lastName;
    Date birthDate;
}