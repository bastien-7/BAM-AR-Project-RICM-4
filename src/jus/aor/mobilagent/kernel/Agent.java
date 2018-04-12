package jus.aor.mobilagent.kernel;
import java.net.UnknownHostException;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class Agent implements _Agent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Route route;
	private transient AgentServer as;
	
	
	public Agent(Object... args) {
	}
	
	@Override
	public void run() {
		System.out.println("Run ! ");
		if(route.hasNext()){
			Etape e = route.next();
			e.action.execute();
			if(route.hasNext()){
				try {
					move();
				} catch (NoSuchElementException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			this.route = new Route(new Etape(new URI(serverName),new _Action() {

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
	}

	@Override
	public void addEtape(Etape etape) {
		route.add(etape);
	}
	
	protected _Action retour() {
		return this.route.next().action;
		
	}
	
	//TODO
	protected _Service<?> getService(String s){
		return null;
	}
	
	private void move() throws NoSuchElementException, IOException {
		move(route.get().server);
	}
	
	protected String route() {
		return this.route.toString();
	}
	
	protected void move(URI url) throws IOException {
		
		Socket envoi = new Socket(url.getHost(),url.getPort());
		
		OutputStream os = envoi.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		
		oos.writeObject(this);
		
		oos.close();
		os.close();
		envoi.close();
	}
	
	public String toString(){
		return "route : " + this.route.toString() + " | agent serveur : " + this.as.toString();
	}

	@Override
	public void init(List<ServiceDescriptor> liste) {
		// TODO Auto-generated method stub
		//
		
	}
}
