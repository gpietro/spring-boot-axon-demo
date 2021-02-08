package com.fundev.adt.query;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientTreatmentView {
    @Id
    private UUID episodeOfCareId;
    private UUID patientId;
    private EpisodeOfCareStatus status;
    private Date start;
    private Date end;
}