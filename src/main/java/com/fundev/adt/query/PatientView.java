package com.fundev.adt.query;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

// TODO: Auditing (javers, jooq)

@Entity
public class PatientView {
    @Id
    private UUID patientId;
    private Long version;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Date start;
    private Date end;

    private EpisodeOfCareStatus status;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public void setStatus(EpisodeOfCareStatus status) {
        this.status = status;
    }

    public EpisodeOfCareStatus getStatus() {
        return status;
    }

    public void setStart(Date start) {
        this.start = start;
    }


    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }
}
