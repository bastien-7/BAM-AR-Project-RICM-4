package jus.aor.mobilagent.kernel;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

/**
 * Le server qui supporte le modèle du bus à agents mobiles "mobilagent".
 * Lorsqu'un agent se présente, le serveur charge son codebase et l'objet
 * représentant cet agent, puis il active cet objet qui exécute l'action que
 * l'agent a à réaliser sur ce serveur.
 * 
 * @author Morat
 */
final class AgentServer implements Runnable {
	/** le logger de ce serveur */
	private Logger logger;
	/** La table des services utilisables sur ce serveur */
	private Map<String, _Service<?>> services;
	/** Le port auquel est attaché le serveur */
	private int port;
	/** l'état du serveur */
	private boolean running;
	/** la socket de communication du bus */
	private ServerSocket s;
	/** le nom logique de ce serveur */
	private String name;

	/**
	 * L'initialisation du server
	 * 
	 * @param port
	 *            the port où est attaché le service du bus à agents mobiles
	 * @param name
	 *            le nom du serveur
	 * @throws Exception
	 *             any exception
	 */
	AgentServer(int port, String name) throws Exception {
		this.name = name;
		logger = Logger.getLogger("jus.aor.mobilagent." + InetAddress.getLocalHost().getHostName() + "." + name);
		this.port = port;
		services = new HashMap<String, _Service<?>>();
		s = new ServerSocket(port);
	}

	/**
	 * le lancement du serveur
	 */
	public void run() {
		// Reception-Envoi des agents
		Socket client;
		try {
			while (true) {
				client = s.accept();
				Agent agent = (Agent) this.getAgent(client);
				agent.reInit(this, this.name);
				new Thread(agent).start();
				client.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ajoute un service au serveur
	 * 
	 * @param name
	 *            le nom du service
	 * @param service
	 *            le service
	 */
	void addService(String name, _Service<?> service) {
		services.put(name, service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return "mobilagent://" + InetAddress.getLocalHost().getHostName() + ":" + port;
		} catch (UnknownHostException e) {
			return "mobilagent://";
		}
	}

	/**
	 * restitue le service de nom name ou null si celui-ci n'est pas attaché au
	 * serveur.
	 * 
	 * @param name
	 * @return le service souhaité ou null
	 */
	_Service<?> getService(String name) {
		return services.get(name);
	}

	/**
	 * restitue l'URI de ce serveur qui est de la forme :
	 * "mobilagent://<host>:<port>" ou null si cette opération échoue.
	 * 
	 * @return l'URI du serveur
	 */
	URI site() {
		try {
			return new URI("mobilagent://" + InetAddress.getLocalHost().getHostName() + ":" + port);
		} catch (Exception e) {
			return null;
		}
	}

	private _Agent getAgent(Socket sock) throws ClassNotFoundException, IOException {

		InputStream is = sock.getInputStream();
		BAMAgentClassLoader loader = new BAMAgentClassLoader(this.getClass().getClassLoader());
		AgentInputStream ais = new AgentInputStream(is, loader);

		Jar j = (Jar) ais.readObject();
		loader.integrateCode(j);

		Agent agent = (Agent) ais.readObject();
		if (agent!=null) {
			Starter.getLogger().log(Level.ALL,   "Un agent vient d'arriver sur " + this.name + " sa route est : " + agent.route());
		}
		
		is.close();
		ais.close();

		return (_Agent) agent;

	}
}

/**
 * ObjectInputStream spécifique au bus à agents mobiles. Il permet d'utiliser le
 * loader de l'agent.
 * 
 * @author Morat
 */
class AgentInputStream extends ObjectInputStream {
	/**
	 * le classLoader à utiliser
	 */
	BAMAgentClassLoader loader;

	AgentInputStream(InputStream is, BAMAgentClassLoader cl) throws IOException {
		super(is);
		loader = cl;
	}

	protected Class<?> resolveClass(ObjectStreamClass cl) throws IOException, ClassNotFoundException {
		return loader.loadClass(cl.getName());
	}
}
