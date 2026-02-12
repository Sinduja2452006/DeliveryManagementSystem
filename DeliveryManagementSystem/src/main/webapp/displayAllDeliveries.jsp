<%@ page language="java" import="java.util.*,com.wipro.delivery.bean.DeliveryBean" %>
<!DOCTYPE html>
<html>
<head><title>All Deliveries</title></head>
<body>
<%
    String msg = (String) request.getAttribute("message");
    List<DeliveryBean> list = (List<DeliveryBean>) request.getAttribute("deliveries");
    if (msg != null) {
%>
    <h3><%= msg %></h3>
<%
    } else if (list != null) {
%>
    <h3>All Deliveries</h3>
    <table border="1">
        <tr>
            <th>ID</th><th>Person</th><th>Customer</th>
            <th>Item</th><th>Date</th><th>Status</th><th>Remarks</th>
        </tr>
<%
        for (DeliveryBean b : list) {
%>
        <tr>
            <td><%= b.getDeliveryId() %></td>
            <td><%= b.getDeliveryPerson() %></td>
            <td><%= b.getCustomerName() %></td>
            <td><%= b.getDeliveryItem() %></td>
            <td><%= b.getDeliveryDate() %></td>
            <td><%= b.getStatus() %></td>
            <td><%= b.getRemarks() %></td>
        </tr>
<%
        }
%>
    </table>
<%
    } else {
%>
    <h3>No data to display</h3>
<%
    }
%>
<a href="menu.html">Back to menu</a>
</body>
</html>
