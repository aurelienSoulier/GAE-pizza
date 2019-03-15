
package com.urca.das;


import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;

import com.urca.das.storage.PizzaDatastore;
import com.urca.das.entities.Pizza;

import java.util.List;
@WebServlet(name = "ListeAppEngine", value = "/list")
public class ListeAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      PizzaDatastore data = PizzaDatastore.getInstance();

      List<Pizza> list = data.ListPizzas();
      //List list = new List();
      request.setAttribute("list", list);
	    this.getServletContext().getRequestDispatcher( "/liste.jsp" ).forward( request, response );
  }
}
