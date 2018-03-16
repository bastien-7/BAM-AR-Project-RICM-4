package jus.aor.RMI;

import java.rmi.RemoteException;
import java.util.List;

public class Chaine extends java.rmi.server.UnicastRemoteObject implements _Chaine{

	protected Chaine() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Hotel> get(String localisation) {
		// TODO Auto-generated method stub
		return null;
	}

}
