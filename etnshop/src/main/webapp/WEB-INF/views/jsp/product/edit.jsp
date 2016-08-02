<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>etnShop</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>

<form action="update">
    Id<br>
    <input type="text" name="id" value="${product.id}" readonly><br>
    Product Name<br>
    <input type="text" name="name" value="${product.name}" maxlength="255"><br>
    Serial Number<br>
    <input type="text" name="serialNumber" value="${product.serialNumber}" readonly><br>
    <input type="submit" value="Save changes">
</form>

<footer>
    <p>&copy; Etnetera a.s. 2015</p>
</footer>


<spring:url value="/resources/core/css/bootstrap.min.js"
            var="bootstrapJs"/>

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>