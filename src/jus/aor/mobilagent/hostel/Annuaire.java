package jus.aor.mobilagent.hostel;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Annuaire implements _Annuaire{

	public Annuaire(Object... args) {
		// TODO Auto-generated constructor stub
	}

	private HashMap<String, Numero> data_annuaire = new HashMap<>();
	
	/*protected Annuaire(String filename) throws ParserConfigurationException, SAXException, IOException {
		super();
		this.recupAnnuaire(filename);
		// TODO Auto-generated constructor stub
	}*/
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/* Récupération de l'annuaire dans le fichier xml */
	public void recupAnnuaire(String filename) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder docBuilder = null;
		Document doc=null;docBuilder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc=docBuilder.parse(new File(filename));

		String name, numero;
		NodeList list = doc.getElementsByTagName("Telephone");
		NamedNodeMap attrs;
		/* acquisition de toutes les entrées de l'annuaire */
		for(int i = 0;i<list.getLength();i++)
		{
			attrs = list.item(i).getAttributes();
			name = attrs.getNamedItem("name").getNodeValue();
			numero = attrs.getNamedItem("numero").getNodeValue();
			this.data_annuaire.put(name, new Numero(numero));
		}


	}

	@Override
	public Numero get(String abonne) {
		// TODO Auto-generated method stub
		Numero n = this.data_annuaire.get(abonne);
		return n;
	}

	@Override
	public Numero call(Object... params) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
