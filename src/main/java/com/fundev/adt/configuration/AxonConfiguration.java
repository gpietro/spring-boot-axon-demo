package com.fundev.adt.configuration;

import com.fundev.adt.api.EventPatientCreatedUpcaster;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AxonConfiguration {

    @Bean
    public SingleEventUpcaster eventPatientCreatedUpcaster() {
        return new EventPatientCreatedUpcaster();
    }

    @Bean
    public JpaEventStorageEngine eventStorageEngine(Serializer eventSerializer,
                                                    Serializer snapshotSerializer,
                                                    DataSource dataSource,
                                                    SingleEventUpcaster eventPatientCreatedUpcaster,
                                                    EntityManagerProvider entityManagerProvider,
                                                    TransactionManager transactionManager) throws SQLException {
        return JpaEventStorageEngine.builder()
                .eventSerializer(eventSerializer)
                .snapshotSerializer(snapshotSerializer)
                .dataSource(dataSource)
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager)
                .upcasterChain(eventPatientCreatedUpcaster)
                .build();
    }
}