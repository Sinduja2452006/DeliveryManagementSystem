package com.wipro.delivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.delivery.bean.DeliveryBean;
import com.wipro.delivery.util.DBUtil;

public class DeliveryDAO {

	public String createRecord(DeliveryBean bean) {
	    String result = "FAIL";

	    try {
	        Connection connection = DBUtil.getDBConnection();

	        String query = "INSERT INTO DELIVERY_TB VALUES(?,?,?,?,?,?,?)";

	        PreparedStatement ps = connection.prepareStatement(query);

	        ps.setString(1, bean.getDeliveryId());
	        ps.setString(2, bean.getDeliveryPerson());
	        ps.setString(3, bean.getCustomerName());
	        ps.setString(4, bean.getDeliveryItem());
	        ps.setDate(5, new java.sql.Date(bean.getDeliveryDate().getTime()));
	        ps.setString(6, bean.getStatus());
	        ps.setString(7, bean.getRemarks());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            connection.commit();   // ⭐ VERY IMPORTANT
	            result = "SUCCESS";
	        } else {
	            connection.rollback();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}



    public DeliveryBean fetchRecord(String customerName, Date deliveryDate) {

        DeliveryBean bean = null;

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM DELIVERY_TB WHERE CUSTOMERNAME=? AND DELIVERY_DATE=?")) {

            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(deliveryDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new DeliveryBean();
                bean.setDeliveryId(rs.getString("DELIVERYID"));
                bean.setDeliveryPerson(rs.getString("DELIVERYPERSON"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setDeliveryItem(rs.getString("DELIVERYITEM"));
                bean.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public String generateDeliveryID(String customerName, Date deliveryDate) {

        String id = null;

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement("SELECT DELIVERY_SEQ.NEXTVAL FROM DUAL");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                int seq = rs.getInt(1);

                String datePart = new java.text.SimpleDateFormat("yyyyMMdd")
                        .format(deliveryDate);

                String namePart = customerName.toUpperCase();
                if (namePart.length() >= 2) {
                    namePart = namePart.substring(0, 2);
                } else {
                    namePart = namePart + "X";
                }

                id = datePart + namePart + seq;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }


    public boolean recordExists(String customerName, Date deliveryDate) {
        return fetchRecord(customerName, deliveryDate) != null;
    }

    public List<DeliveryBean> fetchAllRecords() {

        List<DeliveryBean> list = new ArrayList<>();

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM DELIVERY_TB")) {

            while (rs.next()) {
                DeliveryBean bean = new DeliveryBean();
                bean.setDeliveryId(rs.getString("DELIVERYID"));
                bean.setDeliveryPerson(rs.getString("DELIVERYPERSON"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setDeliveryItem(rs.getString("DELIVERYITEM"));
                bean.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
