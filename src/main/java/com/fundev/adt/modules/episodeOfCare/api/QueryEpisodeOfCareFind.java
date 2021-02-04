package com.fundev.adt.modules.episodeOfCare.api;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor
@Value
public class QueryEpisodeOfCareFind {
    UUID episodeOfCareId;
}
