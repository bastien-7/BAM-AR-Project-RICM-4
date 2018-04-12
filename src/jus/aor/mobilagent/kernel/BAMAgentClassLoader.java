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
		this.integrateCode(new Jar(jarName));

	}

	BAMAgentClassLoader(ClassLoader parent) {
		super(parent);
		if (parent instanceof BAMAgentClassLoader) {
			try {
				my_jar = ((BAMAgentClassLoader) parent).extractCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void integrateCode(Jar my_jar) {
		this.my_jar = my_jar;
		for (Entry<String, byte[]> ent : my_jar) {
			Class<?> classe = this.defineClass(this.className(ent.getKey()), ent.getValue(), 0, ent.getValue().length);
			super.resolveClass(classe);
			// System.out.println("BAMAgentClassLoader : charge class " + ent.getKey());
		}

	}

	private String className(String s) {
		return s.replace(".class", "").replace('/', '.');
	}

	Jar extractCode() throws IOException {

		return this.my_jar;

	}

	public String toString() {
		return "BAMAgentClassLoader : parent :" + this.getParent().toString() + "Jar :" + this.my_jar.toString();

	}
}