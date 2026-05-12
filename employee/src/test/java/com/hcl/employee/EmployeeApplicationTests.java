package com.hcl.employee;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

class EmployeeApplicationTest {

    @Test
    void mainMethodShouldRunSuccessfully() {

        assertDoesNotThrow(() ->
                EmployeeApplication.main(new String[] {}));
    }

    @Test
    void applicationClassShouldBeLoaded() {

        EmployeeApplication application = new EmployeeApplication();

        assertNotNull(application);
    }
}