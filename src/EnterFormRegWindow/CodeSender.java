package EnterFormRegWindow;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


class CodeSender {

    public void SendCode(String code, String email, String password, String surname, String name) throws IOException, MessagingException, javax.mail.MessagingException, java.net.ConnectException {
        final Properties properties = new Properties();
        FileInputStream in = new FileInputStream("mail.properties");
        properties.load(in);

        /** Creating a mailSession , that making a template of letter*/
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("kursovairabota@gmail.com", "Admin"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Registration code");
        message.setText("Hello, " + name + " " + surname + "!" + "\n Your registration code is: " + code + "\n Your password is: "
                + password + "\n Please,ask administrator to complete your registration.");


        //sending code for registration onto users email

        Transport tr = mailSession.getTransport();
        tr.connect("kursovairabota", "kursovairabota123");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();


    }


}