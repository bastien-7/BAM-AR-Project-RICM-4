package jus.aor.mobilagent.kernel;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class BAMAgentClassLoader extends ClassLoader{
	
	public BAMAgentClassLoader(String jarName, ClassLoader parent) {
		super(parent);	
	}
	
	public BAMAgentClassLoader(ClassLoader parent) {
		// TODO Auto-generated constructor stub
		super(parent);
	}
}
