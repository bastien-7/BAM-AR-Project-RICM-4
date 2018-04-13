/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.mobilagent.hostel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel.Starter;
import jus.aor.mobilagent.kernel._Action;
import jus.aor.mobilagent.kernel._Service;

/**
 * Représente un client effectuant une requête lui permettant d'obtenir les
 * numéros de téléphone des hôtels répondant à son critère de choix.
 * 
 * @author Morat
 */
public class LookForHotel extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** le critère de localisaton choisi */
	private String localisation;
	private int port = 1099;
	// private ArrayList<_Chaine> chaines = new ArrayList<>();
	private ArrayList<Hotel> hotels = new ArrayList<>();
	// private _Annuaire annuaire;
	private HashMap<String, Numero> numero_hotel = new HashMap<>();

	// ...
	/**
	 * Définition de l'objet représentant l'interrogation.
	 * 
	 * @param args
	 *            les arguments n'en comportant qu'un seul qui indique le critère de
	 *            localisation
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MalformedURLException
	 */
	public LookForHotel(Object... args) throws RemoteException, MalformedURLException, NotBoundException {
		localisation = (String) args[0];
		//
		// for (int i = 0; i<4; i++) {
		// _Chaine obj = (_Chaine)
		// java.rmi.Naming.lookup("//localhost:"+(port+i)+"/BAM-RMI");
		// chaines.add(obj);
		// }
		// System.out.println("Chaines trouvés : " + chaines.size());
		//
		// this.annuaire = (_Annuaire)
		// java.rmi.Naming.lookup("//localhost:2003/BAM-RMI");
		// System.out.println("Les annuaires trouvés : " + annuaire.toString());

	}

	protected _Action findHotel = new _Action() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Starter.getLogger().log(Level.ALL, "Execution de l'action findHotel\n\n LookForHotel !");
			_Service<?> hotel = getService("Hotels");
			hotels.addAll((ArrayList<Hotel>) hotel.call(localisation));

		}
		// ...
	};

	protected _Action findTelephone = new _Action() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Starter.getLogger().log(Level.ALL, "Execution de l'action findTelephone\n\n LookForHotel !");
			_Service<?> telephone = getService("Telephones");
			for (int i = 0; i < hotels.size(); i++) {
				numero_hotel.put(hotels.get(i).name, (Numero) telephone.call(hotels.get(i).name));
			}

		}
		// ...
	};

	public String toString() {
		String result = "";
		int i=0;
		for(Entry<String, Numero> ent : numero_hotel.entrySet()) {
			result += ent.toString()+"\n";
			
			i++;
			if(i==5) {
				break;
			}
		}
		long finduree = System.currentTimeMillis();
		long resultduree = finduree - Starter.getDuree();
		return result + "\n durée de l'exécution : " + resultduree;
	}

	/**
	 * réalise une interrogation
	 * 
	 * @return la durée de l'interrogation
	 * @throws RemoteException
	 */
	// public long call() throws RemoteException {
	// // ...
	// long debut = System.currentTimeMillis();
	//
	// ArrayList<Hotel> hotels_a_ajouter;
	//
	// for (int i = 0; i < 4; i++) {
	// hotels_a_ajouter = (ArrayList<Hotel>) chaines.get(i).get(localisation);
	// hotels.addAll(hotels_a_ajouter);
	// }
	//
	// System.out.println("\nHotels trouvés : " + hotels.size());
	// System.out.println("\nLes 5 premiers Hotels sont : ");
	// for (int i = 0; i < 5; i++) {
	// System.out.println(hotels.get(i).toString());
	// }
	//
	// for (int i = 0; i < hotels.size(); i++) {
	// Numero n = this.annuaire.get(hotels.get(i).name);
	// numero_hotel.put(hotels.get(i).name, n);
	// }
	//
	// long fin = System.currentTimeMillis();
	//
	// System.out.println("\nNuméros trouvés : " + numero_hotel.size());
	//
	// System.out.println("\nLes 5 premiers numéros sont : ");
	// for (int i = 0; i < 5; i++) {
	// System.out.println(this.numero_hotel.get(hotels.get(i).name).toString());
	// }
	//
	// return fin - debut;
	// }

	// ...
}
