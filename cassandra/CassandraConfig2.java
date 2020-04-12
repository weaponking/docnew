package com.cassandra.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.data.cassandra.config.AbstractClusterConfiguration;

public class CassandraConfig2 extends AbstractClusterConfiguration
    implements BeanClassLoaderAware {

    public void setBeanClassLoader(ClassLoader classLoader) {

    }
}
