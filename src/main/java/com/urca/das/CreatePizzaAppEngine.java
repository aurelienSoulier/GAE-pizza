
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
import java.util.Map;
import java.util.HashMap;


import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

@WebServlet(name = "CreatePizzaAppEngine", value = "/pizza/create")
public class CreatePizzaAppEngine extends HttpServlet {
  public static final String VUE               = "/creerPizza.jsp";
  public static final String ATT_ERREURS  = "erreurs";
  public static final String ATT_RESULTAT = "resultat";

  public static final String CHAMP_NOM         = "nom";
  public static final String CHAMP_URL         = "urlImage";
  public static final String CHAMP_DESCRIPTION = "description";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      if (request.getUserPrincipal() != null) {
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
      }else{
        response.sendRedirect("/index.html");
      }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      if (request.getUserPrincipal() == null) {
        response.sendRedirect("/index.html");
      }else{
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

        /* Récupération des champs du formulaire. */
        String nom = request.getParameter( CHAMP_NOM );
        String urlImage = request.getParameter( CHAMP_URL );
        String description = request.getParameter( CHAMP_DESCRIPTION );
        
        /* Validation des champs. */
          /*erreurs.put(CHAMP,MESSAGE)*/

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
          PizzaDatastore data = PizzaDatastore.getInstance();
      
          Pizza test = new Pizza.Builder()
          .name(nom)
          .image(urlImage)
          .description(description)
          .build();
          data.addPizza(test);

          resultat = "Succes de l'ajout.";

          //envoi d'un mail de confirmation
          UserService userService = UserServiceFactory.getUserService();
          Properties props = new Properties();
          Session session = Session.getDefaultInstance(props, null);
        
          String msgBody = "";
        
          try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("anything@civic-indexer-233507.appspotmail.com", "Pizza azziP"));
            msg.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(userService.getCurrentUser().getEmail(),userService.getCurrentUser().getNickname()));
            msg.setSubject("Add pizza");
            msg.setText(msgBody);
        
            // [START multipart_example]
            String htmlBody = "<table border='1px'><tr><td>pizza</td><td>image</td><td>description</td></tr><tr><td>"+nom+"</td><td><img src="+urlImage+"></td><td>"+description+"</td></tr></table>";
            byte[] attachmentData = null;  // ...
            Multipart mp = new MimeMultipart();
        
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);
            // [END multipart_example]
        
            Transport.send(msg);
        
          } catch (AddressException e) {
            // ...
          } catch (MessagingException e) {
            // ...
          } catch (UnsupportedEncodingException e) {
            // ...
          }
        } else {
          resultat = "Echec de l'ajout.";
        }

        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
      }
    }
}
