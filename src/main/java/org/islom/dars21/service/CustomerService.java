package org.islom.dars21.service;

import org.islom.dars21.entity.Customer;
import org.islom.dars21.payload.ApiResponse;
import org.islom.dars21.payload.CustomerDto;
import org.islom.dars21.repository.CustomerRepo;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    CustomerRepo customerRepo;

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomer(int id) {
        Optional<Customer> byId = customerRepo.findById(id);
        return byId.orElse(null);

    }

    /*MIJOZ QO'SHDIGAGN METHOD
     * @return ApiResponse
     * Bizga CustomerDto tipidagi json Object keladi
     * Validatsiya qo'ydik*/
    public ApiResponse addCustomer(CustomerDto customerDto) {
        boolean exitsByPhoneNumber = customerRepo.exitsByPhoneNumber(customerDto.getPhoneNumber());
        if (exitsByPhoneNumber) return new ApiResponse("Bunday mijoz mavjud", true);
        Customer customer = new Customer();
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setFullName(customerDto.getFullName());
        customerRepo.save(customer);
        return new ApiResponse("saqlandi", true);
    }
    /*
     * Mijozni tahrirlash
     * @param id
     * @param customerDto
     * @return ApiResponse
     * Bizga id va Customer tipida json Object beradu*/
    public ApiResponse updateCustomer(int id, CustomerDto customerDto) {
        boolean exitsByPhoneNumberAndIdNot = customerRepo.exitsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id);
        if (exitsByPhoneNumberAndIdNot) {
            return new ApiResponse("Bunday Telefin raqammli mivoj mavjud", false);
        }
        Optional<Customer> byId = customerRepo.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("Bunday mijoz mavjud emas", false);

        Customer customer = byId.get();
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setFullName(customerDto.getFullName());
        customerRepo.save(customer);
        return new ApiResponse("saqlandi", true);
    }

    public ApiResponse deleteCustomer(int id) {
        Optional<Customer> byId = customerRepo.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("o'chirishda xatoli",false);
        customerRepo.delete(byId.get());
        return new ApiResponse("o'chirildi",true);
    }
}
