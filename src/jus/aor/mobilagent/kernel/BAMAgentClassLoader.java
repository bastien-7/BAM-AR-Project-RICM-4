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

public class BAMAgentClassLoader extends ClassLoader {
	// we can't add extra Maps to an existing Jar, so let's store a MAP instead of
	// storing a JAR
	private Jar my_jar;

	BAMAgentClassLoader(String jarName, ClassLoader parent) throws JarException, IOException {
		super(parent);
		this.my_jar = new Jar(jarName);
		while (my_jar.iterator().hasNext()) {
			Entry<String, byte[]> ent = my_jar.iterator().next();
			// Save JAR information in the MAP for future export
			defineClass(ent.getKey(), ent.getValue(), 0, ent.getValue().length);
			System.out.println("BAMAgentClassLoader : charge class " + ent.getKey());
		}

	}

	BAMAgentClassLoader(ClassLoader parent) {
		super(parent);
	}

	void integrateCode(Jar my_jar) {
		this.my_jar=my_jar;
		while (my_jar.iterator().hasNext()) {
			Entry<String, byte[]> ent = my_jar.iterator().next();
			defineClass(ent.getKey(), ent.getValue(), 0, ent.getValue().length);
			System.out.println("BAMAgentClassLoader : charge class " + ent.getKey());
		}
	}

	private String className(String s) {
		return s.replace(".class", "");
	}

	Jar extractCode() throws IOException {


		return this.my_jar;

	}

	public String toString() {
		return "BAMAgentClassLoader : parent :" + this.getParent().toString() + "Jar :" +this.my_jar.toString();

	}
}