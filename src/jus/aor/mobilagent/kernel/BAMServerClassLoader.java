package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

public class BAMServerClassLoader extends URLClassLoader{

	public BAMServerClassLoader(URL[] urls) {
		super(urls);
		// TODO Auto-generated constructor stub
	}
	
	public BAMServerClassLoader(URL[] urls, ClassLoader loader) {
		super(urls,loader);
	}

	public BAMServerClassLoader(URL[] urls, ClassLoader loader) {
		super(urls,loader);
	}

	@Override
	public void addURL(URL url) {
		// TODO Auto-generated method stub
		super.addURL(url);
	}

}
