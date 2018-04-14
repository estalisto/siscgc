/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author CIMA2015
 */
public class EnviarEmail {
    
 private String emailDestinatario;

    private String assunto;
    private String msg;

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean enviarGmail() {
        boolean retorno = false;
        //Get the session object  
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session s = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("programatche@gmail.com", "c0l0rad0*");//email e senha usuário 
                    }
                });

        try {
            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress("programatche@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.emailDestinatario));

            message.setSubject(this.assunto);
            message.setContent(this.msg, "text/html; charset=utf-8");

            //send message  
            Transport.send(message);

            retorno = true;

        } catch (MessagingException e) {
            retorno = false;
            e.printStackTrace();
        }
        return retorno;
    }
    
    public boolean enviarHotmail()
    {
        boolean retorno = false; 
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("stalyn.granda@hotmail.com", "St@lyn2008");
                             }
                        });
            /** Ativa Debug para sessão */
            session.setDebug(true);
            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("stalyn.granda@hotmail.com")); //Remetente

                  message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("barcestalyn@gmail.com")); //Destinatário(s)
                  message.setSubject("Prueba Hotmail");//Assunto
                  message.setText("Correor de Prueba");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  System.out.println("Feito!!!");
                  retorno = true;
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
            
            return retorno;
    
    }

     public boolean enviarHotmail2(String pass)
    {
        boolean retorno = false; 
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("stalyn.granda@hotmail.com", "$t@lyn2008");
                             }
                        });
            /** Ativa Debug para sessão */
            session.setDebug(true);
            try {
                String NombreArchivo="CuadreDeCaja_";
               /* BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(
                new DataHandler(new FileDataSource("/home/prueba.txt")));
                adjunto.setFileName("/home/prueba.txt");
                // Una MultiParte para agrupar texto e imagen.
                MimeMultipart multiParte = new MimeMultipart();
                //ltiParte.addBodyPart(texto);
                multiParte.addBodyPart(adjunto);
                  */
                
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("stalyn.granda@hotmail.com")); //Remetente
                  message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("barcestalyn@gmail.com")); //Destinatário(s)
                  message.setSubject("Creacion de Usuario");//Assunto
                  message.setText("Su contraseña para ingresar al sistema es :"+pass);
                  //message.setContent(multiParte);
                  message.setFileName("D:\\Proyecto\\laticobsa\\build\\web\\WEB-INF\\CuadresCaja\\CuadreDeCaja_20170827154334.pdf");
                  
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  System.out.println("Feito!!!");
                  retorno = true;
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
            
            return retorno;
    
    }
     public boolean SendEnviarEmail(String asunto, String contenido, String archivo, String correopara)
    {
        boolean retorno = false; 
        String AsuntoText="";
        String ContenidoMail="";
        String NombreArchivo="";
        String CorreoEnviado="";
        AsuntoText=asunto;
        ContenidoMail= contenido;
        NombreArchivo=archivo;
        //NombreArchivo="D:\\Proyecto\\laticobsa\\build\\web\\WEB-INF\\CuadresCaja\\CuadreDeCaja_20170827154334.pdf";
        CorreoEnviado=correopara;
                
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            
    

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("stalyn.granda@hotmail.com", "$t@lyn2008");
                             }
                        });
            /** Ativa Debug para sessão */
            session.setDebug(true);
            
              try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("stalyn.granda@hotmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(CorreoEnviado));
            BodyPart adjunto;
            adjunto = new MimeBodyPart();
            BodyPart texto = new MimeBodyPart();
            texto.setText(asunto);
            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
            adjunto.setFileName(NombreArchivo);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(adjunto);
            multiParte.addBodyPart(texto);
            message.setSubject(AsuntoText);
            message.setText(ContenidoMail);
            message.setContent(multiParte);
            Transport.send(message);
            
                 // Transport.send(message);
                  System.out.println("se envio correctamente!!!");
                  retorno = true;
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
            
            return retorno;
    
    }
    
         

   

}
