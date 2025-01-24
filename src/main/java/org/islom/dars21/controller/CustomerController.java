package org.islom.dars21.controller;

import jakarta.validation.Valid;
import org.islom.dars21.entity.Customer;
import org.islom.dars21.payload.ApiResponse;
import org.islom.dars21.payload.CustomerDto;
import org.islom.dars21.service.CustomerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {

    CustomerService customerService;

    /*Mijozlarni hammanisi olib kelish*/
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    /*Mijozlarni id si bo'yicha olib kelish*/
    @GetMapping("customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    /*MIJOZ QO'SHDIGAGN METHOD
     * @return ApiResponse
     * Bizga CustomerDto tipidagi json Object keladi
     * Validatsiya qo'ydik*/
    @PostMapping("/customers")
    public ApiResponse addCustomer(@Valid @RequestBody CustomerDto customerDto) {

        ApiResponse apiResponse = customerService.addCustomer(customerDto);
        return apiResponse;
    }

    @PostMapping("/customer")
    public HttpEntity<ApiResponse> addCustomerr(@Valid @RequestBody CustomerDto customerDto) {

        ApiResponse apiResponse = customerService.addCustomer(customerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /*
     * Mijozni tahrirlash
     * @param id
     * @param customerDto
     * @return ApiResponse
     * Bizga id va Customer tipida json Object beradu*/
    @PutMapping("/customers/{id}")
    public HttpEntity<ApiResponse> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDto customerDto) {

        ApiResponse apiResponse = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.status(apiResponse.isSuccess()
                ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/customer/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


}
