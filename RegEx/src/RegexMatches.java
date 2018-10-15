import java.util.regex.Matcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.io.FileWriter;

public class RegexMatches {
	   private static String anchor = "ign";
	   private static String domain = "com";
	   private static String final_domain = anchor + "]\\." + domain +".*";
	   //patterns to match regex
	   private static String[] REGEX = {
			   "<li\\s*.*>\\s*<\\/li>",
			   "<noscript>\\s*\\.*\\n*.*\\s*<\\/noscript>", 
			   "<li\\s*[^>]*>\\s*(<[^>]*>\\s*<[^>]*>\\s*)*<\\/li>",
			   //"<link[\\s\\S]*[^" + final_domain,
			   //"<a[\\s\\S]*[^" + final_domain,
			   //"<img[\\s\\S]*[^" + final_domain
			   "<link((?!" + anchor + ").)*?>",
			   "<a((?!" + anchor + ").)*?>",
			   "<img((?!" + anchor + ").)*?>",
			   "<a((?!class=\"nav).)*?>"
	   };
	   //REGEX[i] pattern will be all replaced by REPLACE[i] 
	   private static String[] REPLACE = {"", "", "", "", "", "", ""};
	   
	   //function to return number of files in the first level dir
	   public static int countFilesInDirectory(File directory) {
		      int count = 0;
		      for (File file : directory.listFiles()) {
		          if (file.isFile()) {
		              count++;
		          }
		          if (file.isDirectory()) {
		              count += countFilesInDirectory(file);
		          }
		      }
		      return count;
		  }
	   
	   public static void main(String[] args) { 
		   try {			   
			    int numFiles = countFilesInDirectory(new File("C:\\Users\\Vincent\\Desktop\\filter"));
			    
			    
			    for (int f=1; f<=numFiles; f++) {
			    	FileReader fileReader = new FileReader("C:\\Users\\Vincent\\Desktop\\filter\\html_"+f+".html");
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					StringBuffer stringBuffer = new StringBuffer();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						stringBuffer.append(line);
						stringBuffer.append("\n");
					}
					fileReader.close();
				
					String INPUT = stringBuffer.toString();
					for (int i=0; i<REGEX.length; i++) {
						Pattern p = Pattern.compile(REGEX[i]);
						Matcher m = p.matcher(INPUT);
						INPUT = m.replaceAll(REPLACE[i]);
					}
	        	    				
					FileWriter writer = new FileWriter("C:\\Users\\Vincent\\Desktop\\filter\\html_ " + f + "f.html", true);
					writer.write(INPUT);
					writer.close();					
			    }
				
				System.out.println("Done!");
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }

}
