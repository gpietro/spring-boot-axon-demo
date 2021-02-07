package com.fundev.adt.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class EventPatientDeleted {
    UUID patientId;
}