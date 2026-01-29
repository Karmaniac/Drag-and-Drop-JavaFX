# Drag-and-Drop JavaFX System

![Java](https://img.shields.io/badge/Language-Java-red)
![JavaFX](https://img.shields.io/badge/Framework-JavaFX-blue)
![Build](https://img.shields.io/badge/Build-Maven-green)
![Status](https://img.shields.io/badge/Status-Experimental-yellow)

## Overview

**Drag-and-Drop JavaFX** is an experimental framework for implementing drag-and-drop interactions in desktop graphical user interfaces using the JavaFX platform. The project is structured as a Maven-managed Java application and demonstrates foundational mechanisms for enabling interactive drag gestures, drop targets, and user-driven object manipulation.

JavaFX provides a native event model for drag-and-drop operations, including gesture detection, transfer modes, and target acceptance handling. These capabilities allow developers to construct responsive and intuitive user interfaces that support direct manipulation of UI elements.

---

## Motivation

Drag-and-drop interaction is a fundamental paradigm in modern graphical user interfaces. It enables intuitive user workflows, improves usability, and allows for spatial organization of visual elements. The objective of this project is to explore a structured and reusable approach to implementing drag-and-drop functionality within the JavaFX ecosystem.

---

## Repository Structure

```
.
├── .mvn/                     # Maven wrapper configuration
├── .idea/                    # IDE configuration files
├── mvnw
├── mvnw.cmd
├── pom.xml                  # Maven project descriptor
├── README.md
├── src/
│   └── main/
│       └── java/            # Core application source code
│           └── ...
└── target/                  # Compiled classes and build artifacts
```

---

## Functional Description

The project demonstrates core drag-and-drop concepts provided by JavaFX, including:

- **Gesture Recognition**  
  Detection of user input events that initiate drag operations.

- **Drag Data Management**  
  Use of the JavaFX `Dragboard` API to transfer data between drag sources and drop targets.

- **Drop Target Handling**  
  Configuration of UI components to accept, reject, or process dropped data according to application logic.

These components form a foundational architecture that can be extended to support more complex interaction models.

---

## Build and Execution

The project uses **Apache Maven** for dependency management and build automation.

1. Clone the repository:
   ```bash
   git clone https://github.com/Karmaniac/Drag-and-Drop-JavaFX.git
   cd Drag-and-Drop-JavaFX
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn javafx:run
   ```

Ensure that a compatible Java Development Kit (JDK) and JavaFX runtime are properly configured in your development environment.

---

## Project Goals

The current implementation serves as a technical demonstration of JavaFX drag-and-drop mechanisms. The primary goals include:

- Demonstrating drag gesture initiation and handling  
- Providing modular, extensible interaction logic  
- Supporting experimentation with event-driven UI design  
 
