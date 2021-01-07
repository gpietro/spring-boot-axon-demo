package com.fundev.adt.command;

import com.fundev.adt.coreapi.CommandPatientAdmit;
import com.fundev.adt.coreapi.EventPatientAdmitted;
import com.fundev.adt.coreapi.EventPatientCreated;
import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class EpisodeOfCare {

    private static final Logger logger = LoggerFactory.getLogger(Patient.class);

    @AggregateIdentifier
    private UUID episodeOfCareId;
    private UUID patientId;
    private EpisodeOfCareStatus status;
    private Date start;
    private Date end;

    public EpisodeOfCare() {
    }

    @CommandHandler
    public EpisodeOfCare(CommandPatientAdmit command) {
        logger.debug("handling {}", command);
        // Business logic
        apply(new EventPatientAdmitted(command.getEpisodeOfCareId(), command.getPatientId(), command.getStatus(), command.getStart(), command.getEnd()));
    }

    @EventSourcingHandler
    public void on(EventPatientAdmitted event) {
        logger.debug("applying {}", event);
        episodeOfCareId = event.getEpisodeOfCareId();
        patientId = event.getPatientId();
        status = event.getStatus();
        start = event.getStart();
        end = event.getEnd();
        logger.debug("patient admitted: {}", this.toString());
    }

    @Override
    public String toString() {
        return "EpisodeOfCare{" +
                "episodeOfCareId=" + episodeOfCareId +
                ", patientId=" + patientId +
                ", status=" + status +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
