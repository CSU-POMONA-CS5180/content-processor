import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webprocessor {

	public static void main(String[] args) throws IOException {
    	
		//read html into string
    	StringBuilder bldr = new StringBuilder();
    	String str;

    	BufferedReader in = new BufferedReader(new FileReader("/Users/wilsenkosasih/desktop/repository/html_1.html"));
    	while((str = in.readLine())!=null)
    	      bldr.append(str);
    	in.close();

    	String html = bldr.toString();
    	    	
    	//convert string into document type
    	Document doc = Jsoup.parse(html);
    	
    	//remove certain elements
    	for(Element element : doc.select("img"))
    		element.remove();
    	
    	for(Element element : doc.select("script"))
    		element.remove();
    	
    	for(Element element : doc.select("iframe"))
    		element.remove();
    	
    	for(Element element : doc.select("nav"))
    		element.remove();
    	
    	//for(Element element : doc.select("svg"))
    	//	element.remove();
    	
    	//convert doc back into html and produce file
    	File file = new File("/Users/wilsenkosasih/desktop/html_1new.html");    	
    	String newhtml = doc.html();
        
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(newhtml);
		
		fileWriter.flush();
		fileWriter.close();
    	
	}

}
