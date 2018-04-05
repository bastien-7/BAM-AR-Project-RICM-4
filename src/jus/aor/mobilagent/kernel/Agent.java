package jus.aor.mobilagent.kernel;
import java.net.UnknownHostException;
import java.net.URI;
import java.net.URISyntaxException;

public class Agent implements _Agent{
	
	private Route route;
	private AgentServer as;
	
	
	public Agent(Object... args) {
		
	}
	
	@Override
	public void run() {
		System.out.println("Run ! ");
		if(route.hasNext()){
			Etape e = route.next();
			e.action.execute();
			if(route.hasNext()){
				move();
			}
		}
		
	}
	
	/**
	 * Initialise l'agent lors de son déploiement initial dans le bus à agents mobiles.
	 * @param agentServer le serveur hébergeant initialement l'agent.
	 * @param serverName le nom logique du serveur d'agent
	 */
	@Override
	public void init(AgentServer agentServer, String serverName) {
		as = agentServer;
		try {
			route = new Route(new Etape(new URI(serverName),new _Action() {

				@Override
				public void execute() {
					System.out.println("J'effectue une action");
				}
				
			}));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialise l'agent lors de son déploiement sur un des serveurs du bus.
	 * @param server le server actuel pour cet agent
	 * @param serverName le nom logique du serveur d'agent
	 * @throws URISyntaxException 
	 * @throws UnknownHostException 
	 */
	@Override
	public void reInit(AgentServer server, String serverName) throws URISyntaxException {
		this.as = server;
		route = new Route(new Etape(new URI(serverName),new _Action() {
			@Override
			public void execute() {
				System.out.println("J'effectue une action");
			}
			
		}));
	}

	@Override
	public void addEtape(Etape etape) {
		route.add(etape);
	}
	
	//TODO
	protected _Action retour() {
		return null;
		
	}
	
	//TODO
	protected _Service<?> getService(String s){
		return null;
	}
	
	//TODO
	private void move() {
		move(route.get().server);
	}
	
	//TODO
	protected String route() {
		return null;
	}
	
	protected void move(URI url) {
		//TODO
	}
	
	public String toString(){
		return null;
		//TODO
	}
}
