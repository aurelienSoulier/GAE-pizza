
package com.urca.das;


import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;

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

@WebServlet(name = "NousContacterAppEngine", value = "/nous-contacter")
public class NousContacterAppEngine extends HttpServlet {
  public static final String VUE           = "/nousContacter.jsp";
  public static final String ATT_ERREURS   = "erreurs";
  public static final String ATT_RESULTAT  = "resultat";

  public static final String CHAMP_MESSAGE = "message";

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
        String message = request.getParameter( CHAMP_MESSAGE );
        
        /* Validation des champs. */
        if(message.isEmpty())
        {
            erreurs.put(CHAMP_MESSAGE,"veuillez ecrire un message.");
        }

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
          
          //envoi du mail
          UserService userService = UserServiceFactory.getUserService();
          Properties props = new Properties();
          Session session = Session.getDefaultInstance(props, null);
        
          String msgBody = "message envoyer par : "+userService.getCurrentUser().getNickname()+"\n"+message;
        
          try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(userService.getCurrentUser().getEmail(),userService.getCurrentUser().getNickname()));
            msg.addRecipient(Message.RecipientType.TO,
                            new InternetAddress("anything@civic-indexer-233507.appspotmail.com", "Pizza azziP"));
            msg.setSubject("Demande de contact");
            msg.setText(msgBody);
        
            Transport.send(msg);
            resultat = "Succes de l'envoi du mail.";
          } catch (AddressException e) {
            resultat = "Echec de l'envoi du mail.";
          } catch (MessagingException e) {
            resultat = "Echec de l'envoi du mail.";
          } catch (UnsupportedEncodingException e) {
            resultat = "Echec de l'envoi du mail.";
          }
        } else {
          resultat = "Parametres incorrect.";
        }

        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
      }
    }
}
