package com.fundev.adt.modules.episodeOfCare.api;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class EventEpisodeOfCareDischarged {

    UUID episodeOfCareId;
    UUID patientId;
    Long version;
    Date end;
    EpisodeOfCareStatus status = EpisodeOfCareStatus.FINISHED;
}
