package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.net.URISyntaxException;

public class Agent implements _Agent{
	
	private Route route;
	private AgentServer as;
	
	//TODO
	public Agent(Object... args) {
		
	}
	
	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		// TODO Stub de la méthode généré automatiquement
		as = agentServer;
		try {
			route = new Route(new Etape(new URI(serverName),new _Action() {

				@Override
				public void execute() {
					// TODO Auto-generated method stub
					System.out.println("J'effectue une action");
				}
				
			}));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void reInit(AgentServer server, String serverName) {
		// TODO Stub de la méthode généré automatiquement
		
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
	}
	
	//TODO
	protected String route() {
		return null;
	}

}
