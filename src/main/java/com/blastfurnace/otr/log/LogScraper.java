package com.blastfurnace.otr.log;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class LogScraper {
	
	protected abstract String processElement(Element data, boolean[] returnElement);

	protected String processDocument(String url, String key) throws IOException {
		String returnValue = "";
		boolean[] returnElement = {false};
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc.title());
		Elements elements = doc.select(key);
		for (Element data : elements) {
			returnValue = processElement(data, returnElement);
			if (returnElement[0]) {
				break;
			}
		}
		
		return returnValue;
	}

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://radiogoldindex.com/cgi-local/p2.cgi?ProgramName=Suspense").get();
		System.out.println(doc.title());
		Elements newsHeadlines = doc.select("p");
		for (Element headline : newsHeadlines) {
			System.out.println(headline.text());
		}
		
		doc = Jsoup.connect("http://www.otrsite.com/logs/logc1004.htm").get();
		System.out.println(doc.title());
		newsHeadlines = doc.select("font");
		for (Element headline : newsHeadlines) {
			System.out.println(headline.text());
		}
		
		doc = Jsoup.connect("http://www.audio-classics.com/ltheamazinginterplanetary.html").get();
		System.out.println(doc.title());
		newsHeadlines = doc.select("dt");
		for (Element headline : newsHeadlines) {
			System.out.println(headline.text());
		}
	}
}
