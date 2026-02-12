package com.wipro.delivery.service;

import java.util.Date;
import java.util.List;

import com.wipro.delivery.bean.DeliveryBean;
import com.wipro.delivery.dao.DeliveryDAO;

public class Administrator {

    DeliveryDAO dao = new DeliveryDAO();

    public String addRecord(DeliveryBean bean) {

        if (bean == null)
            return "INVALID INPUT";

        if (bean.getCustomerName() == null || bean.getCustomerName().trim().length() < 2)
            return "INVALID CUSTOMER NAME";

        if (bean.getDeliveryPerson() == null || bean.getDeliveryPerson().trim().length() < 2)
            return "INVALID DELIVERY PERSON";

        if (bean.getDeliveryItem() == null || bean.getDeliveryItem().trim().length() < 2)
            return "INVALID DELIVERY ITEM";

        if (bean.getDeliveryDate() == null)
            return "INVALID DATE";

        String customerName = bean.getCustomerName().trim();
        Date deliveryDate = bean.getDeliveryDate();

        if (dao.recordExists(customerName, deliveryDate))
            return "ALREADY EXISTS";

        String id = dao.generateDeliveryID(customerName, deliveryDate);
        bean.setDeliveryId(id);

        return dao.createRecord(bean);
    }

    public DeliveryBean viewRecord(String customerName, Date deliveryDate) {
        return dao.fetchRecord(customerName, deliveryDate);
    }

    public List<DeliveryBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
