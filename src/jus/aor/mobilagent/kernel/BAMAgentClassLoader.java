package jus.aor.mobilagent.kernel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarOutputStream;

public class BAMAgentClassLoader extends ClassLoader{
	//we can't add extra Maps to an existing Jar, so let's store a MAP instead of storing a JAR
	private Map<String,byte[]> MapJAR;
	
	BAMAgentClassLoader(String jarName, ClassLoader parent) throws JarException, IOException {
		super(parent);
		this.MapJAR = new HashMap<String,byte[]>();
		Jar my_jar = new Jar(jarName);
		while (my_jar.iterator().hasNext()) {
			Entry<String,byte[]> ent = my_jar.iterator().next();
			// Save JAR information in the MAP for future export
			MapJAR.put(ent.getKey(), ent.getValue());
			defineClass(ent.getKey(),ent.getValue(),0,ent.getValue().length);
			System.out.println("BAMAgentClassLoader : charge class "+ent.getKey());
		}
		
	}
	
	BAMAgentClassLoader(ClassLoader parent) {
		super(parent);
		this.MapJAR = new HashMap<String,byte[]>();
	}
	
	void integrateCode(Jar my_jar) {
		while (my_jar.iterator().hasNext()) {
			Entry<String,byte[]> ent = my_jar.iterator().next();
			defineClass(ent.getKey(),ent.getValue(),0,ent.getValue().length);
			System.out.println("BAMAgentClassLoader : charge class "+ent.getKey());
		}
	}
	
	private String className(String s) {
		return s.replace(".class", "");
	}
	
	Jar extractCode() throws IOException {
		// Creation of a new file 
		// Copy the current class loader (that we saved in MapJAR) in the file
		// return a new Jar (filename)
		
		File my_file = File.createTempFile("my_file_name", ".txt");
		JarOutputStream JOS = new JarOutputStream(new FileOutputStream(my_file)) ;
		for(String s : this.MapJAR.keySet()) {
			JOS.putNextEntry(new JarEntry(s));
			JOS.write(this.MapJAR.get(s));
		}
		JOS.flush();
		JOS.close();
				
		return new Jar(my_file.getPath());
		
	}
	
	public String toString() {
		return "BAMAgentClassLoader : parent :"+this.getParent().toString()+"MAP :"+MapJAR.toString() ;
		
	}
	
}