#  Enterprise Hybrid QA Automation Framework

A production-style QA Automation Framework built with Java, Selenium, Rest Assured, TestNG, Maven, Jenkins, Allure, and Agentic Automation concepts — validating a **Notes Management** web application across UI, API, and hybrid layers.

---

##  Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Framework Architecture](#framework-architecture)
- [Features](#features)
- [UI Automation](#ui-automation)
- [API Automation](#api-automation)
- [Hybrid E2E Testing](#hybrid-e2e-testing)
- [Design Patterns](#design-patterns)
- [Parallel Execution](#parallel-execution)
- [Retry Mechanism](#retry-mechanism)
- [JSON Schema Validation](#json-schema-validation)
- [Logging](#logging)
- [Allure Reporting](#allure-reporting)
- [Performance Engineering](#performance-engineering)
- [CI/CD with Jenkins](#cicd-with-jenkins)
- [Agentic Automation & MCP](#agentic-automation--mcp)
- [Future Enhancements](#future-enhancements)
- [Author](#author)

---

## Overview

This framework validates a Notes Management web application through:

- ✅ UI Automation (Selenium)
- ✅ API Automation (Rest Assured)
- ✅ Hybrid UI + API End-to-End Testing
- ✅ Parallel Execution with thread-safe driver management
- ✅ Retry Mechanisms for flaky test handling
- ✅ JSON Schema Validation
- ✅ Performance Monitoring
- ✅ CI/CD Integration via Jenkins
- ✅ MCP-Inspired Agentic Automation

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Selenium WebDriver | UI Automation |
| TestNG | Test Execution Framework |
| Rest Assured | API Automation |
| Maven | Dependency Management |
| WebDriverManager | Driver Management |
| Log4j2 | Logging |
| Allure Reports | Reporting |
| Jenkins | CI/CD |
| JSON Schema Validator | API Contract Validation |
| Git / GitHub | Version Control |

---

## Framework Architecture

```
CapstoneProject
│
├── UI Automation
│   ├── Login
│   ├── Create Note
│   └── Delete Note
│
├── API Automation
│   ├── Login API
│   ├── Create Note API
│   ├── Get Notes API
│   └── Delete Note API
│
├── Hybrid E2E
│   ├── Create via UI
│   ├── Validate via API
│   └── Delete via API
│
├── Utilities
│   ├── Logger
│   ├── Retry
│   ├── Screenshot
│   ├── JSON Reader
│   ├── Wait Utilities
│   ├── Performance Logger
│   └── Allure Utilities
│
├── Reporting
│   ├── Allure
│   └── Screenshots
│
└── CI/CD
    └── Jenkins
```

---

## Features

| Feature | Status |
|---|---|
| Selenium UI Automation | ✅ |
| Rest Assured API Automation | ✅ |
| Hybrid UI + API Testing | ✅ |
| Parallel Execution | ✅ |
| ThreadLocal Driver Management | ✅ |
| Retry Mechanism | ✅ |
| JSON Schema Validation | ✅ |
| Log4j2 Logging | ✅ |
| Allure Reporting | ✅ |
| Jenkins CI/CD | ✅ |
| Performance Monitoring | ✅ |
| Agentic Automation Concepts | ✅ |

---

## UI Automation

Automates core user workflows using Selenium WebDriver with the Page Object Model.

### Scenarios

**Login Test**
1. Launch application
2. Navigate to login page
3. Enter credentials
4. Verify successful login

**Create Note Test**
1. Login
2. Create a new note
3. Verify note appears in the UI

**Delete Note Test**
1. Login
2. Delete a note
3. Verify note is removed

---

## API Automation

Built using Rest Assured. Authentication tokens are extracted from the Login API response and reused across all authenticated requests.

### Token Extraction

Tokens are discovered via Chrome DevTools (Network tab → Login API response):

```json
{
  "success": true,
  "data": {
    "token": "abc123xyz"
  }
}
```

```java
String token = response.jsonPath().getString("data.token");
```

### APIs Covered

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/users/login` | Authenticate and retrieve token |
| POST | `/notes` | Create a note via API |
| GET | `/notes` | Retrieve notes list |
| DELETE | `/notes/{id}` | Delete a note by ID |

---

## Hybrid E2E Testing

Validates synchronization between UI and backend API layers.

```
Step 1 → Create note via UI
Step 2 → Retrieve notes via API
Step 3 → Validate created note exists in API response
Step 4 → Delete note via API
Step 5 → Verify successful cleanup
```

This ensures consistency across:
```
UI  →  Backend API  →  Data
```

---

## Design Patterns

### Page Object Model (POM)

Pages are separated into reusable classes:

- `HomePage`
- `LoginPage`
- `NotesPage`

Benefits: reusability, maintainability, scalability, and cleaner code.

---

## Parallel Execution

Implemented using `ThreadLocal<WebDriver>` for thread-safe, independent browser sessions.

**TestNG configuration:**
```xml
parallel="classes"
thread-count="2"
```

---

## Retry Mechanism

Handles flaky tests automatically.

- **UI:** `RetryAnalyzer` — reruns failed Selenium tests
- **API:** `ApiRetryUtility` — retries unstable API requests

---

## JSON Schema Validation

Validates API response contracts to detect unintended backend changes.

**Example schema:**
```json
{
  "success": true,
  "status": 200,
  "data": []
}
```

Ensures required fields exist, data types are correct, and API contracts remain stable.

---

## Logging

Implemented with **Log4j2** for production-style, traceable logging.

```java
logger.info("Creating note via API");
logger.info("Validating response");
```

---

## Allure Reporting

Integrated Allure Reports provide detailed execution analysis including:

- Pass/Fail status
- Execution time
- Retry history
- API responses
- Screenshots on failure
- Environment information

---

## Performance Engineering

### API Response Time Validation

```java
Assert.assertTrue(response.time() < 2000); // must be < 2 seconds
```

### UI Navigation Timing

Measures page load duration using JavaScript:

```java
return document.readyState; // expected: "complete"
```

### Sample Performance Metrics

```
GET Notes API    :  361 ms
Create Note API  :  852 ms
Delete Note API  : 1170 ms
Page Navigation  : 1435 ms
```

---

## CI/CD with Jenkins

```
GitHub
  ↓
Jenkins
  ↓
Maven Build
  ↓
TestNG Execution
  ↓
Allure Report
```

---

## Agentic Automation & MCP

### What is MCP?

**Model Context Protocol** — a standardized way for AI agents to discover and execute tools.

```
Agent  →  Tool Selection  →  Execution  →  Result
```

### MCP-Inspired Implementation

The framework exposes automation actions as callable tools:

- `Login Tool`
- `Create Note Tool`
- `Delete Note Tool`

```
AI Agent
  ↓
Tool Invocation
  ↓
Selenium / API Execution
```

### Enterprise Usage

Large organizations use MCP for AI-assisted automation, tool orchestration, and autonomous testing:

```
AI Agent  →  Jira MCP  →  GitHub MCP  →  Selenium MCP  →  Database MCP
```

---

## Future Enhancements

- [ ] Update Note API Automation (PUT)
- [ ] Self-Healing Locators
- [ ] Dockerized Execution
- [ ] Selenium Grid
- [ ] GitHub Actions Integration
- [ ] Real MCP Server Integration
- [ ] AI-Based Test Generation
- [ ] JMeter Load Testing

---

## Author

**Ayush Aote**

B.Tech Final Year · Machine Learning Enthusiast · QA Automation Engineer · AI & Data Science Aspirant

---

> This project demonstrates enterprise-grade automation engineering by combining UI automation, API automation, hybrid validation, reporting, performance monitoring, CI/CD integration, and agentic automation concepts into a unified framework.
