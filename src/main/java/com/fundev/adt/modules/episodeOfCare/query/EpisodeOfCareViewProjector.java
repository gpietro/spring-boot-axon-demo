package com.fundev.adt.modules.episodeOfCare.query;

import com.fundev.adt.modules.episodeOfCare.api.EventEpisodeOfCareAdmitted;
import com.fundev.adt.modules.episodeOfCare.api.EventEpisodeOfCareDischarged;
import com.fundev.adt.modules.episodeOfCare.api.QueryEpisodeOfCareFind;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.SequenceNumber;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EpisodeOfCareViewProjector {
    private final EpisodeOfCareViewQueryRepository repository;

    public EpisodeOfCareViewProjector(EpisodeOfCareViewQueryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(EventEpisodeOfCareAdmitted event) {
        EpisodeOfCareView episodeOfCareView = new EpisodeOfCareView(event.getEpisodeOfCareId(), event.getPatientId(), 1L, event.getStatus(), event.getStart(), event.getEnd());
        repository.save(episodeOfCareView);
    }

    @EventHandler
    public void on(EventEpisodeOfCareDischarged event, @SequenceNumber Long version) {
        EpisodeOfCareView episodeOfCareView = repository.findById(event.getEpisodeOfCareId()).orElseThrow();
        episodeOfCareView.setStatus(event.getStatus());
        episodeOfCareView.setEnd(event.getEnd());
        episodeOfCareView.setVersion(version);
        repository.save(episodeOfCareView);
    }

    @QueryHandler
    public Optional<EpisodeOfCareView> handle(QueryEpisodeOfCareFind query) {
        return repository.findById(query.getEpisodeOfCareId());
    }
}