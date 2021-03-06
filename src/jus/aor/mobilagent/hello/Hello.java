package jus.aor.mobilagent.hello;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import jus.aor.mobilagent.kernel._Action;
import jus.aor.mobilagent.kernel.Agent;
import jus.aor.mobilagent.kernel.Starter;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * 
 * @author Morat
 */

public class Hello extends Agent {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * construction d'un agent de type hello.
	 * 
	 * @param args
	 *            aucun argument n'est requis
	 */

	public Hello(Object... args) {
	}

	/**
	 * l'action à entreprendre sur les serveurs visités
	 */
	protected _Action doIt = new _Action() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			Starter.getLogger().log(Level.ALL,
					"Execution de l'action doIt\n\n Hello ! I'm the Hello Agent. Thank you for your attention. Good Bye !");
		}
		// ...
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see jus.aor.mobilagent.kernel.Agent#retour()
	 */
	@Override
	protected _Action retour() {
		return doIt;
	}
	// ...
}
