package com.sendEmail.sendEmailContext.Entity;

public class ProductResponse {
	
	private String prodName;
	private String prodQuantity;
	private String prodRetialPrice;
	private String prodPrice;
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(String prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public String getProdRetialPrice() {
		return prodRetialPrice;
	}
	public void setProdRetialPrice(String prodRetialPrice) {
		this.prodRetialPrice = prodRetialPrice;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	@Override
	public String toString() {
		return "ProductResponse [prodName=" + prodName + ", prodQuantity=" + prodQuantity + ", prodRetialPrice="
				+ prodRetialPrice + ", prodPrice=" + prodPrice + "]";
	}
	

}
