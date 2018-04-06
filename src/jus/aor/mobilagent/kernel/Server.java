/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.mobilagent.kernel;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jus.aor.mobilagent.kernel.BAMAgentClassLoader;
import jus.aor.mobilagent.kernel._Agent;

/**
 * Le serveur principal permettant le lancement d'un serveur d'agents mobiles et
 * les fonctions permettant de déployer des services et des agents.
 * 
 * @author Morat
 */
public final class Server implements _Server {

	/** le nom logique du serveur */
	protected String name;
	/**
	 * le port où sera ataché le service du bus à agents mobiles. Pafr défaut on
	 * prendra le port 10140
	 */
	protected int port = 10140;
	/** le server d'agent démarré sur ce noeud */
	protected AgentServer agentServer;
	/** le nom du logger */
	protected String loggerName;
	/** le logger de ce serveur */
	protected Logger logger = null;

	/**
	 * Démarre un serveur de type mobilagent
	 * 
	 * @param port
	 *            le port d'écoute du serveur d'agent
	 * @param name
	 *            le nom du serveur
	 */
	public Server(final int port, final String name) {
		this.name = name;
		try {
			this.port = port;
			/* mise en place du logger pour tracer l'application */
			loggerName = "jus/aor/mobilagent/" + InetAddress.getLocalHost().getHostName() + "/" + this.name;
			logger = Logger.getLogger(loggerName);
			/* démarrage du server d'agents mobiles attaché à cette machine */
			this.agentServer = new AgentServer(this.port, this.name);
			new Thread(this.agentServer).start();

			/* temporisation de mise en place du server d'agents */
			Thread.sleep(1000);
		} catch (Exception ex) {
			logger.log(Level.FINE, " erreur durant le lancement du serveur" + this, ex);
			return;
		}
	}

	/**
	 * Ajoute le service caractérisé par les arguments
	 * 
	 * @param name
	 *            nom du service
	 * @param classeName
	 *            classe du service
	 * @param codeBase
	 *            codebase du service
	 * @param args
	 *            arguments de construction du service
	 */
	public final void addService(String name, String classeName, String codeBase, Object... args) {
		try {
			// TODO

			BAMServerClassLoader classloader = new BAMServerClassLoader(new URL[] { new URL(codeBase) });

			// BAMServerClassLoader wClassLoader = new BAMServerClassLoader(new URL[] {},
			// this.getClass().getClassLoader());

			Class<?> classe = Class.forName(classeName, true, classloader);

			Constructor<?> constr = classe.getConstructor(Object[].class);

			agentServer.addService(name, (_Service<?>) constr.newInstance(args));
		} catch (Exception ex) {
			logger.log(Level.FINE, " erreur durant le lancement du serveur" + this, ex);
			return;
		}
	}

	/**
	 * deploie l'agent caractérisé par les arguments sur le serveur
	 * 
	 * @param classeName
	 *            classe du service
	 * @param args
	 *            arguments de construction de l'agent
	 * @param codeBase
	 *            codebase du service
	 * @param etapeAddress
	 *            la liste des adresse des étapes
	 * @param etapeAction
	 *            la liste des actions des étapes
	 */
	public final void deployAgent(String classeName, Object[] args, String codeBase, List<String> etapeAddress,
			List<String> etapeAction) {
		try {
			// TODO en terme de startAgent

			BAMAgentClassLoader classloader = new BAMAgentClassLoader(codeBase, this.getClass().getClassLoader());

			Class<?> classe = Class.forName(classeName, true, classloader);

			Constructor<?> constr = classe.getConstructor(Object[].class);

			_Agent agent = (_Agent) constr.newInstance(args);
			agent.init(this.agentServer, this.name);

			while (etapeAddress.iterator().hasNext() && etapeAction.iterator().hasNext()) {
				agent.addEtape(new Etape(new URI(etapeAddress.iterator().next()),
						(_Action) classe.getField(etapeAction.iterator().next()).get(agent)));
			}

			startAgent(agent, classloader);
		} catch (Exception ex) {
			logger.log(Level.FINE, " erreur durant le lancement du serveur" + this, ex);
			return;
		}
	}

	public final void deployAgent(String classeName, Object[] args, String codeBase, List<ServiceDescriptor> LSD) {
		// TODO
	}

	/**
	 * Primitive permettant de "mover" un agent sur ce serveur en vue de son
	 * exécution immédiate.
	 * 
	 * @param agent
	 *            l'agent devant être exécuté
	 * @param loader
	 *            le loader à utiliser pour charger les classes.
	 * @throws Exception
	 */
	protected void startAgent(_Agent agent, BAMAgentClassLoader loader) throws Exception {

		Socket envoi = new Socket(agentServer.site().getHost(), agentServer.site().getPort());

		OutputStream os = envoi.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);

		oos.writeObject(loader.extractCode());
		oos.writeObject(agent);

		oos.close();
		os.close();
		envoi.close();
	}

	public String toString() {
		return "server : " + name + ", port : " + port;
	}
}
