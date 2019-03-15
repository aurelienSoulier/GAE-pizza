<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%@include file="includes/header.jsp"%>

<body>
    <%@include file="includes/navbar.jsp"%>
    <div class="container-fluid">
        <c:forEach items="${list}" var="item">
            <div class="col-md-3">
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" src=<c:out value="${item.image}" /> alt=<c:out value="${item.name}" />>
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${item.name}" /></h5>
                        <p class="card-text"><c:out value="${item.description}" /></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%@include file="includes/footer.jsp"%>
</body>

</html>