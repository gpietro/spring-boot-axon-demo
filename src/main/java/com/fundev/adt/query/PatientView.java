package com.fundev.adt.query;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class PatientView {
    @Id
    private UUID patientId;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public PatientView() {

    }

    public PatientView(UUID patientId, String firstName, String lastName, Date birthDate) {
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