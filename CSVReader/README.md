# Customer CSV Import Tool


## Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven (for building the project)

## Build Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/Tom-hayden/JavaAssignment.git
   ```
2. Build the project using Maven (or alternatively using an IDE such as IntelliJ )
   ```bash
   mvn clean package
   ```
   
# Usage

## Running the tool

You will need to run the accompanying `h2Database` project at the same time to connect to the in memory database.

You can run the tool from the command line with the following syntax:

```bash
   java -jar target/customer-csv-import-tool.jar [-path=<csvPath>] [-url=<serviceUrl>]
```

* -path=\<csvPath>: Specify the path to the CSV file containing customer data.
* -url=\<serviceUrl>: Specify the URL of the database service. (Optional, default: http://localhost:8080/customer)

# Notes


