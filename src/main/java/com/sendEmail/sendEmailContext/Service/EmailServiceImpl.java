package com.sendEmail.sendEmailContext.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.sendEmail.sendEmailContext.Entity.EmailDetails;
import com.sendEmail.sendEmailContext.Entity.ProductResponse;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${pont.email.template.location}")
	private String template;

	public String sendEmail(EmailDetails details) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			String userId=String.valueOf(details.getUserId());
			String cartId=String.valueOf(details.getCartId());
			String url="http://localhost:3001/FeedbackForm/"+userId+"/"+cartId;
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getCustEmail());
			mimeMessageHelper.setSubject("Order Confirmation");
			String htmlBody = getEmailTemplate();
			htmlBody = htmlBody.replace("{{customer_name}}", details.getCustName());
			htmlBody = htmlBody.replace("{{order.order_id}}", details.getOrder_id());
			htmlBody = htmlBody.replace("{{order.delivery_date}}", details.getDelivery_date().substring(0, 10));
			htmlBody = htmlBody.replace("{{order.feedback}}","'"+url+"'");

			String prodcutItem = getProdItemsHTML(details);
			
			htmlBody = htmlBody.replace("{{order.items.details}}", prodcutItem);
			htmlBody = htmlBody.replace("{{order.shipping.price}}", details.getShippingCost());
			htmlBody = htmlBody.replace("{{order.total.price}}", details.getTotalAmount());
			mimeMessage.setText(htmlBody, "utf-8", "html");
			javaMailSender.send(mimeMessage);
			return "Mail Sent successfully";
		} catch (Exception e) {
			return "Error while sending mail!!";
		}
	}

	private static String getEmailTemplate() throws IOException {
		String templatePath = "C:\\Users\\r0k02j0\\Documents\\Ascend\\capstone\\sendEmailContext\\src\\main\\resources\\templates\\emailContext.html";
		StringBuilder content = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(templatePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content.toString();
	}

	private static String getProdItemsHTML(EmailDetails details) {
		List<ProductResponse> prodItems = new ArrayList<ProductResponse>();
		StringBuilder itemsHTML = new StringBuilder();
		itemsHTML.append("<table border='0' width='650'>");
		itemsHTML.append("<tr><th>Product Name</th><th>    Quantity</th><th>     Unit Price</th><th>     Price</th></tr>");
		prodItems = details.getProductResponse();

		for (ProductResponse item : prodItems) {
			itemsHTML.append("<tr>");
			itemsHTML.append("<td>").append(item.getProdName()).append("</td>");
			itemsHTML.append("<td>").append(item.getProdQuantity()).append("</td>");
			itemsHTML.append("<td>").append("$").append(item.getProdRetialPrice()).append("</td>");
			itemsHTML.append("<td>").append("$").append(item.getProdPrice()).append("</td>");
			itemsHTML.append("</tr>");
		}

		itemsHTML.append("</table>");

		return itemsHTML.toString();
	}
}
