package com.fundev.adt.api;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CommandPatientDischarge {
    @TargetAggregateIdentifier
    UUID patientId;
    UUID episodeOfCareId;
    Date end;
}