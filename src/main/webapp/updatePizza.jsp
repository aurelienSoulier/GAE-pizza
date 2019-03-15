<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Pizza azziP</title>
    <link type="text/css" rel="stylesheet" href="form.css" />
</head>

<body>
    <div>
        <form method="post" action="update">
            <fieldset>
                <legend>Informations</legend>

                <label for="nom">Nom <span class="requis">*</span></label>
                <input type="text" id="nom" name="nom" value="<c:out value=" ${param.nom}" />" size="20" maxlength="20"
                />
                <span class="erreur">${erreurs['nom']}</span>
                <br />

                <label for="urlImage">url de l'image <span class="requis">*</span></label>
                <input type="text" id="urlImage" name="urlImage" value="<c:out value=" ${param.urlImage}" />" size="20"
                maxlength="20" />
                <span class="erreur">${erreurs['urlImage']}</span>
                <br />

                <label for="description">description <span class="requis">*</span></label>
                <input type="text" id="description" name="description" value="<c:out value=" ${param.description}" />"
                size="100" maxlength="100" />
                <span class="erreur">${erreurs['description']}</span>
                <br />

                <input type="submit" value="Valider" />
                <input type="reset" value="Remettre à zéro" /> <br />

                <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
            </fieldset>
        </form>
    </div>
    <%@include file="includes/footer.jsp"%>
</body>

</html>