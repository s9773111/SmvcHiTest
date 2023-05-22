package test.isaac.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMail {
	public JavaMail() {
		super();
	}

	public JavaMail(String customer, String txt) {
		super();
		this.customer = customer;
		this.txt += txt;
	}

	//1 基本資料
	private String userName = "s9773111@gmail.com"; //寄件者email
	private String password = "hapydloywbncicqu"; //寄件者密碼
	private String customer = ""; //收件者email
	private String subject = "更換密碼";  //信件標題
	private String txt = "新密碼是："; 	  //信件內容
	
	@SuppressWarnings("static-access")
	public void SendMail() {
		//2 連線設定
		Properties prop = new Properties();
		//smtp.gmail.com 設定連線方式
		prop.setProperty("mail.transport.protocol", "smtp");
		//host : smtp.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");
		//host port 465
		prop.put("mail.smtp.port", "465");
		//寄件者帳號需要驗證
		prop.put("mail.smtp.auth", "true");
		//需要安全傳輸層 使用此API來做連線
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//通訊埠
		prop.put("mail.smtp.socketFactory.port", "465");
		
		//顯示連線資訊
		prop.put("mail.debug", "true");
		
		//3 帳號驗證 gmail會對寄件者帳號驗證
		//4 session javamil api 默認屬性(將prop設定值放好，放入session，session會幫忙做好設定)
		
		//getDefaultInstance
		//Session javamail要連線gmail 所有屬性都已經好了。
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//return super.getPasswordAuthentication();
				return new PasswordAuthentication(userName, password);
			}
			
		});
		
	
		//5 message 基本資料 要放入信件的內容
		MimeMessage message = new MimeMessage(session);
		
		//寄件者、收件者
		try {
			message.setSender(new InternetAddress(userName));
			//BBC密件副本、CC副本、To一般收件者
			//收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			//標題
			message.setSubject(subject);
			//內容 / 格式
			message.setContent(txt, "text/html;charset=UTF-8");
			
			//6 Transport Message傳出
			//連線資訊在seesion中
			Transport transport = session.getTransport();
			transport.send(message);
			transport.close();
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
			 
	}

	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
}
	
