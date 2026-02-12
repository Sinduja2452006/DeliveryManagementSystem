package com.wipro.delivery.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.delivery.bean.DeliveryBean;
import com.wipro.delivery.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Administrator admin = new Administrator();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String operation = req.getParameter("operation");

        try {

            // ================= ADD RECORD =================
            if ("newRecord".equals(operation)) {

                DeliveryBean bean = new DeliveryBean();

                String deliveryPerson = req.getParameter("deliveryPerson");
                String customerName = req.getParameter("customerName");
                String deliveryItem = req.getParameter("deliveryItem");
                String status = req.getParameter("status");
                String remarks = req.getParameter("remarks");
                String dateStr = req.getParameter("deliveryDate");

                Date date = null;
                if (dateStr != null && !dateStr.isEmpty()) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }

                bean.setDeliveryPerson(deliveryPerson);
                bean.setCustomerName(customerName);
                bean.setDeliveryItem(deliveryItem);
                bean.setStatus(status);
                bean.setRemarks(remarks);
                bean.setDeliveryDate(date);

                String result = admin.addRecord(bean);
                System.out.println("Result from addRecord = " + result);


                if (result == null || result.equals("FAIL")) {
                    resp.sendRedirect("error.html");
                } else if (result.startsWith("INVALID")
                        || result.equals("ALREADY EXISTS")) {
                    resp.sendRedirect("error.html");
                } else {
                    resp.sendRedirect("success.html");
                }
            }

            // ================= VIEW ONE =================
            else if ("viewRecord".equals(operation)) {

                String customerName = req.getParameter("customerName");
                String dateStr = req.getParameter("deliveryDate");

                Date date = null;
                if (dateStr != null && !dateStr.isEmpty()) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                }

                DeliveryBean bean = admin.viewRecord(customerName, date);

                if (bean == null) {
                    req.setAttribute("message", "No matching records found!");
                } else {
                    req.setAttribute("delivery", bean);
                }

                RequestDispatcher rd = req.getRequestDispatcher("displayDelivery.jsp");
                rd.forward(req, resp);
            }

            // ================= VIEW ALL =================
            else if ("viewAllRecords".equals(operation)) {

                List<DeliveryBean> list = admin.viewAllRecords();

                if (list == null || list.isEmpty()) {
                    req.setAttribute("message", "No records available!");
                } else {
                    req.setAttribute("deliveries", list);
                }

                RequestDispatcher rd = req.getRequestDispatcher("displayAllDeliveries.jsp");
                rd.forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.html");
        }
    }
}
