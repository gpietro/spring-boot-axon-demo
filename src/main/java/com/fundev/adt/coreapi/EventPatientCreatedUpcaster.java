package com.fundev.adt.coreapi;

import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;

// Upcaster from 1.0 revision to 2.0 revision
public class EventPatientCreatedUpcaster extends SingleEventUpcaster {

    private static SimpleSerializedType targetType =
            new SimpleSerializedType(EventPatientCreated.class.getTypeName(), "1.0");

    @Override
    protected boolean canUpcast(IntermediateEventRepresentation
                                        intermediateRepresentation) {
        return intermediateRepresentation.getType().equals(targetType);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(
            IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.upcastPayload(
                new SimpleSerializedType(targetType.getName(), "2.0"),
                org.dom4j.Document.class,
                document -> {
                    document.getRootElement()
                            .addElement("address")
                            .setText("no address"); // Default value
                    return document;
                }
        );
    }
}
