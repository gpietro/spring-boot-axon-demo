package com.fundev.adt.coreapi;

import org.axonframework.commandhandling.RoutingKey;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateVersion;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

// immutable
public class CommandPatientUpdate {
    @TargetAggregateVersion
    private final Long version;
    @TargetAggregateIdentifier
    private UUID patientId;
    private String firstName;
    private String lastName;
    private Date birthDate;


    public CommandPatientUpdate(Long version, UUID patientId, String firstName, String lastName, Date birthDate) {
        this.version = version;
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Long getVersion() {
        return version;
    }
}