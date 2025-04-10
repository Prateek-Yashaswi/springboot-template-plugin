spring.application.name=${projectName}
<#if jpaEnabled>
# Remove braces from the following configuration
spring.datasource.url=[Replace with your DB connection string]
spring.datasource.username=[Replace with your DB username]
spring.datasource.password=[Replace with your DB password]
spring.datasource.driver-class-name=[Replace with your driver classname]
</#if>