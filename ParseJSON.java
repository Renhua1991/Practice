import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ParseJSON {
	public static void main(String args[]) throws IOException {
		File file = new File("data/users.json");
		File file1 = new File("data/Moosewood Restaurant.json");
		File outFile = new File("data/Moosewood1.json");
		PrintWriter out = new PrintWriter(new FileWriter(outFile));
		Scanner sc = new Scanner(file1);
		Scanner sc1 = new Scanner(file);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			Object obj = JSONValue.parse(line);
			JSONObject json = (JSONObject) obj;
			String user_id = (String)json.get("user_id");
		
			while(sc1.hasNext()){
				String line1 = sc1.nextLine();
				Object obj1 = JSONValue.parse(line1);
				JSONObject json1 = (JSONObject) obj1;
				if(json1.get("user_id").equals(user_id)){
					out.println(line + line1);
					File file2 = new File("data/users.json");
					Scanner sc2 = new Scanner(file2);
					line1 = sc2.nextLine();
					
				}
			}
			
		}
		sc.close();
		out.close();
		System.out.println("DONE");
	}
}