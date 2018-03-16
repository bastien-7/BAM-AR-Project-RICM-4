/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.RMI;

/**
 * Un numéro de téléphone
 * @author Morat 
 */
public class Numero {
	/** le numéro de téléphone */
	public String numero;
	/**
	 * Construction d'un numéro de téléphone.
	 * @param numero le numéro
	 */
	public Numero(String numero) { this.numero=numero;}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { return numero;}
}
