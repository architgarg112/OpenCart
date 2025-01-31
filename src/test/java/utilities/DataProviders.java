package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

		//DataProvider 1
	
		@DataProvider(name="LoginData")
		String[][] getdata() throws IOException
		{
			String path =".\\testData\\Opencart_Login_Data.xlsx"; //taking xl file from testData (./ will take current path)
			
			ExcelUtility util=new ExcelUtility(path); //creating obj of the xlutility
			
			int totalrows = util.getRowCount("Sheet1");
			int totalcol = util.getcellCount("Sheet1", 1);
			
			String logindata[][]=new String[totalrows][totalcol]; //created 2d array for storing data
			
			for(int i=1;i<=totalrows;i++)  //1   
			{
				for(int j=0;j<totalcol;j++)  //0 i is rows j is col
				{
					logindata[i-1][j]=util.getCellData("Sheet1", i, j); //1,0
				}
			}
			
		return logindata; //return 2d array
			
		}

	
	
	
		//DataProvider 2

		//DataProvider 3
		
}