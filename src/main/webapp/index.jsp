<!DOCTYPE html>
<!-- [START_EXCLUDE] -->
<%--
  ~ Copyright 2017 Google Inc.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License"); you
  ~ may not use this file except in compliance with the License. You may
  ~ obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
  ~ implied. See the License for the specific language governing
  ~ permissions and limitations under the License.
  --%>
<!-- [END_EXCLUDE] -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@include file="includes/header.jsp"%>

<body>
  <%@include file="includes/navbar.jsp"%>
  <h1>Welcome to Pizza azziP</h1>
  <%
    String user = (String) request.getAttribute("user");
    if(user != null){
      String logout = (String) request.getAttribute("logout");
      out.println(
        "<p>Hello, "
        + user
        + "!  You can <a href=\""
        + logout
        + "\">sign out</a>.</p>");
    }else{
      String login = (String) request.getAttribute("login");
      out.println(
        "<p>Please <a href=\""
        + login 
        + "\">sign in</a>.</p>");
    }
  %>
  <img src="http://www.nag-pizza.fr/images/pizza-image_2.jpg" />

  <%@include file="includes/footer.jsp"%>
</body>

</html>