package com.afidal.facturacionservice.repositories;

import com.afidal.facturacionservice.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, String> {

  Optional<List<Bill>> findByCustomerBill(String customer);


}
