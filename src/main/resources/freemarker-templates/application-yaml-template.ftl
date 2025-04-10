spring:
    application:
        name: ${projectName}
    <#if jpaEnabled>
    # Remove braces from the following configuration
    datasource:
        url: [Replace with your DB connection string]
        username: [Replace with your DB username]
        password: [Replace with your DB password]
        driver-class-name: [Replace with your driver classname]
    </#if>