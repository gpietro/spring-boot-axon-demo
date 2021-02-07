package com.fundev.adt.command;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import com.fundev.adt.api.EventPatientDischarged;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
class EpisodeOfCare {

    @EntityId
    UUID episodeOfCareId;
    EpisodeOfCareStatus status;
    Date start;
    Date end;

    @EventSourcingHandler
    public void on(EventPatientDischarged event) {
        this.status = EpisodeOfCareStatus.FINISHED;
        this.end = event.getEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeOfCare that = (EpisodeOfCare) o;
        return episodeOfCareId.equals(that.episodeOfCareId) && status == that.status && start.equals(that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(episodeOfCareId, status, start, end);
    }
}