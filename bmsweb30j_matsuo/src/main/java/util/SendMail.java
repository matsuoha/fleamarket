package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bean.Sale;
import bean.User;

public class SendMail {

	public void sendMail(User user, ArrayList<Sale> list) {

		try {
			Properties props = System.getProperties();

			// SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
			props.put("mail.smtp.host", "sv5215.xserver.jp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					// メールサーバにログインするメールアドレスとパスワードを設定
					return new PasswordAuthentication("test.sender@kanda-it-school-system.com", "kandaSender-2025");
				}
			});

			MimeMessage mimeMessage = new MimeMessage(session);

			// 送信元メールアドレスと送信者名を指定
			mimeMessage.setFrom(
					new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

			// 送信先メールアドレスを指定（ご自分のメールアドレスに変更）
			mimeMessage.setRecipients(Message.RecipientType.TO, user.getEmail());

			// メールのタイトルを指定
			mimeMessage.setSubject("ご利用ありがとうございました。", "iso-2022-jp");

			int total = 0;
			String text = "";
			text += user.getUserid() + "様\n\n";
			text += "本のご購入ありがとうございます。\n以下内容でご注文を受け付けましたので、ご連絡致します。\n\n";
			for (int i = 0; i < list.size(); i++) {
				Sale sale = list.get(i);
				text += sale.getIsbn() + " " + sale.getTitle() + " " + sale.getPrice() + "円　" + sale.getQuantity()
						+ "冊\n";
				total += sale.getPrice() * sale.getQuantity();
			}
			text += "合計 " + total + "円\n\n";
			text += "またのご利用よろしくお願いします。";

			// メールの内容を指定
			mimeMessage.setText(text, "iso-2022-jp");
			// メールの形式を指定
			mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

			// 送信日付を指定
			mimeMessage.setSentDate(new Date());

			// 送信します
			Transport.send(mimeMessage);

			// 送信成功
			System.out.println("送信に成功しました。");

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("送信に失敗しました。\n" + e);
		}
	}
}
