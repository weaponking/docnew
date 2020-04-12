https://logback.qos.ch/

jar:
    logback-classic.x.x.x.jar
    logback-core.x.x.x.jar
    slf4j-api-x.x.x.jar

<dependency>
	<groupId>ch.qos.logback</groupId>
	<artifactId>logback-classic</artifactId>
	<version>1.2.3</version>
</dependency>

<context-param>  
     <param-name>logbackConfigLocation</param-name>  
     <param-value>classpath:logback.xml</param-value>  
 </context-param>  
 <listener>  
     <listener-class>ch.qos.logback.ext.spring.web.LogbackConfigListener</listener-class>  
 </listener>

xml:
<bean id="logbackBean" class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
   <property name="targetClass" value="ch.qos.logback.ext.spring.LogbackConfigurer"/>
   <property name="targetMethod" value="initLogging"/>
   <property name="arguments" value="classpath:config/logback${profile}.xml"/>
 </bean>

java:
<context-param>
    <param-name>spring.profiles.default</param-name>
    <param-value>dev</param-value>
</context-param>

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.base.log.util.LogbackProfileUtil;
import ch.qos.logback.ext.spring.LogbackConfigurer;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan("com.base")
@Slf4j
public class LogbackConfiguration {

	@Bean
    public MethodInvokingFactoryBean logbackConfigurer(Environment environment) {
        String currentProfile = LogbackProfileUtil.getProfileFromEnvironment(environment);
        log.debug("LogbackConfiguration currentProfile: {}",currentProfile);
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setTargetClass(LogbackConfigurer.class);
        factoryBean.setTargetMethod("initLogging");
        Object [] args = {String.format("classpath:config/logback-%s.xml", currentProfile)};
        factoryBean.setArguments(args);
        return factoryBean;
    }
}

public class LogbackProfileUtil {

    public static String getProfileFromEnvironment(Environment environment) {
        String[] profiles = environment.getActiveProfiles();
        if(null != profiles && profiles.length != 0) {
            return profiles[0];
        }

        profiles = environment.getDefaultProfiles();
        if(null != profiles && profiles.length != 0) {
            return profiles[0];
        }

        throw new IllegalStateException("Must specify a spring profile in the environment!");
    }
}
