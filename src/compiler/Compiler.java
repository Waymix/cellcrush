package compiler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Compiler {

	public Compiler() {}

	public void analyse() {
		// check that functions and classes used are all okay and safe
	}

	public ArrayList<String> compile() {
		ArrayList<String> players = new ArrayList<String>();

		String compiled_list = execute("find bin/arena -name *.class");
		System.out.println("Compiled scripts : " + compiled_list);
		
		System.out.println(execute("rm bin/arena/* -rf"));

		String scripts_list[] = execute("find src/arena -name *.java").split("\n");

		for (String a : scripts_list) {
			System.out.println("Compiling " + a + "... ");
			System.out.println( execute("javac -cp bin -d arena/ " + a) );
			
			players.add(a.replaceAll(".java", "").replaceAll("src/arena/", ""));
		}
		
		return players;
	}

	private String execute(String command) {
		String output = "";

		Runtime runtime = Runtime.getRuntime();
		try {
			Process p = runtime.exec(command);

			java.io.InputStream is = p.getInputStream();
			java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
			// And print each line
			String s = null;
			while ((s = reader.readLine()) != null) {
				output += s + "\n";
			}
				
			if (!output.isEmpty())
				output = output.substring(0, output.length() - 1);
			
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}
}
