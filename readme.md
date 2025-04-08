# 🚀 Spring Boot Template Plugin

A **custom Maven plugin** that scaffolds ready-to-use Spring Boot projects in seconds — with options for different
templates, configurations, and Docker support!

---

## ✨ Features

- ⚡ Instantly generate a Spring Boot project using `mvn` command
- 🧱 Multiple templates (e.g. `DEFAULT`, `DATABASE_JPA`) to get started quickly
- ⚙️ Supports both `YAML` and `PROPERTIES` configuration formats
- 🐳 Optional Dockerfile generation
- 🧼 Clean package structure, modular and extensible

---

## 📦 Getting Started

### ⚙️ Maven Plugin Parameters

| **Parameter**         | **Required** | **Description**                     | **Default Value** | **Possible Values**             |
|-----------------------|--------------|-------------------------------------|-------------------|---------------------------------|
| `-DProjectName`       | ✅ Yes        | Name of the Spring Boot project     | –                 | *Any valid string*              |
| `-DGroupId`           | ✅ Yes        | Maven Group ID                      | –                 | *Any valid string*              |
| `-DArtifactId`        | ✅ Yes        | Maven Artifact ID                   | –                 | *Any valid string*              |
| `-DTemplate`          | ❌ No         | Type of project template            | `DEFAULT`         | `DEFAULT`, `DATABASE_JPA`       |
| `-DSpringVersion`     | ❌ No         | Version of Spring Boot to use       | `3.1.0`           | *Any valid Spring Boot version* |
| `-DPackageName`       | ❌ No         | Base package for the generated code | `com.example`     | *Any valid Java package name*   |
| `-DJavaVersion`       | ❌ No         | Java version for the project        | `17`              | `8`, `11`, `17`, `21`           |
| `-DConfigurationType` | ❌ No         | Format of application configuration | `YAML`            | `YAML`, `PROPERTIES`            |
| `-DCreateDockerfile`  | ❌ No         | Whether to generate a `Dockerfile`  | `N`               | `Y`, `N`                        |

### ▶️ How to use this plugin

This plugin is available at [Maven Central](https://central.sonatype.com/artifact/io.github.prateek-yashaswi/springboot-template-plugin/overview)

### 📥 Example Usage

```bash
mvn io.github.prateek-yashaswi:springboot-template-plugin:1.0:generate -DProjectName=demo -DGroupId=com.example -DArtifactId=demo -DSpringVersion=3.2.0 -DConfigurationType=YAML -DCreateDockerfile=Y
```

This will generate a project 'demo' with group id 'com.example' and artifact id 'demo'. The spring version that'll be
used is 3.2.0 with the application.yaml & a dockerfile. You can remove the optional parameters if needed.

## 🤝 Contributing

We welcome contributions! Please check out our [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.


## 👨‍💻 Developer Details

| Name             | Contact                                                                                                                                                     |
|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Prateek Yashaswi | [GitHub](https://github.com/Prateek-Yashaswi) · [LinkedIn](https://www.linkedin.com/in/prateek-yashaswi/) · [Email](mailto:prateekyashaswi.work@gmail.com ) |