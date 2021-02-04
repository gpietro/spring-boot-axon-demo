package com.fundev.adt.modules.episodeOfCare.command;

import com.fundev.adt.datatypes.EpisodeOfCareStatus;
import com.fundev.adt.modules.episodeOfCare.api.CommandEpisodeOfCareAdmit;
import com.fundev.adt.modules.episodeOfCare.api.CommandEpisodeOfCareDischarge;
import com.fundev.adt.modules.episodeOfCare.api.EventEpisodeOfCareAdmitted;
import com.fundev.adt.modules.episodeOfCare.api.EventEpisodeOfCareDischarged;
import lombok.ToString;
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
@ToString
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
    public EpisodeOfCare(CommandEpisodeOfCareAdmit command) {
        logger.debug("handling {}", command);
        apply(new EventEpisodeOfCareAdmitted(command.getEpisodeOfCareId(), command.getPatientId(), command.getStatus(), command.getStart(), command.getEnd()));
    }

    @CommandHandler
    public void on(CommandEpisodeOfCareDischarge command) {
        logger.debug("handling {}", command);
        Assert.isTrue(EpisodeOfCareStatus.ACTIVE == this.status, "The patient treatment is not active and cannot be dismissed");
        apply(new EventEpisodeOfCareDischarged(command.getEpisodeOfCareId(), command.getPatientId(), command.getVersion(), command.getEnd()));
    }


    @EventSourcingHandler
    public void on(EventEpisodeOfCareAdmitted event) {
        logger.debug("applying {}", event);
        episodeOfCareId = event.getEpisodeOfCareId();
        patientId = event.getPatientId();
        status = event.getStatus();
        start = event.getStart();
        end = event.getEnd();
        logger.debug("patient admitted: {}", this.toString());
    }

    @EventSourcingHandler
    public void on(EventEpisodeOfCareDischarged event) {
        logger.debug("applying {}", event);
        status = event.getStatus();
        end = event.getEnd();
        logger.debug("patient dismissed: {}", this.toString());
    }
}
