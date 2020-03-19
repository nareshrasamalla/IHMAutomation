package com.scripts.netSuite.suppressRates;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.BaseReport;
import com.framework.BaseTest;
import com.framework.Excel_Reader;
import com.framework.Generic;
import com.netsuite.common.NS_Billing_AdjustmentAndSpecialBilling;
import com.netsuite.common.NS_Billing_CasePageValidations;
import com.netsuite.common.NS_Billing_InvoicePageValidations;
import com.netsuite.common.NS_LoginPage;

public class TS_NS_6696_SuppressRate extends BaseReport{
	private BaseTest basetest;
	public static Excel_Reader excelReader;
	public static int i=2224;
	int HistoryRowNumber=0;
	int passCount=0, FailCount=0;
	public static String TestDataPath="";
	public static HashMap<String,String> XLTestData;
	public static WebDriver driver;

	//========================>
	NS_LoginPage oLoginPage=new NS_LoginPage();
	Generic oGenericUtils=new Generic();
	NS_Billing_AdjustmentAndSpecialBilling oBillingNetsuite = new NS_Billing_AdjustmentAndSpecialBilling();
	NS_Billing_CasePageValidations oCaseNetsuite = new NS_Billing_CasePageValidations();
	NS_Billing_InvoicePageValidations oInvoiceNetsuite = new NS_Billing_InvoicePageValidations();
	Generic gen =new Generic();
	String filePathToDownload =null;

	//========================>
	@BeforeTest(alwaysRun=true)
	public void getTest() throws IOException {
		basetest=new BaseTest();
		basetest.getTest(this.getClass().getSimpleName(),"Remove Agency Commission");
		/*Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("taskkill /im chromedriver.exe /f /t");*/

	}
	//==============>
	@BeforeClass
	public void test() throws FileNotFoundException, IOException {
		TestDataPath = System.getProperty("user.dir") + "\\Data\\iHeart_NetSuiteTestData_RemoveAgencyCommission.xlsx";
		System.out.println("Test Data Path: "+TestDataPath);
		excelReader=new Excel_Reader(TestDataPath);
		excelReader.cFileNameWithPath = TestDataPath;
		excelReader.cSheetName = "TestData";
		excelReader.cTcID = "TestCaseID";
		excelReader.cTcValue = "1";
		XLTestData = new HashMap<String, String>();
		XLTestData = excelReader.readExcel("TC_NST_" + Integer.toString(i));



		//Folder creation
		File folder = new File(System.getProperty("user.dir") + "\\RemoveAgencyCommissionPDFFile");
		if(!folder.exists()){
			folder.mkdir();
		}
		for(File file :folder.listFiles()){
			String fileNameDeleted = "";
			fileNameDeleted = file.getName();
			if(file.delete()){
				System.out.println("File : "+fileNameDeleted+" deleted sucessfully");
			}
		}
		filePathToDownload = folder.getAbsolutePath();
	}

	//=============>

	@Test
	public void suppressRate() throws InterruptedException, IOException, AWTException {
		System.out.println(XLTestData.get("NetSuite_URL").toString());	
		System.out.println(XLTestData.get("NetEmail").toString());
		System.out.println(XLTestData.get("NetPassword").toString());
		basetest.test = basetest.extent.createTest(XLTestData.get("TestCaseID")+":"+XLTestData.get("TestFlow").toString(), XLTestData.get("TestCaseID")+":"+XLTestData.get("TestFlow").toString());
		 basetest.test.info("<span style='font-weight:bold;color:blue'>'"+ XLTestData.get("TestCaseID")+":"+XLTestData.get("TestFlow").toString()+ " Execution started"+"'</span>");
		//Launch URL
		//driver=oLoginPage.LaunchNetSuiteApp(XLTestData.get("NetSuite_URL").toString(),XLTestData,basetest);
		driver = oLoginPage.LaunchNetSuiteApp(XLTestData.get("NetSuite_URL").toString(), XLTestData, filePathToDownload, basetest);
		//Login Application
		oLoginPage.NetSuiteLogin(driver, XLTestData,basetest);

		//choosing role
		oBillingNetsuite.SelectRoleFOrNetSuiteAsAdmin(driver, XLTestData, basetest);

		//selecting new sales order through list
		oBillingNetsuite.selectAdjSplBillingInBilling(driver, XLTestData, basetest);

		//SuppressRate Validation
		oBillingNetsuite.supressRate(driver, XLTestData,basetest);
		oCaseNetsuite.clickInvoiceNumber(driver,basetest,XLTestData);
		oInvoiceNetsuite.suppressRateValidations(driver, basetest);

		//logout from NetSuite
		oLoginPage.NetSuiteLogout(driver, basetest);
		 basetest.test.info("<span style='font-weight:bold;color:blue'>'"+ XLTestData.get("TestCaseID")+":"+XLTestData.get("TestFlow").toString()+ " Execution completed"+"'</span>");
	}

	@AfterMethod(alwaysRun = true)
	public void ExtentReport() {
		basetest.extent.flush();
		if(driver != null)
		{driver.close();
		// driver.quit();
		}



	}
	
	@AfterClass(alwaysRun = true)
	public void LogsOut() throws InterruptedException, IOException {
		String ClassName = this.getClass().getSimpleName();
		LogScenario(ClassName, passCount, FailCount);
		if(driver != null) {
			{
				if(gen.isAlertPresents(driver))
				{
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}
				driver.close();
				driver.quit();
			}

		}
	}
}
