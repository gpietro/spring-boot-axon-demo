package com.fundev.adt.modules.episodeOfCare.api;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
public class EventEpisodeOfCareAdmitted {

    UUID episodeOfCareId;
    UUID patientId;
    EpisodeOfCareStatus status;
    Date start;
    Date end;

    public EventEpisodeOfCareAdmitted(UUID episodeOfCareId, UUID patientId, EpisodeOfCareStatus status, Date start, Date end) {
        this.episodeOfCareId = episodeOfCareId;
        this.patientId = patientId;
        this.status = status;
        this.start = start;
        this.end = end;
    }
}
