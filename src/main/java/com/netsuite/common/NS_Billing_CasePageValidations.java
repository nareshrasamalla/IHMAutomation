package com.netsuite.common;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.framework.BaseTest;
import com.framework.Generic;

public class NS_Billing_CasePageValidations {
	Generic gen=new Generic();

	//Case page locators
	String processStatus = "(//span[@id='custevent_process_status_fs_lbl_uir_label']/following::span)[1]";
	String statusText = "(//span[@id='custevent_case_approval_fs_lbl_uir_label']/following::span/span)[1]";
	String adjustmentType = "(//span[@id='custevent_ihm_adjustmenttype_fs_lbl']/following::span)[1]";
	String customer = "(//span[@id='company_fs_lbl_uir_label']/following::span)[1]";
	String eMail = "(//span[@id='email_fs_lbl_uir_label']/following::span)[1]";
	String invoiceTotalAmt = "//span[@id='custevent_inv_total_amt_fs_lbl_uir_label']/../span[2]";
	String invoiceRemaningAmt = "//span[@id='custevent_ihm_invremainingamount_fs_lbl_uir_label']/../span[2]";
	String invoicesOrCMS = "(//a[text()='I'])[1]";
	String creditMemoType = "//td[ starts-with(@class,'listtext') and text()='Credit Memo']"; 
	String creditButn = "//input[@id='credit']";
	String totalApplyAmt = "//a[@id='itemtxt']/span[2]";
	String statusInCasePage = "//td[ starts-with(@class,'listtext') and text()='Fully Applied']";
	//checkBox
	String autoApprovedCheckBox = "//span[@id='custevent_case_auto_approved_fs_lbl']/../../span/span/img";
	String splitByStationCheckBox = "//span[@id='custevent_spit_by_station_fs_lbl_uir_label']/../span/span/img";
	String splitByProductTypeCheckBox ="//span[@id='custevent_split_by_product_fs']/img";
	String splitByLineTypeCheckBox  ="//span[@id='custevent_split_by_line_type_fs']/img";
	String splitByMarketTypeCheckBox = "//span[@id='custevent_split_by_market_fs']/img";
	String splitByISCICheckBox = "//span[@id='custevent_split_by_isci_fs']/img";

	String caseAdjID = "//input[@name='CUSTBODY_IHM_CASEADJID_display']";
	String invoiceCMsTable = "//table[starts-with(@id,'customsublist')]/tbody/tr";
	String invoiceNoLink ="(//span[@id='custevent_ihm_case_inv_no_fs_lbl_uir_label']/following::span)[1]";
	String invoiceCMS 	= 	"//span[@class='inputreadonly']/a[contains(text(),'Invoice')]";


	//Issue Credit to client
	String preference = "//input[@name='inpt_newinvoicerequired']";
	String invoicePage = "//span[@id='invnum_text_fs_lbl']/a[contains(text(),'Invoice Number')]";
	String fullCreditcheck = "//span[@id='custpage_fullcredit_fs']";
	String invoiceAmount = "//input[@id='custpage_creditamount_formattedValue']";
	String ok_btn = "//input[@id='invoicelines_addedit']";
	String linecreditamount = "//input[@id='linecreditamount_formattedValue']";
	String autoApproval = "//span[@id='custevent_case_approval_fs_lbl_uir_label']/following::span[1]/span";
	String adjustmentAmount = "//span[@id='custevent_ihm_adjamount_fs_lbl']/following::span[1]";
	String invoiceTotalamount = "//span[@id='custevent_inv_total_amt_fs_lbl']/following::span[1]";

	public void casePageValidations(WebDriver driver,String splitingType,String invoiceNo,String caseNumberText,BaseTest basetest) {
		String splitTypeCheckBox="";
		if (splitingType.contains("ISCI")) {			
			splitTypeCheckBox = splitByISCICheckBox;

		} else if (splitingType.contains("MarketType")) {			
			splitTypeCheckBox = splitByMarketTypeCheckBox;

		} else if (splitingType.contains("ProductType")) {			
			splitTypeCheckBox = splitByProductTypeCheckBox;

		} else if (splitingType.contains("SplitStation")) {			
			splitTypeCheckBox = splitByStationCheckBox;

		} else if (splitingType.contains("SpotAndMisc")) {			
			splitTypeCheckBox = splitByLineTypeCheckBox;
		}
		try {
			String processStatusText = gen.getTextOfElement(driver, processStatus);
			if(processStatusText.contains("Complete")){
				basetest.test.log(Status.PASS, "Process status is : <span style='font-weight:bold;color:blue'> "+processStatusText+"</span>");
			}else{
				basetest.test.log(Status.FAIL, "Process status is : <span style='font-weight:bold;color:blue'> "+processStatusText+"</span>");
			}

			String statusTextInUI = gen.getTextOfElement(driver, statusText);
			if(statusTextInUI.contains("Approved")){
				basetest.test.log(Status.PASS, "Status displayed as : <span style='font-weight:bold;color:blue'> "+statusTextInUI+"</span>");
			}else{
				basetest.test.log(Status.FAIL, "Status displayed as :<span style='font-weight:bold;color:blue'> "+statusTextInUI+"</span>");
			}

			//
			String adjustmentTypeText = gen.getTextOfElement(driver, adjustmentType);
			if(adjustmentTypeText.contains("Split Invoice")){
				basetest.test.log(Status.PASS, "Adjustment Type displayed as : <span style='font-weight:bold;color:blue'>"+adjustmentTypeText+"</span>");
			}else{
				basetest.test.log(Status.FAIL, "Adjustment Type displayed as : <span style='font-weight:bold;color:blue'>"+adjustmentTypeText+"</span>");
			}

			String invoiceNumber = gen.getTextOfElement(driver, invoiceNoLink);
			if(invoiceNumber.length()>0) {
				basetest.test.log(Status.PASS, "Invoice number displayed as : <span style='font-weight:bold;color:blue'>"+invoiceNumber+"</span>");
			}else {
				basetest.test.log(Status.FAIL, "Invoice number displayed as : <span style='font-weight:bold;color:blue'>"+invoiceNumber+"</span>");
			}

			String customerText = gen.getTextOfElement(driver, customer);
			if(customerText.length()>0){
				basetest.test.log(Status.PASS, "Customer displayed as : <span style='font-weight:bold;color:blue'>"+customerText+"</span>");
			}else{
				basetest.test.log(Status.FAIL, "Customer is not displayed");
			}

			String emailText = gen.getTextOfElement(driver, eMail);
			if(emailText.length()>0) {
				basetest.test.log(Status.PASS, "Email :<span style='font-weight:bold;color:blue'>"+emailText+"</span> is displayed");
			}else {
				basetest.test.log(Status.FAIL, "Email is not displayed");
			}

			String invoiceTotalAmtText = gen.getTextOfElement(driver,invoiceTotalAmt);

			String invoiceRemainingAmtText = gen.getTextOfElement(driver,invoiceRemaningAmt);
			if(invoiceTotalAmtText.contains(invoiceRemainingAmtText)) {
				basetest.test.log(Status.PASS, " Invoice total amount("+invoiceTotalAmtText+") and Invoice remaining amount("+invoiceRemainingAmtText+") are same ");
			}else {
				basetest.test.log(Status.FAIL, " Invoice total amount("+invoiceTotalAmtText+") and Invoice remaining amount("+invoiceRemainingAmtText+") are not same ");
			}
			WebElement autoApproviedElement = driver.findElement(By.xpath(autoApprovedCheckBox));
			WebElement splitByISCIElement = driver.findElement(By.xpath(splitTypeCheckBox));

			String checkboxAutoApp = autoApproviedElement.getAttribute("alt").trim();

			String checkboxSplitByStation = splitByISCIElement.getAttribute("alt").trim();

			if(checkboxAutoApp.contains("Checked")){
				basetest.test.log(Status.PASS, "Auto Approvied check Box is selected");
			}else{
				basetest.test.log(Status.FAIL, "Auto Approvied check Box is not selected");
			}
			if(checkboxSplitByStation.contains("Checked")){
				basetest.test.log(Status.PASS, "Split By Product Type check Box is selected");
			}else{
				basetest.test.log(Status.FAIL, "Split By Product Type Box is not selected");
			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1500)");

			gen.clickButton(driver, By.xpath(invoicesOrCMS),"Click on Invoice/CMs",basetest);

			Thread.sleep(4000);	
			//enter case ID click on 
			gen.SetVal(driver, By.xpath(caseAdjID), caseNumberText,"Case number entered",basetest);
			driver.findElement(By.xpath(caseAdjID)).sendKeys(Keys.ENTER);
			Thread.sleep(3000);

			if(!(driver.findElements(By.xpath("//table[starts-with(@id,'customsublist')]//td[text()='No records to show.']")).size()>0)) {			
				String creditMemoTypeTxt = gen.getTextOfElement(driver, creditMemoType);
				if (creditMemoTypeTxt.contains("Credit Memo")) {
					basetest.test.log(Status.PASS, "Type :<span style='font-weight:bold;color:blue'>" + creditMemoTypeTxt + "</span> is displayed");
				} else {
					basetest.test.log(Status.FAIL, "Type :<span style='font-weight:bold;color:blue'>" + creditMemoTypeTxt + "</span> is displayed");
				}

				String statusTxt = gen.getTextOfElement(driver, statusInCasePage);
				if (statusTxt.contains("Fully Applied")) {
					basetest.test.log(Status.PASS, "Status :<span style='font-weight:bold;color:blue'>" + statusTxt + "</span> is displayed");
				} else {
					basetest.test.log(Status.FAIL, "Status :<span style='font-weight:bold;color:blue'>" + statusTxt + " </span>is displayed");
				}

				List<WebElement> tableInvoiceCMS  = driver.findElements(By.xpath(invoiceCMsTable));
				double creditAmount1=0.00;
				double creditAmount2=0.00;
				String creditAmountStr1="";
				String creditAmountStr2="";
				for(int i =1;i<=tableInvoiceCMS.size();i++) {
					if(i==1) {
						creditAmountStr1 = gen.getTextOfElement(driver, "(//table[starts-with(@id,'customsublist')]/tbody/tr)["+(i)+"]/td[6]");
						if(creditAmountStr1.length()>0) {
							creditAmountStr1=creditAmountStr1.replaceAll("-","");
							creditAmountStr1=creditAmountStr1.replaceAll(",","");
							creditAmount1 =Double.valueOf(creditAmountStr1);
						}
					}
					if(i>1) {
						creditAmountStr2 = gen.getTextOfElement(driver, "(//table[starts-with(@id,'customsublist')]/tbody/tr)["+(i)+"]/td[6]");	
						creditAmountStr2 = creditAmountStr2.replaceAll("-","");
						creditAmountStr2 = creditAmountStr2.replaceAll(",","");
						creditAmount2 = creditAmount2+Double.valueOf(creditAmountStr2);
					}
				}
				if(creditAmount1==creditAmount2) {
					basetest.test.log(Status.PASS, "Credit amount(<span style='font-weight:bold;color:blue'>"+creditAmount1+"</span>) and invoice total amount("+creditAmount2+") are equal");
				}else {
					basetest.test.log(Status.PASS, "Credit amount(<span style='font-weight:bold;color:blue'>"+creditAmount1+"</span>) and invoice total amount("+creditAmount2+") are not equal");
				}
			}else {
				String text = driver.findElement(By.xpath("//table[starts-with(@id,'customsublist')]//td[text()='No records to show.']")).getText();
				basetest.test.log(Status.FAIL, "After entering the case adjustment ID :" + text);
			}
			//click on invoice number

			js.executeScript("window.scrollBy(0,-1500)");
			Thread.sleep(3000);

			gen.clickButton(driver, By.xpath(invoiceNoLink),"Click on invoice number",basetest);
			Thread.sleep(6000);
		}catch(Exception e) {
			System.out.println(e);
		}
	}


	public void clickInvoiceNumber(WebDriver driver,BaseTest basetest) throws InterruptedException{

		gen.clickButton(driver, By.xpath(invoiceCMS), "invoice CMS", basetest);
		Thread.sleep(10000);
	}


	public void approvalWorkFlowValidations(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest) throws InterruptedException{




		WebElement Element = driver.findElement(By.xpath(autoApproval));

		if(Element.getText().contains("Approved"))
		{
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  is Auto Approved"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  is Not Auto Approved"+"'</span>");
		}


		WebElement adjustmentamount = driver.findElement(By.xpath(adjustmentAmount));

		Thread.sleep(3000);

		if(adjustmentamount.getText().equals(XLTestData.get("linecreditamount").toString()))
		{
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Adjustment Amount is Displayed Cottectly"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Adjustment Amount is Displayed Incorrectly"+"'</span>");
		}

		String invoiceTotalamount = "//span[@id='custevent_inv_total_amt_fs_lbl']/following::span[1]";
		WebElement invoiceamount_in_page = driver.findElement(By.xpath(invoiceTotalamount));

		String invoiceamt = driver.findElement(By.xpath(invoiceAmount)).getAttribute("value");
		if(invoiceamount_in_page.getText().equals(invoiceamt))
		{
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice Total  Amount is Displayed Cottectly"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice Total  Amount is Displayed Incorrectly"+"'</span>");
		}

	}

}
