package com.afidal.facturacionservice.service;

import com.afidal.facturacionservice.models.Bill;
import com.afidal.facturacionservice.models.dto.BillDTO;
import com.afidal.facturacionservice.models.dto.KeycloakUser;
import com.afidal.facturacionservice.repositories.BillRepository;
import com.afidal.facturacionservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class BillService {

  private final BillRepository billRepository;
  private final UserRepository userRepository;

  public BillService(BillRepository billRepository, UserRepository userRepository) {
    this.billRepository = billRepository;
    this.userRepository = userRepository;
  }

  public List<Bill> getAllBill() {
    return billRepository.findAll();
  }

  public Bill saveBill(Bill bill) {
    return billRepository.save(bill);
  }

  public List<Bill> findByCustomer(String customer) {
    return billRepository.findByCustomerBill(customer).orElse(null);
  }

  public BillDTO findBillsByUsername(String username){

    //List<Bill> listaFacturas = getAllBill().stream().filter(bill -> bill.getCustomerBill().equals(username)).collect(Collectors.toList());

    BillDTO billDTO = new BillDTO();
    billDTO.setFacturas(findByCustomer(username));
    KeycloakUser keycloakUser = userRepository.findByUsername(username);
    if(keycloakUser != null){
      billDTO.setUsuario(keycloakUser);
    }
    return billDTO;
  }
}
