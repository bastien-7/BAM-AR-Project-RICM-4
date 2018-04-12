/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.mobilagent.hostel;
import java.rmi.Remote;
import java.rmi.RemoteException;

import jus.aor.mobilagent.kernel._Service; 
/**
 * Définit un annuaire téléphonique élémentaire permettant, étant donnée un abonné, d'obtenir son numéro de téléphone.
 * @author Morat 
 */
public interface _Annuaire extends _Service<Numero>{
	/**
	 * restitue le numéro de téléphone de l'abonné
	 * @param abonne l'abonné
	 * @return le numéro de télephone de l'abonné
	 */
	public Numero get(String abonne) throws RemoteException;
}