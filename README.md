# WeatherStation GUI Application

A Java Swing-based desktop application that provides current weather conditions and detailed hourly forecasts for any city worldwide by leveraging the Open-Meteo API.

## 📋 Project Overview

This project was developed as part of university coursework. **Note: The `OpenMeteoClient` class was provided by the university as a foundational component for the project.** My contribution was the design and implementation of the complete graphical user interface (GUI) in the `WeatherApp` class, which integrates with the provided API client to create a fully functional weather application.

## ✨ Features

- **Current Weather Display:** View real-time temperature, precipitation, wind speed, and humidity for any city.
- **Detailed Hourly Forecast:** Get a comprehensive 7-day hourly forecast presented in a clean, scrollable table.
- **User-Friendly GUI:** Intuitive interface built with Java Swing, featuring input validation and error handling.
- **Global City Support:** Search for weather information in any city worldwide.

## 🛠️ Technologies Used

- **Java SE:** Core application logic and Swing framework for the GUI.
- **Open-Meteo API:** Free weather API for retrieving meteorological data.
- **HTTP Client:** Java's built-in HTTP client for making API requests.

## ▶️ Installation & Compilation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/DevAhmadYarKhan/weather-app-java.git
    cd weather-app-java
    ```

2.  **Compile the Java files:**
    Navigate to the directory containing the `WeatherApp.java` file and run:
    ```bash
    javac -d . WeatherApp.java
    ```
    This will compile all `.java` files in the project and place the `.class` files in the correct package structure.

3.  **Run the application:**
    ```bash
    java WeatherApp
    ```

## 🖥️ Usage

1. Launch the application to open the WeatherStation window.
2. Enter the name of a city in the "Location" textfield.
3. Click the "Check Weather" button to retrieve weather data.
4. View current weather conditions in the center panel.
5. Scroll through the hourly forecast in the bottom panel.
