/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.RMI;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Représente un client effectuant une requête lui permettant d'obtenir les numéros de téléphone des hôtels répondant à son critère de choix.
 * @author  Morat
 */
public class LookForHotel{
	/** le critère de localisaton choisi */
	private String localisation;
	private int port = 1099;
	private ArrayList<_Chaine> chaines = new ArrayList<>();
	private ArrayList<Hotel> hotels = new ArrayList<>();
	private _Annuaire annuaire;
	private HashMap<String, Numero> numero_hotel = new HashMap<>();
	// ...
	/**
	 * Définition de l'objet représentant l'interrogation.
	 * @param args les arguments n'en comportant qu'un seul qui indique le critère
	 *          de localisation
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public LookForHotel(String[] args) throws RemoteException, MalformedURLException, NotBoundException{
		localisation = args[0];
		
		for (int i = 0; i<4; i++) {
			_Chaine obj = (_Chaine) java.rmi.Naming.lookup("//localhost:"+(port+i)+"/BAM-RMI");
			chaines.add(obj);			
		}
		System.out.println("Chaines trouvés : " + chaines.size());

		this.annuaire = (_Annuaire) java.rmi.Naming.lookup("//localhost:2003/BAM-RMI");
		//System.out.println("Les annuaires trouvés : " + annuaire.toString());

		
	}
	/**
	 * réalise une intérrogation
	 * @return la durée de l'interrogation
	 * @throws RemoteException
	 */
	public long call() throws RemoteException {
		// ...
		long debut = System.currentTimeMillis();
		
		ArrayList<Hotel> hotels_a_ajouter;
		
		for (int i =0; i<4; i++) {
			hotels_a_ajouter = (ArrayList<Hotel>) chaines.get(i).get(localisation);
			hotels.addAll(hotels_a_ajouter);			
		}
		
		System.out.println("\nHotels trouvés : " + hotels.size());
		System.out.println("\nLes 5 premiers Hotels sont : ");
		for (int i =0 ; i<5; i++) {
			System.out.println(hotels.get(i).toString());
		}
		 
		for (int i = 0; i< hotels.size(); i++) {
			Numero n = this.annuaire.get(hotels.get(i).name);
			numero_hotel.put(hotels.get(i).name, n);
		}

		
		long fin = System.currentTimeMillis();
		
		System.out.println("\nNuméros trouvés : " + numero_hotel.size());
		
		System.out.println("\nLes 5 premiers numéros sont : ");
		for (int i =0 ; i<5; i++) {
			System.out.println(this.numero_hotel.get(hotels.get(i).name).toString());
		}

		
		return fin-debut;
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		LookForHotel lfh = new LookForHotel(args);
		
		long duree = lfh.call();
		System.out.println("durée du call : " + duree + " miliseconds");
	}

	// ...
}
