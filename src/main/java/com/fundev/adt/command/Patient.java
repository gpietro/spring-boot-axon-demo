package com.fundev.adt.command;

import com.fundev.adt.coreapi.CommandPatientCreate;
import com.fundev.adt.coreapi.EventPatientCreated;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Patient {

    private static final Logger logger = LoggerFactory.getLogger(Patient.class);

    @AggregateIdentifier
    private UUID patientId;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Patient() {
        // Required By Axon
    }

    @CommandHandler
    public Patient(CommandPatientCreate command) {
        logger.debug("handling {}", command);
        // Business logic
        apply(new EventPatientCreated(command.getPatientId(), command.getFirstName(), command.getLastName(), command.getBirthDate()));
    }

    @EventSourcingHandler
    public void on(EventPatientCreated event) {
        logger.debug("applying {}", event);
        patientId = event.getPatientId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        birthDate = event.getBirthDate();
        logger.debug("new patient: {}", this.toString());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
