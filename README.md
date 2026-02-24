# 📏 Quantity Measurement Application

A scalable Java-based measurement system that supports **length comparison and unit-to-unit conversion** using clean Object-Oriented Design principles.

This project evolves progressively from basic equality checks (UC1) to a fully designed conversion API (UC5), demonstrating refactoring, abstraction, scalability, and proper API design.

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
QuantityMeasurementApp/  
│  
├── src/  
│ ├── main/  
│ │ └── java/  
│ │ └── com/  
│ │ └── quantitymeasurementapp/  
│ │ └── QuantityMeasurementApp.java  
│ │  
│ └── test/  
│ └── java/  
│ └── com/  
│ └── quantitymeasurementapp/  
│ ├── QuantityLengthTest.java  
│ └── QuantityMeasurementAppTest.java  


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



