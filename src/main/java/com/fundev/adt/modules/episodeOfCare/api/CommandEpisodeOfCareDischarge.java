package com.fundev.adt.modules.episodeOfCare.api;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CommandEpisodeOfCareDischarge {
    @TargetAggregateIdentifier
    UUID episodeOfCareId;
    UUID patientId;
    Long version;
    Date end;
}