package com.fundev.adt.coreapi;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;

import java.util.Date;
import java.util.UUID;

public class EventPatientAdmitted {

    private UUID episodeOfCareId;
    private UUID patientId;
    private EpisodeOfCareStatus status;
    private Date start;
    private Date end;

    public EventPatientAdmitted(UUID episodeOfCareId, UUID patientId, EpisodeOfCareStatus status, Date start, Date end) {
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
