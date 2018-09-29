package html_noise_filter;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;


public class HTMLNoiseFilter {
	private int numFiles;	//number of files in repo folder
	private String filter_path; //filtered files path
	private String html_path; //repo path
	
	private int countFiles(String path) {
		File dir = new File(this.html_path);		
		return dir.list().length; 
	}
	
	public HTMLNoiseFilter(String p1, String p2) {
		html_path = p1;
		filter_path = p2;
		numFiles = countFiles(html_path);
	}
	
	private String getFilterPath() {
		return this.filter_path;
	}
	
	private String getHTMLPath() {
		return this.html_path;
	}
	
	private int getFileCount() {
		return this.numFiles;
	}
	
	private void noiseFilter() {
		for (int i=0; i<this.numFiles; i++) {
			try {
				File input = new File(this.getHTMLPath() + "\\html_" + (i+1) + ".html");
				Document doc = Jsoup.parse(input, "UTF-8");
				doc.select("img").remove();
				doc.select("a").remove();
				doc.select("style").remove();
				doc.select("link").remove();
				doc.select("script").remove();
				doc.select("hr").remove();
				doc.select("button").remove();
				doc.select("br").remove();
				
				File file = new File(this.getFilterPath() + "\\html_" + (i+1) + ".html");	    	
		    	
		    	String html = doc.html();		        
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(html);				
				fileWriter.flush();
				fileWriter.close();
			}
			catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {		
		String filter_path = "C:\\Users\\Vincent\\Desktop\\filter";
		String html_path = "C:\\Users\\Vincent\\Desktop\\repository";
				
		HTMLNoiseFilter hnf = new HTMLNoiseFilter(html_path, filter_path);
		
		System.out.println("Reading " + hnf.getFileCount() + " html files from " + "\"" + hnf.getHTMLPath() + "\"");
		
		hnf.noiseFilter();
		
		System.out.println("Finished saving noise filter files to: " + "\"" + hnf.getFilterPath() + "\"");
	}
}
