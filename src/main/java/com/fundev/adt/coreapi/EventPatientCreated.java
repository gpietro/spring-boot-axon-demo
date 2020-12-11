package com.fundev.adt.coreapi;

import java.util.Date;
import java.util.UUID;

public class EventPatientCreated {
    private UUID patientId;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public EventPatientCreated(UUID patientId, String firstName, String lastName, Date birthDate) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
