package src.jus.aor.RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import jus.aor.RMI.Annuaire;
import jus.aor.RMI.Chaine;

public class Server{

	
public static void main(final String args[]) {
	
	int port = 1099;
	//annuaire 2003
    if (args.length == 2){
//        System.out.println("Server <num port> <nombre chaines>");
    	System.out.println("Server <num port>");
        System.exit(1);
      }
    
    
    // installation d'un securityManager
	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new SecurityManager());
	}
	
	
    //  Mise en place du Registery
    try{
    	Registry registry;
    	for (int i = 0; i < 4; i++) {
            LocateRegistry.createRegistry(port+i);
            Chaine chaine = new Chaine();
            chaine.Recuperation_hotel("src/jus/aor/rmi/DataStore/Hotels"+i+".xml");
        	java.rmi.Naming.bind("//localhost:"+(port+i)+"/BAM-RMI", chaine );
		}
    	LocateRegistry.createRegistry(2003);
    	Annuaire annuaire = new Annuaire();
    	annuaire.recupAnnuaire("src/jus/aor/rmi/DataStore/Annuaire.xml");
    	java.rmi.Naming.bind("//localhost:2003"+"/BAM-RMI", annuaire);
    }
    catch(Exception e){
    	System.out.println("Registry creation impossible in Server");
    }	

	}
}
