<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>

<html lang="pl">
<head>
    <link rel="stylesheet" href="<c:url value="/resources/static/css/index.css"/>" />
    <title>Add a party</title>
</head>
<body>
Give us some info about the party:
    <input type="text" name="name"/>
    <input type="text" name="description"/>
    <input type="date" name="date"/>
    <button value="wyślij"></button>
</body>
</html>