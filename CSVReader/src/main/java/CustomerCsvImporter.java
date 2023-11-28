import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import model.Customer;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CustomerCsvImporter {

    private final CustomerRestClient client;

    public CustomerCsvImporter(CustomerRestClient client) {
        this.client = client;
    }


    public void saveCsv(String path) {

        List<String> errors = new ArrayList<>();
        int addedCustomers = 0;

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            skipHeader(reader);

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                Customer customer = createCustomerFromRow(nextLine);
                try{
                    int responseCode = client.sendCustomer(customer);
                    if(responseCode == 201){
                        addedCustomers += 1;
                    } else {
                        errors.add(
                                String.format(
                                "model.Customer Ref: %s, Error: Could not connect to database. Response code: %d",
                                        customer.getCustomerRef().toString(),
                                        responseCode
                        ));
                    }
                } catch (Exception e) {
                    if (customer == null) {
                        errors.add(String.format("Could not create customer with Id: %s", nextLine[0]));
                    } else {
                        errors.add(String.format( "model.Customer Ref: %s, Error: %s",
                                customer.getCustomerRef().toString(), e));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } finally {
            printResults(addedCustomers, errors);
        }
    }

    private void skipHeader(CSVReader reader) throws IOException {
        String[] nextLine;
        if ((nextLine = reader.peek()) != null) {
            if (nextLine[0].equals("model.Customer Ref")) {
                System.out.println("CSV Header detected. Skipping header line");
                reader.skip(1);
            }
        }
    }

    public Customer createCustomerFromRow(String[] row){
        UUID customerRef;

        try {
            customerRef = UUID.fromString(row[0]);
        } catch (IllegalArgumentException e){
            System.out.printf("Invalid UUID. model.Customer reference: \"%s\" - Row not added\n", row[0]);
            return null;
        }
        return new Customer( customerRef, row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
    }

    private void printResults(int addedCustomers, List<String> errors) {
        System.out.printf("Successfully added %d customers\n" , addedCustomers);
        if (!errors.isEmpty()) {
            System.out.printf("Errors occurred for %d customers.\n", errors.size());
            errors.forEach(System.out::println);
        }
    }
}
