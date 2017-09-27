package com.example.customer.service;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerServiceImpl customerService;

    @Test
    public void testAddGet() {
        // Get unique names every time this test runs
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());

        Customer customer1 = new Customer();
        customer1.setFirstName(firstName);
        customer1.setLastName(lastName);
        customerService.add(customer1);

        List<Customer> people = customerService.get();

        Customer customer2 = findInList(people, firstName, lastName);
        Assert.assertNotNull(customer2);

        Customer customer3 = customerService.getById(customer2.getId());
        Assert.assertNotNull(customer3);
        Assert.assertEquals(firstName, customer3.getFirstName());
        Assert.assertEquals(lastName, customer3.getLastName());
    }

    @Test
    public void testUpdate() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> people = customerService.get();

        Customer customer2 = findInList(people, customer1.getFirstName(), customer1.getLastName());
        Assert.assertNotNull(customer2);

        String updateFirstName = Long.toString(System.currentTimeMillis());
        String updateLastName = Long.toString(System.currentTimeMillis());

        customer2.setFirstName(updateFirstName);
        customer2.setLastName(updateLastName);
        customerService.update(customer2);

        people = customerService.get();

        Customer person3 = findInList(people, updateFirstName, updateLastName);
        Assert.assertNotNull(person3);
        Assert.assertEquals(customer2.getId(), person3.getId());
    }

    @Test
    public void testDelete() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> people = customerService.get();

        Customer customer2 = findInList(people, customer1.getFirstName(), customer1.getLastName());
        Assert.assertNotNull(customer2);

        customerService.delete(customer2.getId());

        people = customerService.get();
        Customer person3 = findInList(people, customer1.getFirstName(), customer1.getLastName());
        Assert.assertNull(person3);
    }

    private Customer createTestCustomer() {
        // Get unique names every time this test runs
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }


    private Customer findInList(List<Customer> people, String first, String last) {
        // Find the new person in the list
        for (Customer person : people) {
            if (person.getFirstName().equals(first) && person.getLastName().equals(last)) {
                return person;
            }
        }
        return null;
    }
}