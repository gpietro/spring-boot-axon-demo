package com.fundev.adt.command

import com.fundev.adt.datatypes.EpisodeOfCareStatus
import java.util.*

data class PatientAdmission(val episodeOfCareId: UUID, val status: EpisodeOfCareStatus, val start: Date, val end: Date?) {
    constructor(status: EpisodeOfCareStatus, start: Date) : this(UUID.randomUUID(), status, start, null)
}