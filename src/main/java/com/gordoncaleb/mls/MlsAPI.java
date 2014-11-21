package com.gordoncaleb.mls;

import java.io.IOException;
import java.net.URISyntaxException;





import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gordoncaleb.util.WebUtil;

public class MlsAPI {

	private String user = "CalebW";
	private String pass = "9osiris9";

	public static void main(String[] args) throws URIException {

		//Connection response = Jsoup.connect("http://loginlax.rapmls.com/Menu.aspx?hidMLS=BRWC&SID=756b85ca-a3c0-4b60-9ccf-f383bcffb97f");
		
		//Document doc = response.get();
		
		
		
		//System.out.println(response.response().cookies());

		//System.out.println(doc.data());
		
		System.out.println(URIUtil.decode("http://searchlax.rapmls.com/LookUpWindow.aspx?hidMLS=WILM&SID=71656ad1-d4e4-40c6-a648-4452f8bc7e99&MLS=WILM&URL=http%3A%2F%2Fwrar.rapmls.com%2Fscripts%2Fmgrqispi.dll%3FAPPNAME%3Dwilmington%26PRGNAME%3DMLSReportPropertyHistory%26ARGUMENTS%3D-N143781744%2C-A%2C-AR%2C-AR%2C-N0%2C-A%2C-A%2C-A%2C-LFalse%2C-N0%2C-N%20%20%20%20%20%20%2031%2C-AWILM%2C-AMLS%2C-N%20%20%20%2099995%2C-N%20%20%20%2099995%2C-N%20%20%20%2099995%2C-N0%2C-N1%2C-AR02615002092000%2C-ANew%20Hanover%2C-N000455167%26MLS_Origin%3DWILM%26openDetailInNewWindow%3Dfalse&showNavigation=undefined"));
		
		
		System.out.println(URIUtil.decode("http://searchlax.rapmls.com/LookUpWindow.aspx?hidMLS=WILM&SID=6a3944af-6abb-4054-a439-1caca9ef99e0&MLS=WILM&URL=http%3A%2F%2Fwrar.rapmls.com%2Fscripts%2Fmgrqispi.dll%3FAPPNAME%3Dwilmington%26PRGNAME%3DMLSReportPropertyHistory%26ARGUMENTS%3D-N186032744%2C-A%2C-AR%2C-AR%2C-N0%2C-A%2C-A%2C-A%2C-LFalse%2C-N0%2C-N579505825%2C-AWILM%2C-AMLS%2C-N579500212%2C-N579500212%2C-N99995%2C-N0%2C-A1%2C-AR05520010005001%2C-ANew%20Hanover%2C-N463136%2C-A%26MLS_Origin%3DWILM%26openDetailInNewWindow%3Dfalse&showNavigation=undefined"));
	}

}
