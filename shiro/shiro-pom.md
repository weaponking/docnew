<properties>
    <shiro.version>1.4.0</shiro.version>
    <thymeleaf.extras.shiro.version>2.0.0</thymeleaf.extras.shiro.version>
</properties>

<dependencies>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-core</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-web</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-aspectj</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-spring</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-cas</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-quartz</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-ehcache</artifactId>
        <version>${shiro.version}</version>
    </dependency>

    <dependency>
        <groupId>com.github.theborakompanioni</groupId>
        <artifactId>thymeleaf-extras-shiro</artifactId>
        <version>${thymeleaf.extras.shiro.version}</version>
    </dependency>

</dependencies>
