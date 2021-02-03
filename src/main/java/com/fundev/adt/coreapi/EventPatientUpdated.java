package com.fundev.adt.coreapi;

import java.util.Date;
import java.util.UUID;

// immutable
public class EventPatientUpdated {
    private UUID patientId;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public EventPatientUpdated(UUID patientId, String firstName, String lastName, Date birthDate) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
