# 🤝 Contributing to Spring Boot Template Plugin

Thanks for your interest in contributing! This document outlines how to set up the project locally and the process for
making contributions.

---

## 📋 Steps To Work On This Repository

### 1️⃣ Clone The Repository

```bash
git clone https://github.com/Prateek-Yashaswi/springboot-template-plugin.git
```

### 2️⃣ Navigate Inside The Folder

```bash
cd springboot-template-plugin
```

### 3️⃣ Build The Project

```bash
mvn clean install
```

---

## 🧑‍💻 Prerequisites

Make sure you have the following installed:

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

---

## 🔧 Setting Up Your Local Environment

If you plan to contribute:

```bash
# (Optional) Fork the repository
git fork https://github.com/Prateek-Yashaswi/springboot-template-plugin.git

# Clone your fork
git clone https://github.com/<your-username>/springboot-template-plugin.git

# Set the upstream remote
git remote add upstream https://github.com/Prateek-Yashaswi/springboot-template-plugin.git

# Create a new branch for your feature/fix
git checkout -b feature/my-feature
```

---

## 🚀 Running the Plugin Locally

After building the project:

```bash
mvn clean install
```

You can now test the plugin by installing it to your local Maven repo and using it in a separate test Maven project.

---

## 🧪 Running Tests

To run all tests:

```bash
mvn test
```

---

## ✅ Creating a Pull Request

1. Make sure your branch is up to date with `main`:

```bash
git fetch upstream
git rebase upstream/main
```

2. Push your branch:

```bash
git push origin feature/my-feature
```

3. Open a pull request on GitHub from your branch to `main`.

4. Fill out the PR description, mention related issues if any, and explain your changes.

---

## 💡 Contribution Tips

- Follow the existing code style and conventions.
- Write clear and descriptive commit messages.
- Add relevant tests for new features or bug fixes.
- Keep pull requests focused and minimal.
- If you're unsure about something, feel free to open an issue for discussion first.

---

## 📄 License

By contributing, you agree that your contributions will be licensed under the [MIT License](LICENSE).

---

Happy coding! 💻✨
