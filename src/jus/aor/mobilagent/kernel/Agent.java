package jus.aor.mobilagent.kernel;

import java.net.UnknownHostException;

public class Agent implements _Agent{

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		
	}
	
	/**
	 * Initialise l'agent lors de son déploiement initial dans le bus à agents mobiles.
	 * @param agentServer le serveur hébergeant initialement l'agent.
	 * @param serverName le nom logique du serveur d'agent
	 */
	@Override
	public void init(AgentServer agentServer, String serverName) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	/**
	 * Initialise l'agent lors de son déploiement sur un des serveurs du bus.
	 * @param server le server actuel pour cet agent
	 * @param serverName le nom logique du serveur d'agent
	 * @throws UnknownHostException 
	 */
	@Override
	public void reInit(AgentServer server, String serverName) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void addEtape(Etape etape) {
		// TODO Stub de la méthode généré automatiquement
		
	}

}
