package br.com.adaptaconsultoria.amr.springmvc.form;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private Discount discount;
  private List<Item> items = new ArrayList<Item>();

  public Discount getDiscount() {
    return discount;
  }

  public void setDiscount(Discount discount) {
    this.discount = discount;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

}
