package com.fundev.adt.command;

import com.fundev.adt.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.conflictresolution.ConflictResolver;
import org.axonframework.eventsourcing.conflictresolution.Conflicts;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
public class Patient {

    private static final List<Class<? extends Object>> PATIENT_CHANGING_EVENT_TYPES = Arrays
            .asList(EventPatientUpdated.class);

    // TODO: study more in deep
    private static Predicate<List<DomainEventMessage<?>>> patientChangingEventMatching() {
        return Conflicts.payloadMatching(event -> PATIENT_CHANGING_EVENT_TYPES.stream()
                .anyMatch(type -> type.isAssignableFrom(event.getClass())));
    }

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
        // TODO patient validation
        apply(new EventPatientCreated(command.getPatientId(), command.getFirstName(), command.getLastName(), command.getBirthDate(), command.getAddress()));
    }

    @CommandHandler
    void on(CommandPatientUpdate command, ConflictResolver conflictResolver) {
        conflictResolver.detectConflicts(patientChangingEventMatching());
        logger.debug("handling {}", command);
        // TODO patient validation
        apply(new EventPatientUpdated(command.getPatientId(), command.getFirstName(), command.getLastName(), command.getBirthDate()));
    }

    @CommandHandler
    void on(CommandPatientDelete command) {
        logger.debug("handling {}", command);
        apply(new EventPatientDeleted(command.getPatientId()));
    }

    @EventSourcingHandler
    public void on(EventPatientCreated event) {
        logger.debug("applying {}", event);
        patientId = event.getPatientId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        birthDate = event.getBirthDate();
        logger.debug("created patient: {}", this.toString());
    }

    @EventSourcingHandler
    public void on(EventPatientUpdated event) {
        logger.debug("applying {}", event);
        firstName = event.getFirstName();
        lastName = event.getLastName();
        birthDate = event.getBirthDate();
        logger.debug("updated patient: {}", this.toString());
    }

    @EventSourcingHandler
    public void on(EventPatientDeleted event) {
        markDeleted();
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
