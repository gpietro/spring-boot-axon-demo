package com.fundev.adt.coreapi;

import org.axonframework.serialization.Revision;

import java.util.Date;
import java.util.UUID;

// immutable
@Revision("2.0")
public class EventPatientCreated {
    private UUID patientId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;

    public EventPatientCreated(UUID patientId, String firstName, String lastName, Date birthDate, String address) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
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

    public String getAddress() { return address; }
}
