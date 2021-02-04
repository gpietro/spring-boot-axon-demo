package com.fundev.adt.modules.patient.api;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.serialization.Revision;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
@Revision("2.0")
public class EventPatientCreated {
    UUID patientId;
    String firstName;
    String lastName;
    Date birthDate;
    String address;
}
