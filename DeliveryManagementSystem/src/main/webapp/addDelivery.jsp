<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head><title>Add Delivery</title></head>
<body>
<form action="MainServlet" method="post">
    <input type="hidden" name="operation" value="newRecord"/>
    Delivery Person: <input type="text" name="deliveryPerson"/><br/>
    Customer Name: <input type="text" name="customerName"/><br/>
    Delivery Item: <input type="text" name="deliveryItem"/><br/>
    Delivery Date: <input type="date" name="deliveryDate"/><br/>
    Status: <input type="text" name="status"/><br/>
    Remarks: <input type="text" name="remarks"/><br/>
    <input type="submit" value="Add"/>
    
</form>
</body>
</html>
