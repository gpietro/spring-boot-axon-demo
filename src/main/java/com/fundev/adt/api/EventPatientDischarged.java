package com.fundev.adt.api;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@AllArgsConstructor
public class EventPatientDischarged {
    UUID patientId;
    UUID episodeOfCareId;
    Date end;
    EpisodeOfCareStatus status = EpisodeOfCareStatus.FINISHED;
}