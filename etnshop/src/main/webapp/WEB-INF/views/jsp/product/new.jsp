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

<form id="new-product" action="addNew">
    Product Name<br>
    <input id="new-product-name" type="text" name="name" maxlength="255"><br>
    Serial Number<br>
    <input id="new-product-serial" type="text" name="serialNumber" maxlength="11"><br>
    <input type="submit" value="Save changes">
</form>

<footer>
    <p>&copy; Etnetera a.s. 2015</p>
</footer>


<spring:url value="/resources/core/js/validations.js"
            var="validationsJs"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${validationsJs}"></script>
</body>
</html>