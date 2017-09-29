package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.CustomerRepositoryImpl;
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

    private final String INSERT_SQL = "INSERT INTO customer (firstName, lastName) VALUES (?,?)";

    @Override
    @Transactional
    public void add(Customer customer) {
        customerRepository.add(customer);
    }


    @Override
    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    private final String SELECT_SQL = "SELECT * FROM customer";

    @Override
    public List<Customer> get() {
        return customerRepository.get();
    }



    @Override
    @Transactional
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    private final String DELETE_SQL = "DELETE FROM person WHERE id=?";
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
