package jus.aor.RMI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Chaine extends java.rmi.server.UnicastRemoteObject implements _Chaine {

	public Chaine() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<Hotel> hotels;
	
	
	@Override
	public List<Hotel> get(String localisation) {
		List<Hotel> hotel_ok = new ArrayList<Hotel>();
		for(Hotel h : this.hotels){
			if(localisation.equals(h.localisation)){
				hotel_ok.add(h);
			}
		}
		return hotel_ok;
	}

	public void Recuperation_hotel(String fichier) throws ParserConfigurationException, SAXException, IOException{
	
		/* récupération des hôtels de la chaîne dans le fichier xml passé en 1er argument */
		DocumentBuilder docBuilder = null;
		Document doc=null;
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
			doc = docBuilder.parse(new File(fichier));

		String name, localisation;
		NodeList list = doc.getElementsByTagName("Hotel");
		NamedNodeMap attrs;
		/* acquisition de toutes les entrées de la base d'hôtels */
		for(int i =0; i<list.getLength();i++) {
			attrs = list.item(i).getAttributes();
			name=attrs.getNamedItem("name").getNodeValue();
			localisation=attrs.getNamedItem("localisation").getNodeValue();
			this.hotels.add(new Hotel(name,localisation));
		}

	}
	
	public void Configuration(String fichier) throws ParserConfigurationException, SAXException, IOException{
	
	/* récupération d'informations de configuration */
	DocumentBuilder docBuilder = null;
	Document doc = null;
	docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	doc = docBuilder.parse(new File(fichier));
	//On récupère les arguments pour la construction de Chaine
	String arguments = doc.getElementsByTagName("service").item(0).getAttributes().getNamedItem("args").getNodeValue();
	}

}
