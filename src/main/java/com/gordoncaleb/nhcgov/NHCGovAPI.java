package com.gordoncaleb.nhcgov;

import java.io.IOException;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NHCGovAPI {

	public static void main(String[] args) throws IOException {

		Connection conn = Jsoup.connect("http://etax.nhcgov.com/search/CommonSearch.aspx?mode=ADDRESS");

		conn.data("PageNum", "1");
		Document doc = conn.post();

		System.out.println(doc.data());

	}

}
