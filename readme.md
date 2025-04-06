# 🚀 Spring Boot Template Plugin

A **custom Maven plugin** that scaffolds ready-to-use Spring Boot projects in seconds — with options for different templates, configurations, and Docker support!

---

## ✨ Features

- ⚡ Instantly generate a Spring Boot project using `mvn` command
- 🧱 Multiple templates (e.g. `DEFAULT`, `DATABASE_JPA`) to get started quickly
- ⚙️ Supports both `YAML` and `PROPERTIES` configuration formats
- 🐳 Optional Dockerfile generation
- 🧼 Clean package structure, modular and extensible

---

## 📦 Getting Started

### 🔧 Prerequisites

- Java 17+
- Maven 3.8+
- Git (optional, for cloning your generated repo)

---

### 📥 Plugin Usage

```bash
mvn py.projects:springboot-template-plugin:1.0-SNAPSHOT:generate -DprojectName=demo -DgroupId=com.example -DartifactId=demo -DpackageName=com.example.demo -DspringVersion=3.2.0 -DapplicationConfigType=YAML