package com.fundev.adt.command;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import com.fundev.adt.api.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class PatientTest {
    private static AggregateTestFixture<Patient> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(Patient.class);
    }

    @Test
    public void createPatient() {
        // TEST: command handler business logic
        UUID patientId = UUID.randomUUID();
        String firstName = "Pietro";
        String lastName = "Ghezzi";
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        CommandPatientCreate commandPatientCreate = new CommandPatientCreate(patientId, firstName, lastName, birthDate, "");
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, firstName, lastName, birthDate, "");
        fixture.givenNoPriorActivity().when(commandPatientCreate).expectSuccessfulHandlerExecution()
                .expectEvents(eventPatientCreated);
    }

    @Test
    public void updatePatient() {
        // TEST: command handler business logic
        UUID patientId = UUID.randomUUID();
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, "Romelu", "Lukaku", birthDate, "");
        CommandPatientUpdate commandPatientUpdate = new CommandPatientUpdate(0L, patientId, "Zlatan", "Ibrahimovic", birthDate);
        EventPatientUpdated eventPatientUpdated = new EventPatientUpdated(patientId, "Zlatan", "Ibrahimovic", birthDate);
        fixture.given(eventPatientCreated).when(commandPatientUpdate).expectSuccessfulHandlerExecution()
                .expectEvents(eventPatientUpdated);
    }

    @Test
    public void deletePatient() {
        // TEST: command handler business logic
        UUID patientId = UUID.randomUUID();
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, "Romelu", "Lukaku", birthDate, "");
        CommandPatientDelete commandPatientDelete = new CommandPatientDelete(patientId);
        EventPatientDeleted eventPatientDeleted = new EventPatientDeleted(patientId);

        fixture.given(eventPatientCreated).when(commandPatientDelete).expectSuccessfulHandlerExecution()
                .expectEvents(eventPatientDeleted).expectMarkedDeleted();
    }

    @Test
    public void admitPatient() {
        UUID patientId = UUID.randomUUID();
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        PatientAdmission patientAdmission = new PatientAdmission(EpisodeOfCareStatus.ACTIVE, new Date());
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, "Romelu", "Lukaku", birthDate, "");
        CommandPatientAdmit commandPatientAdmit = new CommandPatientAdmit(patientId, patientAdmission);
        EventPatientAdmitted eventPatientAdmitted = new EventPatientAdmitted(patientId, patientAdmission);

        fixture.given(eventPatientCreated).when(commandPatientAdmit).expectEvents(eventPatientAdmitted).expectSuccessfulHandlerExecution();
    }

    @Test
    void dischargePatient() {
        UUID patientId = UUID.randomUUID();
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        Date end = new Date();
        PatientAdmission patientAdmission = new PatientAdmission(EpisodeOfCareStatus.ACTIVE, new Date());

        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, "Romelu", "Lukaku", birthDate, "");
        EventPatientAdmitted eventPatientAdmitted = new EventPatientAdmitted(patientId, patientAdmission);

        CommandPatientDischarge commandPatientDischarge = new CommandPatientDischarge(patientId, patientAdmission.getEpisodeOfCareId(), end);
        EventPatientDischarged eventPatientDischarged = new EventPatientDischarged(patientId, patientAdmission.getEpisodeOfCareId(), end);

        fixture.given(eventPatientCreated, eventPatientAdmitted).when(commandPatientDischarge).expectEvents(eventPatientDischarged).expectSuccessfulHandlerExecution();
    }
}