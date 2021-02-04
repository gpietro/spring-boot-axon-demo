package com.fundev.adt.modules.episodeOfCare.query;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EpisodeOfCareView {
    @Id
    private UUID episodeOfCareId;
    private UUID patientId;
    private Long version = 1L;
    private EpisodeOfCareStatus status;
    private Date start;
    private Date end;
}