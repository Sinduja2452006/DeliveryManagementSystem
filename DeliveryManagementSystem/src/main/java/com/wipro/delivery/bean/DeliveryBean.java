package com.wipro.delivery.bean;

import java.util.Date;

public class DeliveryBean {

    private String deliveryId;
    private String deliveryPerson;
    private String customerName;
    private String deliveryItem;
    private Date deliveryDate;
    private String status;
    private String remarks;

    public String getDeliveryId() {
        return deliveryId;
    }
    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
    public String getDeliveryPerson() {
        return deliveryPerson;
    }
    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getDeliveryItem() {
        return deliveryItem;
    }
    public void setDeliveryItem(String deliveryItem) {
        this.deliveryItem = deliveryItem;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
