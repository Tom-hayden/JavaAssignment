# H2 Customer Database Service

## Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven (for building the project)

## Build Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/Tom-hayden/JavaAssignment.git
   ```
2. Change directory into the H2Database directory.
   ```
   cd H2Database
   ```
3. Build the project using Maven (or alternatively using an IDE such as IntelliJ )
   ```bash
   mvn clean package
   ```

# Usage

## Running the database service

You can run the service from the command line with the following syntax:

```bash
   java -jar target/H2Database-1.0.jar
```

# REST API

The service exposes two endpoints.

## Get Customer
Retrieve customer details by customer ID.

### Endpoint

``` GET /customer```

### Parameters
- `id` (UUID) - The unique identifier for the customer.

### Response
- **200 OK** - Customer details successfully retrieved.
  ```json
  {
    "customerRef": "01d43c5f-2fcd-4dbd-885b-12f0b02ab26a",
    "customerName": "Clark Kent",
    "addressLine1": "344 Clinton Street",
    "addressLine2": "Apartment 3D",
    "town": "Metropolis",
    "county": "Avon",
    "country": "England",
    "postcode": "BS1 1AA"
  }
  ```
- **404 Not Found** - Customer with the specified ID not found.

## Add Customer
Adds a new customer to the system. Please note that calling this multiple times with the same customer
reference will update the existing customer.

### Endpoint

``` POST /customer```

### Request Body
- `customer` (JSON) - Customer details to be added.
   ```json
    "customerRef": "01d43c5f-2fcd-4dbd-885b-12f0b02ab26a",
    "customerName": "Clark Kent",
    "addressLine1": "344 Clinton Street",
    "addressLine2": "Apartment 3D",
    "town": "Metropolis",
    "county": "Avon",
    "country": "England",
    "postcode": "BS1 1AA"
  
  ```

### Response
- **201 OK** - Customer successfully added.
  ```json
  {
    "customerRef": "01d43c5f-2fcd-4dbd-885b-12f0b02ab26a",
    "customerName": "Clark Kent",
    "addressLine1": "344 Clinton Street",
    "addressLine2": "Apartment 3D",
    "town": "Metropolis",
    "county": "Avon",
    "country": "England",
    "postcode": "BS1 1AA"
  }
  ```
  

# Design Considerations

- The requirements were a little unclear to me and in a real project I would follow up on this.
  - I was not sure if `customerRef` should be a unique identifier or not. 
    - If it was required that a customer could have multiple identities then a second `customerID` field could be used.
  - I decided to use UUIDs here but this may prove incompatible with any existing list of customers.
- A good improvement would be to create a batch add endpoint.
- I used Spring-boot and h2 to allow for a quick and simple setup.
