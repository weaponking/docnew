package com.cassandra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import java.util.Arrays;
import java.util.List;

public class CassandraConfig3 extends AbstractCassandraConfiguration {

    @Value("${cassandra.keyspace}")
    private String keyspace;

    protected String getKeyspaceName() {
        return keyspace;
    }

    public static final String CREATE_KEYSPACE = "";

    protected List<String> getStartupScripts() {
        return Arrays.asList(CREATE_KEYSPACE);
    }

    protected List<String> getShutdownScripts() {
        return Arrays.asList("DROP KEYSPACE xxx;");
    }

    public String[] getEntityBasePackages() {
        return new String[] { "xx.xxx", "xx.xxx" };
    }


}
