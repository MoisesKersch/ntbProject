package br.com.adaptaconsultoria.amr.springmvc.form;

import java.util.ArrayList;
import java.util.List;

public class Shipping {

  private String type;
  private String sourceZipCode;
  private String targetZipCode;
  private Address address;
  private List<Services> services = new ArrayList<Services>();
  private Measures measures;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSourceZipCode() {
    return sourceZipCode;
  }

  public void setSourceZipCode(String sourceZipCode) {
    this.sourceZipCode = sourceZipCode;
  }

  public String getTargetZipCode() {
    return targetZipCode;
  }

  public void setTargetZipCode(String targetZipCode) {
    this.targetZipCode = targetZipCode;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<Services> getServices() {
    return services;
  }

  public void setServices(List<Services> services) {
    this.services = services;
  }

  public Measures getMeasures() {
    return measures;
  }

  public void setMeasures(Measures measures) {
    this.measures = measures;
  }

}
