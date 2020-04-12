package com.cassandra.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractClusterConfiguration;
import org.springframework.data.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraCqlSessionFactoryBean;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackages = { "com.cassandra" })
public class CassandraConfig extends AbstractClusterConfiguration {

    public static final String CREATE_KEYSPACE =
        "CREATE KEYSPACE if not exists test WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}";

    protected List<String> getStartupScripts() {
        return Arrays.asList(CREATE_KEYSPACE);
    }

    @Value("${cassandra.keyspace}")
    private String keyspace;
    @Value("${cassandra.host}")
    private String host;

    @Bean
    public Session session() {
        Cluster cluster = Cluster.builder().addContactPoints("localhost").build();
        return cluster.connect(keyspace);
    }

    @Bean
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraCqlClusterFactoryBean cluster = new CassandraCqlClusterFactoryBean();
        cluster.setContactPoints(host);
        return cluster;
    }

    @Bean
    public CassandraCqlSessionFactoryBean cassandraCqlSessionFactoryBean() {
        CassandraCqlSessionFactoryBean session = new CassandraCqlSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(keyspace);
        return session;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        CassandraMappingContext mappingContext =  new CassandraMappingContext();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), keyspace));
        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CqlTemplate getCqlTemplate() {
        CqlTemplate cql = new CqlTemplate();
        cql.setSession(session());
        return cql;
    }
}
