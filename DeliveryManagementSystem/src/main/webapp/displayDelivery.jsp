<%@ page language="java" import="com.wipro.delivery.bean.DeliveryBean" %>
<!DOCTYPE html>
<html>
<head><title>Delivery Details</title></head>
<body>
<%
    String msg = (String) request.getAttribute("message");
    DeliveryBean bean = (DeliveryBean) request.getAttribute("delivery");
    if (msg != null) {
%>
    <h3><%= msg %></h3>
<%
    } else if (bean != null) {
%>
    <h3>Delivery Details</h3>
    ID: <%= bean.getDeliveryId() %><br/>
    Person: <%= bean.getDeliveryPerson() %><br/>
    Customer: <%= bean.getCustomerName() %><br/>
    Item: <%= bean.getDeliveryItem() %><br/>
    Date: <%= bean.getDeliveryDate() %><br/>
    Status: <%= bean.getStatus() %><br/>
    Remarks: <%= bean.getRemarks() %><br/>
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
