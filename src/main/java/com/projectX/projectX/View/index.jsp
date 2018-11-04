<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>

<html lang="pl">
<head>
    <link rel="stylesheet" href="resources/index.css" type="text/css">
</head>
<body>
    Imprezki:
    <ul>
        <li>
            ${party}
        </li>
    </ul>
    <button onclick="window.location.href='add.jsp'">Dodaj imprezę</button>
</body>