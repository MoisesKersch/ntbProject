package br.com.adaptaconsultoria.amr.springmvc.form;

import java.util.Date;

public class RecurrentPayment {

  private String interval;
  private Date endDate;

  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
