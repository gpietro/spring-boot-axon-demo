package com.fundev.adt.modules.patient.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class PatientView {
    @Id
    private UUID patientId;
    private Long version;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public PatientView(UUID patientId, String firstName, String lastName, Date birthDate) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
