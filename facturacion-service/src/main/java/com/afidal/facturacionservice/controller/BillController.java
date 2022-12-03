package com.afidal.facturacionservice.controller;

import com.afidal.facturacionservice.models.Bill;
import com.afidal.facturacionservice.models.dto.BillDTO;
import com.afidal.facturacionservice.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

  private final BillService service;

  public BillController(BillService service) {
    this.service = service;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Bill>> getAll() {
    return ResponseEntity.ok().body(service.getAllBill());
  }

  @PostMapping()
  @PreAuthorize("hasAuthority('SCOPE_facturacion:gestion') AND hasAuthority('GROUP_PROVIDER') AND hasRole('ROLE_PROVIDER')")
  public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
    return ResponseEntity.ok().body(service.saveBill(bill));
  }

  @GetMapping("/findBy")
  public ResponseEntity<List<Bill>> findByCustomer(@RequestParam String customer) {
    List<Bill> bills = service.findByCustomer(customer);
    if (bills != null) {
      return ResponseEntity.ok().body(bills);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/detail/{username}")
  @PreAuthorize("hasAuthority('GROUP_CLIENT') AND hasRole('ROLE_CLIENT')")
  public ResponseEntity<BillDTO> findBillsByUsername(@PathVariable String username) {
    return ResponseEntity.ok().body(service.findBillsByUsername(username));
  }

}
