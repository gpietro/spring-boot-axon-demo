package com.fundev.adt.command;

import com.fundev.adt.coreapi.*;
import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class PatientTest {
    private static AggregateTestFixture<Patient> fixture;

    @BeforeAll
    static void setUp() {
        fixture = new AggregateTestFixture<>(Patient.class);
    }

    @Test
    public void testPatientCreate() {
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
    public void testPatientUpdate() {
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
    public void testPatientDelete() {
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
    public void testPatientAdmission() {
        UUID episodeOfCareId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        EpisodeOfCareStatus status = EpisodeOfCareStatus.ACTIVE; // TODO add state machine validation?
        Date start = new DateTime(2021, 1, 27, 10, 0, 0, 0).toDate();
        Date end = new DateTime(2021, 2, 10, 10, 0, 0, 0).toDate();
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, "Daniel", "Maldini", birthDate, "");
        CommandPatientAdmit commandPatientAdmit = new CommandPatientAdmit(episodeOfCareId, patientId, status, start, end);
        EventPatientAdmitted eventPatientAdmitted = new EventPatientAdmitted(episodeOfCareId, patientId, status, start, end);

        fixture.given(eventPatientCreated).when(commandPatientAdmit).expectSuccessfulHandlerExecution().expectEvents(eventPatientAdmitted);
    }

    @Test
    void testPatientDischarge() {
        UUID episodeOfCareId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        EpisodeOfCareStatus status = EpisodeOfCareStatus.ACTIVE;
        Date start = new DateTime(2021, 1, 27, 10, 0, 0, 0).toDate();
        Date end = new DateTime(2021, 2, 10, 10, 0, 0, 0).toDate();
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, "Daniel", "Maldini", birthDate, "");
        EventPatientAdmitted eventPatientAdmitted = new EventPatientAdmitted(episodeOfCareId, patientId, status, start, end);
        CommandPatientDismiss commandPatientDismiss = new CommandPatientDismiss(episodeOfCareId, patientId, end);
        EventPatientDismissed eventPatientDismissed = new EventPatientDismissed(episodeOfCareId, patientId, end);


        fixture.given(eventPatientCreated, eventPatientAdmitted).when(commandPatientDismiss).expectSuccessfulHandlerExecution().expectEvents(eventPatientDismissed);
    }

}