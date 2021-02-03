package com.fundev.adt.command;

import com.fundev.adt.coreapi.*;
import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class EpisodeOfCare {

    private static final Logger logger = LoggerFactory.getLogger(EpisodeOfCare.class);

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
        // dispatch command create care team
        // dispatch command create account
    }

    @CommandHandler
    public void on(CommandPatientDismiss command) {
        logger.debug("handling {}", command);
        Assert.isTrue(EpisodeOfCareStatus.ACTIVE == this.status, "The patient treatment is not active and cannot be dismissed");
        apply(new EventPatientDismissed(command.getEpisodeOfCareId(), command.getPatientId(), command.getEnd()));
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

    @EventSourcingHandler
    public void on(EventPatientDismissed event) {
        logger.debug("applying {}", event);
        status = event.getStatus();
        end = event.getEnd();
        logger.debug("patient dismissed: {}", this.toString());
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
