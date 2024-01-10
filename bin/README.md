# World Bank Data Visualizer

## Project Description

World Bank Data Visualizer is a system that allows for:

- Retrieving demographic and other data for one or more selected countries from the World Bank’s data repository.
- Processing the data using different types of analyses.
- Rendering the retrieved data or the processed data using appropriately selected visualization means such as bar charts, line graphs, scattered plots, and pie charts.

## Getting Started

### Prerequisites

- Java 8 or higher. You can download it from [here](https://adoptopenjdk.net/).
- Maven. You can download it from [here](https://maven.apache.org/download.cgi).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/hanle23/WorldBankVisualization.git
   ```
2. Navigate to the project directory:
   ```bash
   cd WorldBankVisualization
   ```

### Usage

1. Build the project with Maven:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   java -jar target/WorldBankVisualization.jar
   ```

## Login Credentials

The system uses a file named `credentials.json` located in the `loginCredentials` folder at the top level of the project directory for user authentication. This file contains the login credentials that are used to access the system.

Please note that users do not have the ability to create their own credentials, so it is safe to upload this file. The structure of the file is as follows:

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
