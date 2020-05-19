~~~
@Bean
public FilterRegistrationBean filterRegistration() {
   FilterRegistrationBean registration = new FilterRegistrationBean(new FilterB());
   registration.setUrlPatterns(Arrays.asList("/b"));
   return registration;
}

@Bean
public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
   DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("filterD");
   bean.setUrlPatterns(Arrays.asList("/d"));
   return bean;
}

@WebFilter(urlPatterns = "/a")
@SpringBootApplication + @ServletComponentScan
~~~
