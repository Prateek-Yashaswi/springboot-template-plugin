spring.application.name=${projectName}
<#if jpaEnabled>
# Remove braces from the following configuration
spring.datasource.url=[Replace with your DB connection string]
spring.datasource.username=[Replace with your DB username]
spring.datasource.password=[Replace with your DB password]
spring.datasource.driver-class-name=[Replace with your driver classname]
</#if>

<#if mailEnabled>
spring.mail.host=YOUR_SMTP_HOST
spring.mail.port=1234 # Your SMTP Port
spring.mail.username=YOUR_SMTP_USERNAME
spring.mail.password=YOUR_SMTP_PASSWORD
</#if>