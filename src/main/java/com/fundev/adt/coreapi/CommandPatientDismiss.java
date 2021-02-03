package com.fundev.adt.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;
import java.util.UUID;

public class CommandPatientDismiss {
    @TargetAggregateIdentifier
    private UUID episodeOfCareId;
    private UUID patientId;
    private Date end;

    public CommandPatientDismiss(UUID episodeOfCareId, UUID patientId, Date end) {
        this.episodeOfCareId = episodeOfCareId;
        this.patientId = patientId;
        this.end = end;
    }

    public UUID getEpisodeOfCareId() {
        return episodeOfCareId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public Date getEnd() {
        return end;
    }
}