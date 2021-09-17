package com.ums;

import com.ums.model.request.SaveDeanRequest;
import com.ums.model.response.DeanResponse;
import com.ums.service.entity.DeanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"h2db", "debug"})
class UniversityManagementSystemApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private DeanService deanService;

    @BeforeTestClass
    void init() {
        SaveDeanRequest deanRequest = new SaveDeanRequest();
        deanRequest.setEmail("dean@email.com");
        deanRequest.setFirstName("dean");
        deanRequest.setLastName("dean");
        deanRequest.setPassword("111");
        deanService.create(deanRequest);
    }

    @Test
    void testContextLoads() {
        Assertions.assertNotNull(rest);
        Assertions.assertNotEquals(0, port);
    }



    private ResponseEntity<DeanResponse> createDean(String url, SaveDeanRequest req) {
        String actualUrl = deanControllerUrl() + url;
        return rest.postForEntity(actualUrl, req, DeanResponse.class);
    }


    private String deanControllerUrl() {
        return "http://localhost:" + port + "/dean";
    }
}
