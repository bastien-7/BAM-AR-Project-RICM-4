package jus.aor.RMI;

import java.io.IOException;
import java.rmi.RemoteException;

public class Annuaire extends java.rmi.server.UnicastRemoteObject implements _Annuaire{

	private String abonne;
	
	protected Annuaire(String abonne) throws RemoteException {
		super();
		this.abonne = abonne;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Numero get(String abonne) {
		// TODO Auto-generated method stub
		return new Numero(this.abonne);
	}

}
