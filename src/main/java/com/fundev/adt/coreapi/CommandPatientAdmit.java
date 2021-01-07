package com.fundev.adt.coreapi;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import org.axonframework.commandhandling.RoutingKey;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

public class CommandPatientAdmit {
    @RoutingKey
    private UUID episodeOfCareId;
    private UUID patientId;
    private EpisodeOfCareStatus status;
    private Date start;
    private Date end;

    public CommandPatientAdmit(UUID episodeOfCareId, UUID patientId, EpisodeOfCareStatus status, Date start, Date end) {
        this.episodeOfCareId = episodeOfCareId;
        this.patientId = patientId;
        this.status = status;
        this.start = start;
        this.end = end;
    }

    public UUID getEpisodeOfCareId() {
        return episodeOfCareId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public EpisodeOfCareStatus getStatus() {
        return status;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}