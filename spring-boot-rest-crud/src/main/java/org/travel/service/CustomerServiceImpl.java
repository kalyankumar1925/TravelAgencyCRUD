package org.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.travel.entity.Customer;
import org.travel.exception.RecordNotFoundException;
import org.travel.exception.Validation;
import org.travel.repository.CustomerRepository;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl{

    @Autowired
    private CustomerRepository customerRepository;


    public String saveCustomer(Customer c) throws Validation {
        String phone = c.getPhone();
        if(phone.length() == 10 && Pattern.compile("-?\\d+(\\.\\d+)?").matcher(phone).matches()){
            String fname = c.getFirstName();
            String lname = c.getLastName();
            if(fname.length()>4 && fname.length()<12 && lname.length()>4 && lname.length()<12)
            {
                customerRepository.save(c);
                return "Record Created Successfully!!!!!";
            }
            else
                throw new Validation("Please Check length of FirstName or LastName");
        }
        else
            throw new Validation("Invalid Phone Number Format");
    }


    public String deleteCustomer(long id) throws RecordNotFoundException {
        Optional<Customer> data = customerRepository.findById(id);
        if(data.isPresent()) {
            customerRepository.deleteById(id);
            return "Record Deleted Successfully!!";
        }
        else
            throw new RecordNotFoundException("No Record Found with the Id!!");

    }


    public String updateCustomer(Customer c, long id) throws RecordNotFoundException, Validation {
        Customer customer = customerRepository.findById(id).get();
        if(customer.getId()!=0) {
            customer.setFirstName(c.getFirstName());
            customer.setLastName(c.getLastName());
            customer.setSource(c.getSource());
            customer.setDestination(c.getDestination());
            customer.setPackageType(c.getPackageType());
            customer.setPhone(c.getPhone());
            customer.setAddress(c.getAddress());
            customer.setCost(c.getCost());
            customer.setComments(c.getComments());
        }
        else
            throw new RecordNotFoundException("Record Not Existed!!!!");
        saveCustomer(customer);
        return "Updated Record Successfully!!!";
    }


    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer findCustomerById(long id) throws RecordNotFoundException {
        Optional<Customer> data = customerRepository.findById(id);
        if(data.isPresent())
            return data.get();
        else
            throw new RecordNotFoundException("No Record Found with the Id!!");
    }
}
