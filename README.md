# 📏 Quantity Measurement Application

A scalable Java-based measurement system that supports **length comparison and unit-to-unit conversion** using clean Object-Oriented Design principles.

This project evolves progressively from basic equality checks (UC1) to a fully designed conversion API (UC5)

---

# 🚀 Project Overview

The application allows:

- Value-based equality comparison across units
- Cross-unit comparisons (Feet, Inches, Yards, Centimeters)
- Explicit unit-to-unit conversion
- Static and instance conversion methods
- Clean, immutable value object design
- Centralized conversion logic using Enum
- Robust input validation
- Comprehensive JUnit 5 test coverage

---  
  
# 🏗️ Project Structure  

```
QuantityMeasurementApp/  
│  
├── src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   └── com/  
│   │   │       └── app/  
│   │   │           └── quantitymeasurement/  
│   │   │  
│   │   │               ├── controller/  
│   │   │               │   └── QuantityMeasurementController.java  
│   │   │  
│   │   │               ├── core/  
│   │   │               │   ├── LengthUnit.java  
│   │   │               │   ├── Measurable.java  
│   │   │               │   ├── Quantity.java  
│   │   │               │   ├── TemperatureUnit.java  
│   │   │               │   ├── VolumeUnit.java  
│   │   │               │   └── WeightUnit.java  
│   │   │  
│   │   │               ├── dto/  
│   │   │               │   ├── QuantityDTO.java  
│   │   │               │   ├── QuantityInputDTO.java  
│   │   │               │   └── QuantityMeasurementDTO.java  
│   │   │  
│   │   │               ├── exception/  
│   │   │               │   └── GlobalExceptionHandler.java  
│   │   │  
│   │   │               ├── model/  
│   │   │               │   └── QuantityMeasurementEntity.java  
│   │   │  
│   │   │               ├── repository/  
│   │   │               │   └── QuantityMeasurementRepository.java  
│   │   │  
│   │   │               ├── security/  
│   │   │               │  
│   │   │               │   ├── config/  
│   │   │               │   │   └── SecurityConfig.java  
│   │   │               │  
│   │   │               │   ├── controller/  
│   │   │               │   │   ├── AuthController.java  
│   │   │               │   │   └── OAuthController.java  
│   │   │               │  
│   │   │               │   ├── dto/  
│   │   │               │   │   └── RegisterRequestDTO.java  
│   │   │               │  
│   │   │               │   ├── entity/  
│   │   │               │   │   └── UserEntity.java  
│   │   │               │  
│   │   │               │   ├── jwt/  
│   │   │               │   │   ├── JwtFilter.java  
│   │   │               │   │   └── JwtUtil.java  
│   │   │               │  
│   │   │               │   ├── repository/  
│   │   │               │   │   └── UserRepository.java  
│   │   │               │  
│   │   │               │   └── service/  
│   │   │  
│   │   │               ├── service/  
│   │   │               │   ├── IQuantityMeasurementService.java  
│   │   │               │   └── QuantityMeasurementServiceImpl.java  
│   │   │  
│   │   │               └── QuantityMeasurementAppApplication.java  
│   │   │  
│   │   └── resources/  
│   │       └── application.properties  
│   │  
│   └── test/  
│       └── java/  
│           └── com/  
│               └── app/  
│                   └── quantitymeasurement/  
│                       ├── QuantityLengthTest.java  
│                       └── QuantityMeasurementAppTest.java  
│  
├── pom.xml  
└── README.md  

```
---

# 📚 Use Cases Implemented

---

## ✅ UC1 – Basic Equality (Feet)

- Created Feet class
- Implemented equals()
- Ensured null safety and type safety
- Learned equality contract

---

## ✅ UC2 – Added Inches

- Added separate Inches class
- Identified duplication
- Recognized DRY violation

---

## ✅ UC3 – Generic Quantity Class (DRY Principle)

Refactored into:

- Single `QuantityLength` class
- `LengthUnit` enum

### Achievements

- Eliminated duplication
- Enabled cross-unit equality
- Improved scalability
- Clean architecture


---

## ✅ UC4 – Extended Unit Support

Added:

- YARD
- CENTIMETER

Without modifying `QuantityLength`.

Demonstrates:

- Enum extensibility
- Backward compatibility
- Multi-unit comparison
- Transitive equality

  
---

## ✅ UC5 – Explicit Unit Conversion API

Introduced:

- Static `convert()` method
- Instance `convertTo()` method
- Method overloading
- Input validation
- Zero, negative, and large value support

---
  
## 🟢 UC6 – Addition of Two Length Units
- Implemented addition of two Length objects  
- Used base unit (inches) for internal calculation
- Converted both operands to base unit before addition
- Converted result back to first operand’s unit
- Maintained immutability (returned new object)

---

## 🟢 UC7 – Addition with Explicit Target Unit
- Added support for specifying target unit in addition
- Implemented add(length1, length2, targetUnit)
- Converted result into desired unit
- Improved flexibility for arithmetic operations

---
  
## 🟢 UC8 – Refactoring Unit Enum
- Extracted LengthUnit into standalone enum
- Moved conversion logic from class to enum
- Applied Single Responsibility Principle
- Improved separation of concerns

---

## 🟢 UC9 – Weight Measurement Support
- Added new category: Weight
- Introduced WeightUnit enum (kg, g, lb)
- Implemented equality, conversion, and addition
- Ensured no comparison between different categories

---

## 🟢 UC10 – Generic Quantity with Unit Interface
- Introduced IMeasurable interface
- Created generic class Quantity<U extends IMeasurable>
- Replaced separate classes with single generic solution
- Ensured type safety across categories
- Applied DRY principle

---

## 🟢 UC11 – Volume Measurement
- Added new category: Volume
- Introduced VolumeUnit enum (L, mL, gallon)
- Supported equality, conversion, and addition
- No changes required in generic logic

---

## 🟢 UC12 – Subtraction and Division
- Added subtraction and division operations
- Supported cross-unit arithmetic within same category
- Maintained immutability and consistency

---

## 🟢 UC13 – Centralized Arithmetic Logic
- Refactored arithmetic logic into common method
- Removed duplicate code from operations
- Improved maintainability and readability

---

## 🟢 UC14 – Temperature Measurement
- Added new category: Temperature
- Introduced units (Celsius, Fahrenheit, Kelvin)
- Supported only conversion and comparison
- Restricted invalid arithmetic operations

---

## 🟢 UC15 – N-Tier Architecture Refactoring
- Refactored into layered architecture
- Introduced Controller, Service, Repository layers
- Applied SOLID principles
- Improved scalability and separation of concerns

---

## 🟢 UC16 – Database Integration (JDBC)
- Integrated database using JDBC
- Replaced in-memory storage
- Implemented CRUD operations
- Enabled data persistence

---

## 🟢 UC17 – Spring Boot Integration
- Converted project into Spring Boot application
- Exposed REST APIs (GET, POST, PUT, DELETE)
- Integrated Spring Data JPA
- Added Swagger and Actuator

---

## 🟢 UC18 – Spring Security with OAuth2 & JWT
- Implemented authentication and authorization
- Integrated Spring Security framework
- Added JWT-based token authentication
- Implemented OAuth2 login support
- Secured REST APIs with role-based access


