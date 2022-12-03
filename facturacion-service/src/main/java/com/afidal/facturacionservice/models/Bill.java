package com.afidal.facturacionservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Bill {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String idBill;

  private Date billingDate;

  private String customerBill;

  private String productBill;

  private Double totalPrice;

  public Bill() {
  }

  public Bill(String idBill, Date billingDate, String customerBill, String productBill, Double totalPrice) {
    this.idBill = idBill;
    this.billingDate = billingDate;
    this.customerBill = customerBill;
    this.productBill = productBill;
    this.totalPrice = totalPrice;
  }

  public String getIdBill() {
    return idBill;
  }

  public void setIdBill(String idBill) {
    this.idBill = idBill;
  }

  public Date getBillingDate() {
    return billingDate;
  }

  public void setBillingDate(Date billingDate) {
    this.billingDate = billingDate;
  }

  public String getCustomerBill() {
    return customerBill;
  }

  public void setCustomerBill(String customerBill) {
    this.customerBill = customerBill;
  }

  public String getProductBill() {
    return productBill;
  }

  public void setProductBill(String productBill) {
    this.productBill = productBill;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
