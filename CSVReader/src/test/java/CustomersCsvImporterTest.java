import com.example.CSVReader.CustomerCsvImporter;
import com.example.CSVReader.CustomerRestClient;
import com.example.CSVReader.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomersCsvImporterTest {

    CustomerCsvImporter csvImporter;
    @BeforeEach
    public void beforeEach() {
        CustomerRestClient client = mock(CustomerRestClient.class);
        csvImporter = new CustomerCsvImporter(client);
    }

    @Test
    @Disabled("Disabled: Use this to manually test")
    void saveCsv() {
        URI uri = URI.create("http://localhost:8080/customer");
        CustomerRestClient client = new CustomerRestClient(uri, HttpClient.newBuilder().build());
        csvImporter = new CustomerCsvImporter(client);
        csvImporter.saveCsv("E:\\src\\ESGAssignment\\JavaAssignment\\CSVReader\\Customers.csv");
    }

    @Test
    void createCustomerFromRow_ValidRow() {
        String[] validRow = {"b7fef628-b0ed-4bab-8e27-61eb85ab9553", "CoolGuy", "add1", "add2", "town", "county", "Country", "12345"};

        Customer createdCustomer = csvImporter.createCustomerFromRow(validRow);

        assertNotNull(createdCustomer);
        assertEquals(UUID.fromString(validRow[0]), createdCustomer.getCustomerRef());
        assertEquals("CoolGuy", createdCustomer.getCustomerName());
        assertEquals("add1", createdCustomer.getAddressLine1());
        assertEquals("add2", createdCustomer.getAddressLine2());
        assertEquals("town", createdCustomer.getTown());
        assertEquals("county", createdCustomer.getCounty());
        assertEquals("Country", createdCustomer.getCountry());
        assertEquals("12345", createdCustomer.getPostcode());
    }

    @Test
    void CreateCustomerFromRow_InvalidRow() {
        // Invalid UUID in the first column
        String[] invalidRow = {"invalid-uuid", "CoolGuy", "add1", "add2", "town", "county", "Country", "12345"};

        Customer createdCustomer = csvImporter.createCustomerFromRow(invalidRow);

        assertNull(createdCustomer);
    }

}