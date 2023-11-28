package com.example.CSVReader;

import com.example.CSVReader.model.Customer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;


public class CustomerRestClient {

    URI uri;
    HttpClient httpClient;

    public CustomerRestClient(URI uri, HttpClient httpClient) {
        this.uri = uri;
        this.httpClient = httpClient;
    }

    public int sendCustomer(Customer customer) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(customer.toJson()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }
}
