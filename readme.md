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

### ▶️ To Run This Plugin

**1️⃣ Clone The Repository** 

```bash
git clone https://github.com/Prateek-Yashaswi/springboot-template-plugin.git
```

**2️⃣ Navigate Inside The Folder**
```bash
cd springboot-template-plugin
```

**3️⃣ Build The Project**
```bash
mvn clean install
```

### 📥 Example Usage

```bash
mvn py.projects:springboot-template-plugin:1.0-SNAPSHOT:generate -DProjectName=demo -DGroupId=com.example -DArtifactId=demo -DSpringVersion=3.2.0 -DConfigurationType=YAML -DCreateDockerfile=Y
```

This will generate a project 'demo' with group id 'com.example' and artifact id 'demo'. The spring version that'll be
used is 3.2.0 with the application.yaml & a dockerfile.

## 👨‍💻 Developer Details

| Name             | Contact                                                                                                                                                     |
|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Prateek Yashaswi | [GitHub](https://github.com/Prateek-Yashaswi) · [LinkedIn](https://www.linkedin.com/in/prateek-yashaswi/) · [Email](mailto:prateekyashaswi.work@gmail.com ) |