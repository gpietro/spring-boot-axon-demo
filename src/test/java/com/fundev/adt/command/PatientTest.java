package com.fundev.adt.command;

import com.fundev.adt.coreapi.CommandPatientCreate;
import com.fundev.adt.coreapi.EventPatientCreated;
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
    public void testFirstFixture() {
        UUID patientId = UUID.randomUUID();
        String firstName = "Pietro";
        String lastName = "Ghezzi";
        Date birthDate = new DateTime(1986, 6, 28, 0, 0, 0, 0).toDate();
        CommandPatientCreate commandPatientCreate = new CommandPatientCreate(patientId, firstName, lastName, birthDate);
        EventPatientCreated eventPatientCreated = new EventPatientCreated(patientId, firstName, lastName, birthDate);
        fixture.givenNoPriorActivity().when(commandPatientCreate).expectSuccessfulHandlerExecution()
                .expectEvents(eventPatientCreated);
    }
}