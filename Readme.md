# QA Automation Framework

Automation framework for UI testing (Selenium) and API testing (RestAssured)

---

## Technologies

- Java 17
- Selenium 4
- RestAssured 5
- TestNG
- WebDriverManager
- Jackson
- Maven

---

## Design Patterns / Architecture

### UI — Page Object Model (POM)

Each page has its own class that encapsulates selectors and actions.

```
src/main/java/com/facundo/automation/ui/
    pages/       → Page Objects (HomePage, LoginPage, CartPage...)
    models/      → Data Models (Product, OrderData)
    utils/       → Utilities (DriverManager, WaitUtils, FileUtils)

src/test/java/com/facundo/automation/ui/
    base/        → BaseUiTest (driver setup and teardown)
    tests/       → Test Classes
```

### API — Client Pattern

Centralized client that handles all HTTP requests. Tests invoke client methods and assert the responses.

```
src/main/java/com/facundo/automation/api/
    client/      → PetStoreClient (centralized request)
    models/      → Data Models (Pet, Order)

src/test/java/com/facundo/automation/api/
    base/        → BaseApiTest (client initialization)
    tests/       → Test Classes
```

---

## Requirements

- Java 17+
- Maven 3.8+
- Google Chrome installed

---

## First steps

```bash
git clone https://github.com/facundopucheta/qa-automation-framework.git
cd qa-automation-framework
mvn install -DskipTests
```

---

## Run

### UI Tests

```bash
mvn test -Dsuite=testng-ui.xml
```

### API Tests

```bash
mvn test -Dsuite=testng-api.xml
```

### Full suites

```bash
mvn test -Dsuite=testng.xml
```

### Headless Mode
Append `-Dheadless=true` to any UI test command:

```bash
mvn test -Dsuite=testng-ui.xml -Dheadless=true
```

## Websites used

| Scope | Site                                    | Description            |
|-------|-----------------------------------------|------------------------|
| UI    | [Demoblaze](https://www.demoblaze.com)  | Ecommerce              |
| API   | [PetStore](https://petstore.swagger.io) | API REST for pet store |