package io.sl.itests.webcalculator;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SubtractingController.class, AddingController.class})
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class AllInOneIT {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldRunTwoControllers() throws MalformedURLException {
        ResponseEntity<Integer> addingResponse = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/add/1/2").toString(), Integer.class);

        ResponseEntity<Integer> subtractingResponse = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/subtract/1/2").toString(), Integer.class);

        assertEquals(Integer.valueOf(3), addingResponse.getBody());
        assertEquals(Integer.valueOf(-1), subtractingResponse.getBody());
    }
}
