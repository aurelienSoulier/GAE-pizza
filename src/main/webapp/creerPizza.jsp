<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%@include file="includes/header.jsp"%>

<body>
    <%@include file="includes/navbar.jsp"%>
    <div>
        <form method="post" action="create">
            <fieldset>
                <legend>Informations</legend>
                <div class="form-group">
                    <label for="nom">Nom <span class="requis">*</span></label>
                    <input type="text" id="nom" name="nom" value="<c:out value="${param.nom}" />" size="20"
                    maxlength="20"/>
                    <span class="erreur">${erreurs['nom']}</span>
                </div>
                <div class="form-group">
                    <label for="urlImage">url de l'image <span class="requis">*</span></label>
                    <input type="text" id="urlImage" name="urlImage" value="<c:out value="${param.urlImage}" />"/>
                    <span class="erreur">${erreurs['urlImage']}</span>
                </div>
                <div class="form-group">
                    <label for="description">description <span class="requis">*</span></label>
                    <input type="text" id="description" name="description" value="<c:out value="${param.description}" />"
                    size="100" maxlength="100" />
                    <span class="erreur">${erreurs['description']}</span>
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