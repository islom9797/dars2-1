package org.islom.dars21.repository;

import org.islom.dars21.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    boolean exitsByPhoneNumber(String phoneNumber);

    //select * from customer where phone_number='phone number input' and id<>1
    //1 is id of user
    boolean exitsByPhoneNumberAndIdNot(String phoneNumber,Integer id);
}
