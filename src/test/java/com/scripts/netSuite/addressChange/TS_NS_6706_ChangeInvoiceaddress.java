package com.scripts.netSuite.addressChange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.BaseReport;
import com.framework.BaseTest;
import com.framework.Excel_Reader;
import com.framework.Generic;
import com.netsuite.common.NS_Billing_AdjustmentAndSpecialBilling;
import com.netsuite.common.NS_LoginPage;

public class TS_NS_6706_ChangeInvoiceaddress extends BaseReport {
	
	
	private BaseTest basetest;
	public static Excel_Reader excelReader;
	public static int i=2222;
	int HistoryRowNumber=0;
	int passCount=0, FailCount=0;
	public static String TestDataPath="";
	public static HashMap<String,String> XLTestData;
	public static WebDriver driver;
	
	//========================>
		NS_LoginPage oLoginPage=new NS_LoginPage();
		Generic oGenericUtils=new Generic();
		NS_Billing_AdjustmentAndSpecialBilling oSalesOrderNetsuite = new NS_Billing_AdjustmentAndSpecialBilling();
		Generic gen =new Generic();

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
		}

		@Test
		public void changeAddress() throws InterruptedException

		{
			System.out.println(XLTestData.get("NetSuite_URL").toString());	
			System.out.println(XLTestData.get("NetEmail").toString());
			System.out.println(XLTestData.get("NetPassword").toString());
			basetest.test = basetest.extent.createTest("CA_"+XLTestData.get("Scenario").toString(),"CA_"+XLTestData.get("Scenario").toString());
			//Launch URL
			driver=oLoginPage.LaunchNetSuiteApp(XLTestData.get("NetSuite_URL").toString(),XLTestData,"",basetest);

			//Login Application to NetSuite
			oLoginPage.NetSuiteLogin(driver, XLTestData,basetest);

			//choosing role
			oSalesOrderNetsuite.SelectRoleFOrNetSuiteAsAdmin(driver, XLTestData, basetest);

			//selecting new sales order through list
			oSalesOrderNetsuite.selectAdjSplBillingInBilling(driver, XLTestData, basetest);
			
			
			//adjustments
			oSalesOrderNetsuite.ChangeinvoiceAddress(driver, XLTestData, basetest);
			
			//logout from Net Suite
			oLoginPage.NetSuiteLogout(driver, basetest);

		}

}
