<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%@include file="includes/header.jsp"%>

<body>
    <%@include file="includes/navbar.jsp"%>
    <div>
        <form method="post" action="nous-contacter">
            <fieldset>
                <legend>Message :</legend>
                <div class="form-group">
                    <label for="description">message <span class="requis">*</span></label>
                    <input type="text" id="message" name="message" value="<c:out value="${param.message}" />"
                    size="100" maxlength="256" />
                    <br><span class="erreur">${erreurs['message']}</span>
                </div>

                <input type="submit" value="Valider" class="btn btn-primary"/>
                <input type="reset" value="Remettre à zéro" class="btn btn-secondary"/> <br />

                <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
            </fieldset>
        </form>
    </div>
    <%@include file="includes/footer.jsp"%>
</body>

</html>