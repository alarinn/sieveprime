package com.example.sieveprime;

import com.example.sieveprime.services.SievePrimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SieveprimeApplicationTests {

    @Autowired
    SievePrimeService sievePrimeService;
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    /*
    Test the REST GET endpoint for Success and valid results.
     */
    @Test
    public void testREST() throws URISyntaxException {
        System.out.println("In testREST....");
        final String baseUrl = "http://localhost:" + randomServerPort + "/primes/50";//Get all primes between 2 and 50
        URI uri = new URI(baseUrl);

        int[] primes = sievePrimeService.getPrimeNumbers(50);

        ResponseEntity<ArrayList> result = restTemplate.getForEntity(uri, ArrayList.class);

        Assert.assertEquals(200, result.getStatusCodeValue());//Assert that the request is successful
        Assert.assertEquals(primes.length, result.getBody().size());//Assert that the REST call matches what should be returned via the service method
        // Could add further tests for particular prime numbers or compare actual prime numbers
    }

    /*
    Test that the service method returns accurate data based on computing the number of prime numbers between 2 and 50
     */
    @Test
    public void testPrimes() {
        System.out.println("In testPrimes....");
        int[] primes = sievePrimeService.getPrimeNumbers(50);

        Assert.assertEquals(15, primes.length);
    }
}
