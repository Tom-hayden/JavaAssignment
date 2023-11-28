package com.example.CSVReader;

import com.example.CSVReader.utils.PathSanitiser;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.Scanner;

public class Application {

    private static final String DEFAULT_URL = "http://localhost:8080/customer";

    public static void main(String[] args) {
        String url = getServiceUrl(args);
        String pathValue = getCSVPath(args);

        if (pathValue.isEmpty()) return;

        System.out.printf("Using %s as database service address.\n", url);

        URI uri = URI.create(url);
        CustomerRestClient restClient = new CustomerRestClient(uri, HttpClient.newBuilder().build());
        CustomerCsvImporter csvImporter = new CustomerCsvImporter(restClient);

        csvImporter.saveCsv(pathValue);
    }

    private static String getCSVPath(String[] args) {
        String csvPath = "";
        if (args.length > 0) {
            for (String arg : args) {
                if (arg.startsWith("-path=")) {
                    csvPath = arg.substring("-path=".length());
                }
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter path to csv: ");
            String userInput = scanner.nextLine();
            scanner.close();
            csvPath = PathSanitiser.sanitisePath(userInput);
        }

        File csvFile = new File(csvPath);

        if (csvFile.isFile()) {
            System.out.println("CSV found at: " + csvPath);
        } else {
            System.out.println("CSV not found at: " + csvPath);
            return "";
        }

        return csvPath;
    }

    private static String getServiceUrl(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("-url=")) {
                return arg.substring("-url=".length());
            }
        }

        return DEFAULT_URL;
    }
}
