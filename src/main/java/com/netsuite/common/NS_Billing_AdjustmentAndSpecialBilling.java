package com.netsuite.common;

import java.awt.AWTException;
import java.util.ArrayList;
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
import com.framework.ScreenShot;

import junit.framework.Assert;

public class NS_Billing_AdjustmentAndSpecialBilling  {


	String transaction 				= 	"//li[@data-title='Transactions']";
	String adminElement 			=	"spn_cRR_d1";
	String elementSB2 				= 	"//div[@id='ns-dashboard-heading-panel']/h1";

	//Searching salesorder And Admin Locators
	String SB2Admin 				= 	"//span[contains(text(),'SB1 Dev1 - iHeartMedia  -  Administrator')]";
	String SB4QAAdmin               =   "//span[contains(text(),'SB4 QA - iHeartMedia  -  Administrator')]";

	String SB1FinanceManager		=   "//span[contains(text(),'SB4 QA - iHeartMedia  -  Administrator')]";

	// Billing and Remove Agency Commission flow
	String removeAgencyCommision   =   "//div[text()='Remove Agency Commission']";
	String billing						=	"(//li[@data-title='Billing'])[1]";
	String adjustmentSpecialBilling 	= 	"//li[@data-title='Adjustment / Special Billing']";
	String adjustmentSpecialBillingPage = 	"//h4[text()='Adjustment']";
	String adjustment 					= 	"//h4[text()='Adjustment']";
	String selectAdjustmentType 		= 	"//input[@name='inpt_adjtype_display']";
	String adjustmentPage				=	"//h1[contains(text(),'Adjustments')]";
	String secondarynext                =  "//input[@id='secondarynext']";
	String addOrRemoveAgencyCommision 	= 	"(//input[@title='Add or Remove Agency Commission'])[1]";
	String adjustmentReasonGeneral 		= 	"//input[@name='inpt_adjcode12']";
	String agencyCommision 				= 	"//div[@class='dropdownDiv']/div[text()='Agency Commission']";
	String next    						= 	"//input[@name='next']";
	String agencyCommisNextBtn 			= 	"//input[@name='secondarynext']";
	String invoiceNumber 				= 	"//input[@id='invnum_text']";
	String invoiceNumberText 			=	"//input[@name='invnum_display']";
	String searchInvoice 				=	"//td[text()='Search Invoice']";
	String agencyCommisionOptions 		= 	"//input[@name='inpt_custpage_aggcomm_select']";
	String Comments  					= 	"//textarea[@id='custpage_memo']";
	String finish  						=	"//input[@value='Finish']";
	String caseNumber 					= 	"//table[@id='tbl__innerscroll']//a";
	String goToButton 					= 	"//button[contains(text(),'Go To')]";
	String invoiceCMS 					= 	"//span[@class='inputreadonly']/a[contains(text(),'Invoice')]";
	String adjustAmount = "//input[@id='custpage_creditamount_formattedValue']";
	String lineAdjustTable = "(//div[contains(text(),'Line Adjustment')]/following::tbody[2])/tr[contains(@id,'invoicelines_row')]";
	String downloadpdflink   = "//input[@id='custpage_showpdf']";
	String selectAdjArrow = "//img[@id='inpt_adjtype_display1_arrow']";

	String adjReasonGeneralDowArrow ="(//input[starts-with(@name,'inpt_adjcode')])[10]";
	String orderEntryError = "//div[text()='Order Entry Error']";

	//Split invoice
	String splitByStation = "//input[@id='custpage_split_by_station_fs_inp']/..";
	String splitBySpotAndMisc 	="//span[@id='custpage_split_by_line_type_fs']/..";
	String splitByProductType = "//input[@id='custpage_split_by_prd_type_fs_inp']/..";
	String splitByMarketType = "//span[@id='custpage_split_by_mkt_type_fs']/..";
	String splitByISCI = "//input[@id='custpage_split_by_isci_fs_inp']/..";

	String orderNumber = "(//span[@id='custevent_ihm_case_order_no_fs_lbl_uir_label']/following::span)[1]";
	String memoOrText = "(//span[@id='custevent_adj_req_text_fs_lbl_uir_label']/following::span)[1]";


	//Instance creation
	Generic oGenericUtils=new Generic();
	NS_Billing_CasePageValidations caseValidations = new NS_Billing_CasePageValidations();
	NS_Billing_InvoicePageValidations invoiceValidations = new NS_Billing_InvoicePageValidations();


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



	/*
		 @Descriptions 		---	Choosing Role As Admin
		 @param driver 		---	WebDriver parameter
		 @param XLTestData 	---	Test Data Parameter
		 @param basetest 	---	Report Parameter
	 */
	public String SelectRoleFOrNetSuiteAsAdmin(WebDriver driver, HashMap<String, String> XLTestData, BaseTest basetest)
			throws InterruptedException {
		String homepageurl = null;
		try {

			//Move to Admin Role
			oGenericUtils.navigateMouseToElement(driver, By.id(adminElement),"Admin Button in Netsuite Home page",basetest);

			//Click on Administartor 
			oGenericUtils.clickButton(driver, By.xpath("(//span[text()='"+XLTestData.get("UserRole")+"'])[1]"),"Adminstartor", basetest);



			//Checking for Home Page	
			if(driver.findElements(By.xpath(elementSB2)).size()>0) {

				homepageurl = driver.getCurrentUrl();
				basetest.test.log(Status.PASS,"User Role is successfully selected as "+XLTestData.get("UserRole").toString());
			}
			else 	
			{
				basetest.test.log(Status.FAIL,"User Role is Not selected as "+XLTestData.get("UserRole").toString()+"/May be NewPopup Occurs In Netsuite HomePage");
			}

			oGenericUtils.waiForPageLoad(3);
		} catch (Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
			basetest.test.log(Status.FAIL, "test failed");
		}
		return homepageurl;
	}



	/*##############################################################
	 @Descriptions 		---	Select Adjustment and Special Billing in Billing via Admin role
	 @param driver 		---	WebDriver parameter
	 @param XLTestData 	---	Test Data Parameter
	 @param basetest 	---	Report Parameter
	 ##############################################################*/
	public void selectAdjSplBillingInBilling(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) {

		try {	

			//webdriver waiting
			oGenericUtils.waiForPageLoad(5);

			//Move to Transactions
			oGenericUtils.navigateMouseToElement(driver, By.xpath(transaction),"Transactions",basetest);

			//Move to Billing
			oGenericUtils.navigateMouseToElement(driver, By.xpath(billing),"Billing",basetest);

			//Move to Adjustment / Special Billing
			oGenericUtils.navigateMouseToElement(driver, By.xpath(adjustmentSpecialBilling),"Adjustment / Special Billing",basetest);

			//click on Adjustment / Special Billing
			oGenericUtils.clickButton(driver, By.xpath(adjustmentSpecialBilling),"Adjustment / Special Billing",basetest);

			//Page validation
			if(driver.findElements(By.xpath(adjustment)).size()>0)
			{	
				basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'>'"+"Differnt Billing Request Page Loded Succesfully"+"'</span>");
			}
			else
			{
				basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'>'"+"Differnt Billing Request Page Loded Succesfully"+"'</span>");
			}

		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
		}	
	}	

	public void supressRate(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest) throws InterruptedException
	{
		//click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

		//wait untaile page load
		oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);

		oGenericUtils.waiForPageLoad(5);
		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);

		String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
		oGenericUtils.waiForPageLoad(3);	

		//	oGenericUtils.waiForPageLoad(6);
		//clicking on OK Alert
		oGenericUtils.isAlertPresent(driver);
		oGenericUtils.isAlertPresent(driver);

		oGenericUtils.waiForPageLoad(3);

		//Agencyselection
		/*oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
				String typeofagency = "//div[contains(text(),'"+XLTestData.get("Agencyselection")+"')]";
				oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);*/

		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(10);
		String invoicePage = "//span[@id='invnum_text_fs_lbl']/a[contains(text(),'Invoice Number')]";

		if(driver.findElements(By.xpath(invoicePage)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
		}

		oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

		//click on Search Invoice
		oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

		oGenericUtils.waiForPageLoad(18);

		WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

		String invoiceNumberText = invoiceElement.getAttribute("previousvalue");

		if(invoiceNumberText.contains("#"))

			Assert.assertTrue("Invoice Verified Successfully", true);
		else
			Assert.assertFalse("InvoiceNotVerified", false);


		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(10);

		String suppressRate = "//input[@id='custpage_suppress_spotrate_fs_inp']/following::img[1]";

		String suppressMiscRate = "//input[@id='custpage_suppress_miscrate_fs_inp']/following::img[1]";

		//click on Suppress Spot Rates
		oGenericUtils.clickButton(driver, By.xpath(suppressRate),"Click on suppressrate",basetest);

		//click onSuppress Misc Rates

		oGenericUtils.clickButton(driver, By.xpath(suppressMiscRate),"Click on suppressMiscRate",basetest);

		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(10);


		//Enter Comments

		oGenericUtils.setText(driver, Comments, "QA");

		//click on finish
		oGenericUtils.clickButton(driver, By.xpath(finish),"Click on finish",basetest);

		oGenericUtils.waiForPageLoad(10);

		//click on Case number

		String caseNumberText =  oGenericUtils.getTextOfElement(driver, caseNumber);
		if(caseNumberText.length()>0){
			basetest.test.log(Status.INFO, "Case Number :"+caseNumberText);
		}
		oGenericUtils.waiForPageLoad(6);
		oGenericUtils.clickButton(driver, By.xpath(goToButton), "Go To Case", basetest);

		oGenericUtils.waiForPageLoad(6);


		

	}


	/**
	 * @Descriptions 		---	Select Adjustment and Special Billing in Billing via Admin role
	 * @param driver 		---	WebDriver parameter
	 * @param XLTestData 	---	Test Data Parameter
	 * @param basetest 	---	Report Parameter
	 */


	public String clickAdjustments(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) 
	{
		String invoiceNumberTextValue="";
		float grossAmount =0;
		float netDue =0;
		String adjmentAmtValue =  "";
		String grossValueFormated="";
		String netValueFormated="";

		try {
			float totalRevsiedAmt;
			//click on Adjustment
			oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

			//wait untaile page load
			oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);

			oGenericUtils.waiForPageLoad(5);
			//click on select Adjustment Type
			oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);

			//click on Add or remove agency commision
			oGenericUtils.waiForPageLoad(5);
			String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);

			//tabing 
			oGenericUtils.waiForPageLoad(6);
			//clicking on OK Alert
			oGenericUtils.isAlertPresent(driver);

			//Agencyselection
			oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
			String typeofagency = "//div[contains(text(),'"+XLTestData.get("Agencyselection")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);

			//click on next
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

			oGenericUtils.waiForPageLoad(10);
			String invoicePage = "//span[@id='invnum_text_fs_lbl']/a[contains(text(),'Invoice Number')]";

			if(driver.findElements(By.xpath(invoicePage)).size()>0){
				basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
			}else {
				basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
			}


			oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

			//click on Search Invoice
			oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

			oGenericUtils.waiForPageLoad(18);

			WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

			invoiceNumberTextValue = invoiceElement.getAttribute("previousvalue");

			if(invoiceNumberTextValue.contains("#"))

				Assert.assertTrue("Invoice Verified Successfully", true);
			else
				Assert.assertFalse("InvoiceNotVerified", false);


			//click on next
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);


			oGenericUtils.waiForPageLoad(9);
			if(driver.findElements(By.xpath(agencyCommisionOptions)).size()>0){
				basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
			}else {
				basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
			}
			List<WebElement> lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

			int size =lineAdjustTableRowCount.size();
			ArrayList<Float> beforeRevisedNetAmt =  new ArrayList<Float>();
			ArrayList<Float> aftereRevisedNetAmt =new ArrayList<Float>();
			for(int i=1; i<=size ; i++){
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("REVISED NET AMOUNT")){
						String text = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");						
						beforeRevisedNetAmt.add(Float.parseFloat(text));
						break;
					}
				}

			}

			//click on Adjustment Reason General   agencyCommision
			oGenericUtils.clickButton(driver, By.xpath(agencyCommisionOptions),"Click on Agency Commision",basetest);

			//click on Remove agency commision
			oGenericUtils.clickButton(driver, By.xpath(removeAgencyCommision),"Click on Remove Agency Commision",basetest);

			oGenericUtils.waiForPageLoad(18);
			lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

			size =lineAdjustTableRowCount.size();
			String revisedcommisionRate="";
			for(int i=1; i<=size ; i++){
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("REVISED AG COMM AMOUNT")){
						revisedcommisionRate = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");						
						break;
					}
				}
				if(revisedcommisionRate.contains("0.0")){
					basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'>Revised commision rate is zero"+"'</span>");

				}else{
					basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'>Revised commision rate not changed"+"'</span>");

				}	
			}


			WebElement adjAmtElement = driver.findElement(By.xpath(adjustAmount));
			adjmentAmtValue = adjAmtElement.getAttribute("value").trim();
			if(adjmentAmtValue.contains("-")){
				adjmentAmtValue = adjmentAmtValue.replace("-", "");
			}else if(adjmentAmtValue.contains("+")){
				adjmentAmtValue = adjmentAmtValue.replace("+", "");
			}
			float adjmentAmtValueInDecimal = Float.parseFloat(adjmentAmtValue);

			ArrayList<Float> netCreditAmtDecimal = new ArrayList<Float>();
			float totalNetCreditAmount=0;
			for(int i=1; i<=size ; i++){
				String netCreditAmtText ="";
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("NET CREDIT AMOUNT")){
						netCreditAmtText = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]"); 
						break;
					}
				}				
				if(netCreditAmtText.contains("-")){
					netCreditAmtText = netCreditAmtText.replace("-", "");
				}else if(netCreditAmtText.contains("+")){
					netCreditAmtText = netCreditAmtText.replace("+", "");
				}
				float netCreditAmount=Float.parseFloat(netCreditAmtText);
				netCreditAmtDecimal.add(netCreditAmount);
				totalNetCreditAmount=totalNetCreditAmount+netCreditAmount;
			}

			if(adjmentAmtValueInDecimal==totalNetCreditAmount){
				basetest.test.log(Status.PASS, "Adjustment Amount("+adjmentAmtValueInDecimal+")= Total Net Credit Amount("+totalNetCreditAmount+") are equal");
			}else{
				basetest.test.log(Status.FAIL, "Adjustment Amount("+adjmentAmtValueInDecimal+")= Total Net Credit Amount("+totalNetCreditAmount+") are equal not equal");
			}

			lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

			size =lineAdjustTableRowCount.size();


			for(int i=1; i<=size ; i++){
				List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
				int headSize = tableHead.size();
				for(int j=1;j<=headSize;j++){
					String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
					if(headText.contains("REVISED NET AMOUNT")){
						String text = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");
						aftereRevisedNetAmt.add(Float.parseFloat(text));
						break;
					}
				}
				totalRevsiedAmt	=netCreditAmtDecimal.get(i-1)+beforeRevisedNetAmt.get(i-1);
				if(aftereRevisedNetAmt.get((i-1))==(totalRevsiedAmt)){
					basetest.test.log(Status.PASS, "After Revised Net Amount : "+aftereRevisedNetAmt.get((i-1))+" Before Revised Net Amount("+beforeRevisedNetAmt.get(i-1)+")+ Net Credit Amount("+netCreditAmtDecimal.get(i-1)+") are equal");
				}else{
					basetest.test.log(Status.FAIL, "After Revised Net Amount : "+aftereRevisedNetAmt.get((i-1))+" Before Revised Net Amount("+beforeRevisedNetAmt.get(i-1)+")+ Net Credit Amount("+netCreditAmtDecimal.get(i-1)+") are not equal");
				}

			}


			//click on next
			oGenericUtils.clickButton(driver, By.xpath(agencyCommisNextBtn),"Click on next",basetest);


			oGenericUtils.waiForPageLoad(6);
			//Enter Comments

			oGenericUtils.setText(driver, Comments, "QA");

			//click on finish
			oGenericUtils.clickButton(driver, By.xpath(finish),"Click on finish",basetest);


			//click on Casenumber

			String caseNumberText =  oGenericUtils.getTextOfElement(driver, caseNumber);
			if(caseNumberText.length()>0){
				basetest.test.log(Status.INFO, "Case Number :"+caseNumberText);
			}
			oGenericUtils.waiForPageLoad(3);
			oGenericUtils.clickButton(driver, By.xpath(goToButton), "Go To Case", basetest);



			oGenericUtils.waiForPageLoad(8);
			//page scroll
			/*JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,600)");
			 */
			oGenericUtils.clickButton(driver, By.xpath(invoiceCMS), "invoice CMS", basetest);
			oGenericUtils.waiForPageLoad(8);


			for(float tempGrossAmt: aftereRevisedNetAmt){
				grossAmount = grossAmount+tempGrossAmt;
			}

			for(float tempNetDue :beforeRevisedNetAmt){
				netDue = netDue+tempNetDue;
			}

			grossValueFormated = String.valueOf(grossAmount);
			grossValueFormated = "$"+grossValueFormated.substring(0,1)+","+grossValueFormated.substring(1,grossValueFormated.length());

			netValueFormated = String.valueOf(netDue);
			netValueFormated = "$"+netValueFormated.substring(0,1)+","+netValueFormated.substring(1,netValueFormated.length());


			//Return [1]Gross Amount[2]Net Due[3]invoicenumber

		}catch(Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
		}
		return grossValueFormated+";"+adjmentAmtValue+";"+netValueFormated;	
	}


	public void clickPDFDownloadLink(WebDriver driver,BaseTest basetest) throws InterruptedException, AWTException{

		oGenericUtils.waiForPageLoad(15);
		//enableAutomaticPDFDownload(driver);

		//click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(downloadpdflink),"Click on Adjustment",basetest);

		oGenericUtils.waiForPageLoad(30);
	}


	public void ChangeinvoiceAddress(WebDriver driver,HashMap<String, String> XLTestData,BaseTest basetest) throws InterruptedException {

		//click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

		//wait untaile page load
		oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);

		oGenericUtils.waiForPageLoad(5);
		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath("//img[@id='inpt_adjtype_display1_arrow']"),"Click on Select Adjustment Type",basetest);

		//click on Adrress/Est/NoteChange
		oGenericUtils.waiForPageLoad(5);
		String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);

		//tabing 
		oGenericUtils.waiForPageLoad(6);
		//clicking on OK Alert
		oGenericUtils.isAlertPresent(driver);

		//Agencyselection
		oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
		String typeofagency = "//div[contains(text(),'"+XLTestData.get("Agencyselection")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);

		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(10);
		String invoicePage = "//span[@id='invnum_text_fs_lbl']/a[contains(text(),'Invoice Number')]";

		if(driver.findElements(By.xpath(invoicePage)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
		}

		oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

		//click on Search Invoice
		oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

		oGenericUtils.waiForPageLoad(18);

		WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

		String invoiceNumberText = invoiceElement.getAttribute("previousvalue");

		if(invoiceNumberText.contains("#"))

			Assert.assertTrue("Invoice Verified Successfully", true);
		else
			Assert.assertFalse("InvoiceNotVerified", false);


		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);


		oGenericUtils.waiForPageLoad(9);

		/*if(driver.findElements(By.xpath(agencyCommisionOptions)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}*/


		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath("//img[@id='inpt_custpage_billto1_arrow']"),"Click on Select Adjustment Type",basetest);

		String typeofBilltoSelect = "//div[contains(text(),'"+XLTestData.get("Bill TO Select")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofBilltoSelect),"Type of Invoice Adjustment Slection",basetest);

		String revisedESTIOCPE ="//textarea[@id='custpage_cpe']";
		oGenericUtils.SetVal(driver, By.xpath(revisedESTIOCPE), XLTestData.get("RevisedEST/IO/CPE").toString(),"RevisedEST/CPE",basetest);

		String newNote1 = "//textarea[@id='custpage_note1']";
		oGenericUtils.SetVal(driver, By.xpath(newNote1), XLTestData.get("NewNote1").toString(),"NewNote1",basetest);

		String newNote2 = "//textarea[@id='custpage_note2']";
		oGenericUtils.SetVal(driver, By.xpath(newNote2), XLTestData.get("NewNote2").toString(),"NewNote2",basetest);		
	}
	/**
	 * 
	 * @param driver
	 * @param basetest
	 */
	public void clickOnAdjustment(WebDriver driver,BaseTest basetest) {
		try {
			oGenericUtils.clickButton(driver, By.xpath(adjustment), "Click on Adjustment", basetest);
		} catch (Exception e) {
			System.out.println(e);
		}
	}



	public void verifyInvalidInvoiceMessage(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest){
		try{
			oGenericUtils.waiForPageLoad(3);
			clickOnAdjustment(driver, basetest);
			oGenericUtils.waiForPageLoad(11);
			oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);		
			String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
			oGenericUtils.waiForPageLoad(5);
			//click on select Adjustment Type
			oGenericUtils.clickButton(driver, By.xpath(adjReasonGeneralDowArrow),"Click Adjustment Reason General",basetest);
			oGenericUtils.waiForPageLoad(2);
			oGenericUtils.clickButton(driver, By.xpath(orderEntryError),"Click on Select Adjustment Type",basetest);
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			oGenericUtils.waiForPageLoad(6);
			oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);
			//click on Search Invoice
			oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);						
			oGenericUtils.waiForPageLoad(20500);
			//clicking on OK Alert
			oGenericUtils.getAlertText(driver, "No Match",basetest);		

		}catch(Exception e){
			System.out.println(e);
		}
	}




	public void splitInvoicePartiallyPaid(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest){
		try{
			oGenericUtils.waiForPageLoad(3);
			clickOnAdjustment(driver, basetest);
			oGenericUtils.waiForPageLoad(11);
			oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);		
			String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
			oGenericUtils.waiForPageLoad(5);

			//click on select Adjustment Type
			oGenericUtils.clickButton(driver, By.xpath(adjReasonGeneralDowArrow),"Click on Select Adjustment Type",basetest);
			oGenericUtils.waiForPageLoad(2);
			oGenericUtils.clickButton(driver, By.xpath(orderEntryError),"Click on Select Adjustment Type",basetest);
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			oGenericUtils.waiForPageLoad(6);
			oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);
			//click on Search Invoice
			oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);						
			oGenericUtils.waiForPageLoad(20500);

			//clicking on OK Alert
			oGenericUtils.getAlertText(driver, "You are not allowed to request adjustment for this Invoice. Please use the",basetest);	


		}catch(Exception e){
			System.out.println(e);
		}
	}



	/**
	 * 
	 * @param driver - WebDriver obj
	 * @param scenario - split Invoice
	 * @param invoiceNO - pass invoice
	 * @param splitingType - type of spliting
	 * @param basetest - report obj
	 */
	public void splitInvoiceByType(WebDriver driver,String scenario,String invoiceNO,String splitingType,BaseTest basetest) {
		String caseNumberText="";
		String selectSplitTypeCheckBox="";
		if (splitingType.contains("ISCI")) {
			selectSplitTypeCheckBox = splitByISCI;

		} else if (splitingType.contains("MarketType")) {
			selectSplitTypeCheckBox = splitByMarketType;			

		} else if (splitingType.contains("ProductType")) {
			selectSplitTypeCheckBox = splitByProductType;			

		} else if (splitingType.contains("SplitStation")) {
			selectSplitTypeCheckBox = splitByStation;			

		} else if (splitingType.contains("SpotAndMisc")) {
			selectSplitTypeCheckBox = splitBySpotAndMisc;

		}

		try {
			oGenericUtils.waiForPageLoad(3);
			clickOnAdjustment(driver, basetest);
			oGenericUtils.waiForPageLoad(15);
			oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);		
			String typeofAdjustment = "//div[contains(text(),'"+scenario+"')]";
			oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
			oGenericUtils.waiForPageLoad(5);

			//click on select Adjustment Type
			oGenericUtils.clickButton(driver, By.xpath(adjReasonGeneralDowArrow),"Click on Select Adjustment Type",basetest);
			oGenericUtils.waiForPageLoad(2);
			oGenericUtils.clickButton(driver, By.xpath(orderEntryError),"Click on Select Adjustment Type",basetest);
			oGenericUtils.waiForPageLoad(4);
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			oGenericUtils.waiForPageLoad(9);
			oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), invoiceNO,"Invoice Text Box",basetest);
			//click on Search Invoice
			//oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);
			driver.findElement(By.xpath(invoiceNumber)).sendKeys(Keys.TAB);
			oGenericUtils.waiForPageLoad(29);

			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			oGenericUtils.waiForPageLoad(11);

			//Select split by station checkbox 
			oGenericUtils.clickButton(driver, By.xpath(selectSplitTypeCheckBox),"Click on Split By "+splitingType,basetest);
			//click next
			oGenericUtils.waiForPageLoad(3);
			oGenericUtils.clickButton(driver, By.xpath(next),"Click on next button",basetest);
			//Click on alert
			oGenericUtils.isAlertPresent(driver);

			oGenericUtils.waiForPageLoad(16);
			oGenericUtils.setText(driver, Comments, "QA");
			oGenericUtils.clickButton(driver, By.xpath(finish),"Click on finish",basetest);
			oGenericUtils.waiForPageLoad(13);
			caseNumberText =  oGenericUtils.getTextOfElement(driver, caseNumber).trim();
			if(caseNumberText.length()>0){
				basetest.test.log(Status.INFO, "Case Number :<span style='font-weight:bold;color:blue'>"+caseNumberText+"</span> is genersted.");
			}
			oGenericUtils.waiForPageLoad(7);
			oGenericUtils.clickButton(driver, By.xpath(goToButton), "Go To Case", basetest);
			oGenericUtils.waiForPageLoad(12);
			//Details under Case page
			//Added Status statement
			//Validation statements						
			caseValidations.casePageValidations(driver, splitingType, invoiceNO, caseNumberText, basetest);			
			invoiceValidations.invoiceValidations(driver, basetest);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void approvalWorkFlow(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest) throws InterruptedException
	{
		//click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

		//wait until page load
		oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);

		oGenericUtils.waiForPageLoad(7);
		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);

		String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
		oGenericUtils.waiForPageLoad(5);


		oGenericUtils.clickButton(driver, By.xpath(preference),"Type of Preference",basetest);
		String typeofPreference = "//div[contains(text(),'"+XLTestData.get("preference")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofPreference),"Click on Select Preference Type",basetest);
		oGenericUtils.waiForPageLoad(6);
		//clicking on OK Alert
		oGenericUtils.isAlertPresent(driver);

		oGenericUtils.waiForPageLoad(5);

		//Agency selection
		oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
		String typeofagency = "//div[contains(text(),'"+XLTestData.get("AdjustmentReasonGeneral")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);

		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(9);

		oGenericUtils.waiForPageLoad(10);


		if(driver.findElements(By.xpath(invoicePage)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
		}

		oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

		//click on Search Invoice
		oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

		oGenericUtils.waiForPageLoad(50);
		WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

		String invoiceNumberText = invoiceElement.getAttribute("previousvalue");

		if(invoiceNumberText.contains("#"))

			Assert.assertTrue("Invoice Verified Successfully", true);
		else
			Assert.assertFalse("InvoiceNotVerified", false);


		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(25);


		//click on full credit
		oGenericUtils.clickButton(driver, By.xpath(fullCreditcheck),"Click on Full Credit Button",basetest);



		String invoiceamt = driver.findElement(By.xpath(invoiceAmount)).getAttribute("value");

		List<WebElement> lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));
		lineAdjustTableRowCount=driver.findElements(By.xpath(lineAdjustTable));

		int  size =lineAdjustTableRowCount.size();
		boolean flag=false;
		for(int i=1; i<=size ; i++){
			List<WebElement> tableHead = driver.findElements(By.xpath("//table[@id='invoicelines_splits']/tbody/tr/td/div"));
			int headSize = tableHead.size();
			for(int j=1;j<=headSize;j++){
				String headText = driver.findElement(By.xpath("(//table[@id='invoicelines_splits']/tbody/tr/td/div)["+j+"]")).getText().trim();
				if(headText.contains("NET CREDIT AMOUNT")){
					//String text = oGenericUtils.getTextOfElement(driver, "//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]");						
					//click on Ok button
					oGenericUtils.clickButton(driver, By.xpath("//table[@id='invoicelines_splits']/tbody/tr[contains(@id,'invoicelines_row_"+i+"')]/td["+j+"]"),"Click on NET CREDIT AMOUNT",basetest);
					oGenericUtils.waiForPageLoad(2);

					oGenericUtils.SetVal(driver, By.xpath(linecreditamount), XLTestData.get("linecreditamount").toString(),"Change Line Credit Amount",basetest);
					oGenericUtils.waiForPageLoad(2);
					oGenericUtils.clickButton(driver, By.xpath(adjustmentPage),"Click on Adjustments",basetest);
					flag = true;
					break;
				}
			}
			if(flag==true)
				break;

		}

		//String linecreditamount = "//input[@id='linecreditamount_formattedValue']";


		//oGenericUtils.SetVal(driver, By.xpath(linecreditamount), XLTestData.get("linecreditamount").toString(),"Change Line Credit Amount",basetest);
		oGenericUtils.waiForPageLoad(2);

		//click on Ok button
		oGenericUtils.clickButton(driver, By.xpath(ok_btn),"Click on Ok Button",basetest);

		oGenericUtils.waiForPageLoad(2);
		oGenericUtils.clickButton(driver, By.xpath(adjustmentPage),"Click on Adjustments",basetest);
		oGenericUtils.waiForPageLoad(2);
		//click on next
		oGenericUtils.clickButton(driver, By.xpath(secondarynext),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(10);


		//Enter Comments

		oGenericUtils.setText(driver, Comments, "QA");

		//click on finish
		oGenericUtils.clickButton(driver, By.xpath(finish),"Click on finish",basetest);

		oGenericUtils.waiForPageLoad(12);

		String caseNumberText =  oGenericUtils.getTextOfElement(driver, caseNumber);
		if(caseNumberText.length()>0){
			basetest.test.log(Status.INFO, "Case Number :"+caseNumberText);
		}
		oGenericUtils.waiForPageLoad(6);
		oGenericUtils.clickButton(driver, By.xpath(goToButton), "Go To Case", basetest);

		oGenericUtils.waiForPageLoad(8);

	}

	/*##############################################################
	@Descriptions 		---	Changing the Role
	@param driver 		---	WebDriver parameter
	@param XLTestData 	---	Test Data Parameter
	@param basetest 	---	Report Parameter
	@param role        --- Role Parameter
	##############################################################*/


	public String changeRole_to_Different_Administrators(WebDriver driver, HashMap<String, String> XLTestData, BaseTest basetest, String role)
	{
		String homepageurl = null;
		try {

			//Move to Admin Role
			oGenericUtils.navigateMouseToElement(driver, By.id(adminElement),"Admin Button in Netsuite Home page",basetest);

			//Click on Administartor 
			oGenericUtils.clickButton(driver, By.xpath(role),"Adminstartor", basetest);

			if(driver.getTitle().contains("Administrator Message - NetSuite"))
			{
				basetest.test.log(Status.INFO,"Administrator Message - NetSuite/Netsuite Under Maintance");
				driver.findElement(By.xpath("//*[@id='agree_fs_fs']/img")).click();
				oGenericUtils.waiForPageLoad(1);
				driver.findElement(By.xpath("//*[@id='submitter']")).click();
			}

			//Checking for Home Page	
			if(driver.findElements(By.xpath(elementSB2)).size()>0) {

				homepageurl = driver.getCurrentUrl();
				basetest.test.log(Status.PASS,"User Role is successfully selected as "+XLTestData.get("UserRole").toString());
			}
			else 	
			{
				basetest.test.log(Status.FAIL,"User Role is Not selected as "+XLTestData.get("UserRole").toString()+"/May be NewPopup Occurs In Netsuite HomePage");
			}


		} catch (Exception e) {
			oGenericUtils.Verify("Object not found:="+e.getMessage(),"FAILED",basetest);
			basetest.test.log(Status.FAIL, "test failed");
		}
		return homepageurl;
	}


	public void approvalWorkFlowover10k(WebDriver driver,HashMap<String,String> XLTestData,BaseTest basetest) throws InterruptedException
	{
		//click on Adjustment
		oGenericUtils.clickButton(driver, By.xpath(adjustment),"Click on Adjustment",basetest);

		//wait untaile page load
		oGenericUtils.WaitUntilElement(driver, By.xpath(adjustmentPage), "Adjustment Page", basetest);

		oGenericUtils.waiForPageLoad(7);
		//click on select Adjustment Type
		oGenericUtils.clickButton(driver, By.xpath(selectAdjArrow),"Click on Select Adjustment Type",basetest);

		String typeofAdjustment = "//div[contains(text(),'"+XLTestData.get("Scenario")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofAdjustment),"Type of Invoice Adjustment Slection",basetest);
		oGenericUtils.waiForPageLoad(5);


		oGenericUtils.clickButton(driver, By.xpath(preference),"Type of Preference",basetest);
		String typeofPreference = "//div[contains(text(),'"+XLTestData.get("preference")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofPreference),"Click on Select Preference Type",basetest);
		oGenericUtils.waiForPageLoad(6);
		//clicking on OK Alert
		oGenericUtils.isAlertPresent(driver);

		oGenericUtils.waiForPageLoad(5);

		//Agency Selection
		oGenericUtils.clickButton(driver, By.xpath(adjustmentReasonGeneral),"Click on Adjustment Reason General",basetest);
		String typeofagency = "//div[contains(text(),'"+XLTestData.get("AdjustmentReasonGeneral")+"')]";
		oGenericUtils.clickButton(driver, By.xpath(typeofagency),"Adjustment Reason General",basetest);

		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(9);

		oGenericUtils.waiForPageLoad(10);


		if(driver.findElements(By.xpath(invoicePage)).size()>0){
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page verifeid successfully"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  Page Not verifeid"+"'</span>");
		}

		oGenericUtils.SetVal(driver, By.xpath(invoiceNumber), XLTestData.get("invoiceNumber").toString(),"Invoice Text Box",basetest);

		//click on Search Invoice
		oGenericUtils.clickButton(driver, By.xpath(searchInvoice),"Click on Invoice Search",basetest);

		oGenericUtils.waiForPageLoad(50);
		WebElement invoiceElement = driver.findElement(By.xpath(invoiceNumberText));

		String invoiceNumberText = invoiceElement.getAttribute("previousvalue");

		if(invoiceNumberText.contains("#"))

			Assert.assertTrue("Invoice Verified Successfully", true);
		else
			Assert.assertFalse("InvoiceNotVerified", false);


		//click on next
		oGenericUtils.clickButton(driver, By.xpath(next),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(25);


		//click on full credit
		oGenericUtils.clickButton(driver, By.xpath(fullCreditcheck),"Click on Full Credit Button",basetest);

		oGenericUtils.waiForPageLoad(5);
		//click on next
		oGenericUtils.clickButton(driver, By.xpath(secondarynext),"Click on next",basetest);

		oGenericUtils.waiForPageLoad(10);


		//Enter Comments

		oGenericUtils.setText(driver, Comments, "QA");

		oGenericUtils.waiForPageLoad(12);

		String caseNumberText =  oGenericUtils.getTextOfElement(driver, caseNumber);
		if(caseNumberText.length()>0){
			basetest.test.log(Status.INFO, "Case Number :"+caseNumberText);
		}
		oGenericUtils.waiForPageLoad(6);
		oGenericUtils.clickButton(driver, By.xpath(goToButton), "Go To Case", basetest);

		oGenericUtils.waiForPageLoad(8);

		WebElement Element = driver.findElement(By.xpath(autoApproval));

		if(Element.getText().contains("Pending Approval From Finance"))
		{
			basetest.test.log(Status.PASS,"<span style='font-weight:bold;color:blue'> '"+"Invoice  is Partially Approved"+"'</span>");
		}else {
			basetest.test.log(Status.FAIL,"<span style='font-weight:bold;color:blue'> '"+"Invoice  is Auto Approved"+"'</span>");
		}

		//choosing role to iHM Market Finance Manager-SSO
		changeRole_to_Different_Administrators(driver, XLTestData, basetest,SB1FinanceManager);



	}


}
