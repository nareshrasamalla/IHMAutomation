package com.netsuite.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.framework.BaseTest;
import com.framework.Generic;

public class NS_Billing_InvoicePageValidations {
	Generic gen=new Generic();
	
	String invoicePageHeading = "//div[@class='uir-page-title-secondline']/div[3]";
	String subTotalInInvoicePage = "(//span[@id='subtotal_fs_lbl_uir_label']/following::span)[1]";
	String tottalInInvoicePage = "(//span[@id='total_fs_lbl_uir_label']/following::span)[1]";
	
	String suppressRate_InvoicePage = "//span[@id='custbody_ihm_suppress_rate_fs']/img";
	String supressMiscRate_InvoicePage  = "//span[@id='custbody_ihm_suppress_misc_rate_fs_lbl']/../../span/span/img";

	
	public void invoiceValidations(WebDriver driver,BaseTest basetest) {
		try {

			String subTotalTxt = gen.getTextOfElement(driver,subTotalInInvoicePage);
			String totalTxt = gen.getTextOfElement(driver,tottalInInvoicePage);
			if(subTotalTxt.contains(totalTxt)) {
				basetest.test.log(Status.PASS, "Sub total("+subTotalTxt+") and Total("+totalTxt+") are equal");
			}else {
				basetest.test.log(Status.FAIL, "Sub total("+subTotalTxt+") and Total("+totalTxt+") are not equal");
			}
			
			String invoicePagHeadingTxt = gen.getTextOfElement(driver,invoicePageHeading);
			if(invoicePagHeadingTxt.contains("PAID IN FULL")) {
				basetest.test.log(Status.PASS, "Invoice page heading is displayed as :<span style='font-weight:bold;color:blue'>"+invoicePagHeadingTxt+"</span>");
			}else {
				basetest.test.log(Status.FAIL, "Invoice page heading: <span style='font-weight:bold;color:blue'>"+invoicePagHeadingTxt+" </span> is displayed ");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void suppressRateValidations(WebDriver driver,BaseTest basetest){
			

		//oGenericUtils.scrollToElement( driver,suppressRate_InvoicePage);

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		WebElement suppressRate_Invoice = driver.findElement(By.xpath(suppressRate_InvoicePage));
		WebElement supressMiscRate_Invoice = driver.findElement(By.xpath(supressMiscRate_InvoicePage));

		String Suppress_Misc = supressMiscRate_Invoice.getAttribute("alt");
		String Suppress = suppressRate_Invoice.getAttribute("alt");

		if(Suppress.contains("Checked"))
		{
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page SuppressRate Is Selected"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page SuppressRate Is not selected"+"'</span>");
		}

		if(Suppress_Misc.contains("Checked"))
		{
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page SuppressMiscllanious Rate Is Selected"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page SuppressMiscllanious Rate Is not selected"+"'</span>");
		}

	}
}
