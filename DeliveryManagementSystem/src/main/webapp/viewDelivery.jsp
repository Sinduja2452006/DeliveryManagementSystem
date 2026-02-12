<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head><title>View Delivery</title></head>
<body>
<form action="MainServlet" method="post">
    <input type="hidden" name="operation" value="viewRecord"/>
    Customer Name: <input type="text" name="customerName"/><br/>
    Delivery Date: <input type="date" name="deliveryDate"/><br/>
    <input type="submit" value="View"/>
</form>
</body>
</html>
