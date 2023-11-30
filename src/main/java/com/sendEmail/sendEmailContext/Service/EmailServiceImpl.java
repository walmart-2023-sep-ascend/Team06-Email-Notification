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
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Value("${pont.email.template.location}")
	private String template;

	public String sendEmail(EmailDetails details) {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			/*
			 * OrderDetails order = new OrderDetails(); UserDetails user = new
			 * UserDetails(); ProductDetails prod = new ProductDetails();
			 */
		// System.out.println(details);
		//ProductResponse productresponse=new ProductResponse();
		/*details.setCustName(details.getCustName());
		 details.setCustEmail(details.getCustEmail());
		 details.setOrder_id(details.getOrder_id());
		 details.setDelivery_date(details.getDelivery_date());
		 details.setShippingCost(details.getShippingCost());
		 details.setTotalAmount(details.getTotalAmount());
		 details.setProductResponse(details.getProductResponse());*/
		 //productresponse.setProdName("Iphone 15");
		 //productresponse.setProdQuantity("1");
		 //productresponse.setProdPrice("100");
		 
         MimeMessageHelper mimeMessageHelper;
         try {
         mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
         mimeMessageHelper.setFrom(sender);
         mimeMessageHelper.setTo(details.getCustEmail());
         mimeMessageHelper.setSubject("Order Confirmation");
         String htmlBody = getEmailTemplate();
         //String price = Double.toString(getProductSubTotalPrice());
         //htmlBody = htmlBody.replace("{{order_items}}", getOrderItemsHTML());
         //htmlBody = htmlBody.replace("{{total_amount}}", "Total Amount: $30.00");
         htmlBody = htmlBody.replace("{{customer_name}}", details.getCustName());
         htmlBody = htmlBody.replace("{{order.order_id}}", details.getOrder_id());
         
         String prodcutItem= getProdItemsHTML(details);
         
         htmlBody = htmlBody.replace("{{order.items.details}}", prodcutItem);
        // htmlBody = htmlBody.replace("{{order.items.quantity}}", details.getProdQuantity());
         //htmlBody = htmlBody.replace("{{order.items.price}}", details.getProdRetialPrice());
         //htmlBody = htmlBody.replace("{{order.subtotal.price}}", details.getProdPrice());
         
         //htmlBody = htmlBody.replace("{{order.subtotal.price}}", details.getProdPrice());
         
         htmlBody = htmlBody.replace("{{order.shipping.price}}", details.getShippingCost());
         
         
         htmlBody = htmlBody.replace("{{order.total.price}}", details.getTotalAmount());
         mimeMessage.setText(htmlBody, "utf-8", "html");
         javaMailSender.send(mimeMessage);
         return "Mail Sent successfully";
		}
        catch(Exception e) {
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
	
	/*private static double getProductSubTotalPrice() {
        List<Double> productPrices = new ArrayList<>();
        ProductDetails prod = new ProductDetails();
        productPrices.add(prod.setProdPrice("100"));
        return productPrices.stream().mapToDouble(Double::doubleValue).sum();
    } */

	
	private static String getProdItemsHTML(EmailDetails details) {
	    List<ProductResponse> prodItems = new ArrayList<ProductResponse>();
	    /*ProductResponse productresponse=new ProductResponse();
	    productresponse.setProdName("Iphone 15");
		 productresponse.setProdQuantity("2");
		 productresponse.setProdRetialPrice("50");
		 productresponse.setProdPrice("100");
		 
		 ProductResponse productresponse1=new ProductResponse();
		    productresponse1.setProdName("Iphone 14");
			 productresponse1.setProdQuantity("1");
			 productresponse1.setProdRetialPrice("50");
			 productresponse1.setProdPrice("50");
			 
			 prodItems.add(productresponse);
			 prodItems.add(productresponse1);*/
		 
	    StringBuilder itemsHTML = new StringBuilder();
	    itemsHTML.append("<table border='0' width='650'>");
	    itemsHTML.append("<tr><th>Product</th><th>    Quantity</th><th>     Unit Price</th><th>     Price</th></tr>");
	    
	      prodItems=details.getProductResponse();
	    //ProductDetails[] productdetails;
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

	/*
	private static List<ProductResponse> () {
	    List<ProductResponse> orderItems = new ArrayList<>();
	    return orderItems;
	} */
}
