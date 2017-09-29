package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;


    @Override
    @Transactional
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }


    @Override
    public Customer getById(int id) {
        return customerRepository.findOne(id);
    }


    @Override
    public List<Customer> get() {
        return customerRepository.findAll();
    }



    @Override
    @Transactional
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(int id) {
        customerRepository.delete(id);
    }


    // Map a row of the result set to a person object
    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            return customer;
        }

    }
}
