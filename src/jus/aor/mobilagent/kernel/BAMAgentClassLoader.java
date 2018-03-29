package jus.aor.mobilagent.kernel;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.jar.JarException;

public class BAMAgentClassLoader extends ClassLoader{
	
	public BAMAgentClassLoader(String jarName, ClassLoader parent) throws JarException, IOException {
		super(parent);
		Jar my_jar = new Jar(jarName);
		while (my_jar.iterator().hasNext()) {
			Entry<String,byte[]> ent = my_jar.iterator().next();
			defineClass(ent.getKey(),ent.getValue(),0,ent.getValue().length);
			System.out.println("BAMAgentClassLoader : charge class "+ent.getKey());
		}
	}
	
	public BAMAgentClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	public void integrateCode(Jar my_jar) {
		while (my_jar.iterator().hasNext()) {
			Entry<String,byte[]> ent = my_jar.iterator().next();
			defineClass(ent.getKey(),ent.getValue(),0,ent.getValue().length);
			System.out.println("BAMAgentClassLoader : charge class "+ent.getKey());
		}
	}
	
	//TODO
	public Jar extractCode() {
		return null;
		
	}
	
	private String className(String s) {
		return s;
	}
	
}