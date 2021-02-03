package com.fundev.adt.coreapi;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;

import java.util.Date;
import java.util.UUID;

public class EventPatientDismissed {

    private UUID episodeOfCareId;
    private UUID patientId;
    private Date end;
    private EpisodeOfCareStatus status = EpisodeOfCareStatus.FINISHED;

    public EventPatientDismissed(UUID episodeOfCareId, UUID patientId, Date end) {
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

    public EpisodeOfCareStatus getStatus() {
        return status;
    }
}
