package grup5A;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;


import net.xqj.exist.ExistXQDataSource;

public class GestorGrup5A {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static XQConnection conn;
	public static String[] elementos = {"ConsultesPresencialsSalesDeConsulta","UsuarisSalesDeConsulta",
				"Prestecs","Visites","UsosInternetTotals"};
	public static String[] carpetas = {"Arxius_Consultes", "Arxius_Usuaris", "Biblio_Prestecs", "Biblio_Visites", "Usos_Internet"};
	public static String[] nombres = {"arxius-consultes", "arxius-usuaris", "biblioteques-prestecs", "biblioteques-visites", "biblioteques-usosinternet"};
	public static String[] anos = {"2013","2014","2015"};
	public static ArrayList<String> valores = new ArrayList<>();
	public static HashSet<String> distritos = new HashSet<>();
	
	public static void establirConnexio() throws XQException {
		XQDataSource xqs = new ExistXQDataSource();
		xqs.setProperty("serverName", "localhost");
		xqs.setProperty("port", "8080");
		
		XQConnection conn1= xqs.getConnection("admin", "admin");
		
		conn= conn1;
	}
	
	public static void closeConnection(){
		try{
			if (conn!=null){
				conn.close();
			}
		}catch (XQException ex){
			ex.printStackTrace();
		}			
	}
	
	public static void showAllData() throws XQException{
		
		XQExpression xqe = conn.createExpression();
		for (int i=0;i<=elementos.length-1;i++){
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/"+carpetas[i]+"/"+nombres[i]+"-"+anos[j]+".xml')//xml/"+nombres[i]+"\n"+
						 "let $any := data($p/Any)\n"
					        + "let $ambit := data($p/Ambit)\n" 
					        + "let $titularitat := data($p/Titularitat)\n"
					        + "let $tipuseq := data($p/TipusEquipament)\n"
					        + "let $eq := data($p/Equipament)\n"
					        + "let $districte := data($p/Districte)\n" 				       
					        + "let $var := data($p/"+elementos[i]+")\n"
					        + "let $nota := data($p/Nota)\n"
					        + "return concat($any,',',$ambit,',',$titularitat,',',$tipuseq,',',$eq,',',$districte,',',$var,',',$nota)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				System.out.println("\n "+carpetas[i].toUpperCase()+" "+anos[j]+": ");
				
				while (xqrs.next()) {
			     String line = xqrs.getItemAsString(null);
			      String[] output = line.split(",");
			      for(int h = 0; h < output.length; ++h){			    	 
			        System.out.println(output[h]);
			      }
			      System.out.println();
			    }
			}			
		}				
	}
	
	public static void showAllDataByYear(String y) throws XQException {

		XQExpression xqe = conn.createExpression();
		for (int i = 0; i <= elementos.length - 1; i++) {
				String cad1 = "for $p in doc('/db/GRUP5A/" + carpetas[i] + "/" + nombres[i] + "-"+y
						+ ".xml')//xml/" + nombres[i] + "\n" 
						+ "let $any := data($p/Any)\n"
						+ "let $ambit := data($p/Ambit)\n" 
						+ "let $titularitat := data($p/Titularitat)\n"
						+ "let $tipuseq := data($p/TipusEquipament)\n" 
						+ "let $eq := data($p/Equipament)\n"
						+ "let $districte := data($p/Districte)\n" 
						+ "let $var := data($p/" + elementos[i] + ")\n"
						+ "let $nota := data($p/Nota)\n"
						+ "return concat($any,',',$ambit,',',$titularitat,',',$tipuseq,',',$eq,',',$districte,',',$var,',',$nota)\n";

				XQResultSequence xqrs = xqe.executeQuery(cad1);
				System.out.println("====================================");
				System.out.println("\n " + carpetas[i].toUpperCase() + " " + y + ": ");
				System.out.println("====================================");

				while (xqrs.next()) {
					String line = xqrs.getItemAsString(null);
					String[] output = line.split(",");
					for (int h = 0; h < output.length; ++h) {
						System.out.println(output[h]);
					}
					System.out.println();
				}			
		}
	}
	
	public static void loadDistricNames() throws XQException{
		
		XQExpression xqe = conn.createExpression();
		for (int i=0;i<=elementos.length-1;i++){
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/"+carpetas[i]+"/"+nombres[i]+"-"+anos[j]+".xml')//xml/"+nombres[i]+"\n"
					        + "let $districte := data($p/Districte)\n" 					       
					        + "return $districte";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				while (xqrs.next()) {					
				     String line = xqrs.getItemAsString(null);
				     distritos.add(line);				     
				}
			}
		}
		for (String s:distritos){
			System.out.println(s);
		}		
	}
	
	public static void showByDistrict(String dist) throws XQException {

		XQExpression xqe = conn.createExpression();
		for (int i = 0; i <= elementos.length - 1; i++) {
			for (int j = 0; j <= anos.length - 1; j++) {
				String cad1 = "for $p in doc('/db/GRUP5A/" + carpetas[i] + "/" + nombres[i] + "-" + anos[j]
						+ ".xml')//xml/" + nombres[i] + "\n" 
						+ "let $any := data($p/Any)\n"
						+ "let $ambit := data($p/Ambit)\n" 
						+ "let $titularitat := data($p/Titularitat)\n"
						+ "let $tipuseq := data($p/TipusEquipament)\n" 
						+ "let $eq := data($p/Equipament)\n"
						+ "let $districte := data($p/Districte)\n" 
						+ "let $var := data($p/" + elementos[i] + ")\n"
						+ "let $nota := data($p/Nota)\n"
						+ "return concat($any,',',$ambit,',',$titularitat,',',$tipuseq,',',$eq,',',$districte,',',$var,',',$nota)\n";

				XQResultSequence xqrs = xqe.executeQuery(cad1);
				System.out.println("====================================");
				System.out.println("\t"+carpetas[i].toUpperCase() + " " + anos[j] + ": ");
				System.out.println("====================================");

				while (xqrs.next()) {
					String line = xqrs.getItemAsString(null);
					String[] output = line.split(",");
					String code = output[5].substring(0, 2);
					String nom = output[5].substring(4, output[5].length());
					if (dist.equals(code) || dist.equals(nom)) {
						for (int h = 0; h < output.length; ++h) {
							System.out.println(output[h]);
						}
						System.out.println();
					}
				}
			}
			System.out.println("////////////////////////////////////////\n");
		}
	}
	
	public static Map<Float, String> consultasPorUsuario(String year) throws XQException{
		
		HashMap<String, Float> datos = new HashMap<>();
		HashMap<String, Float> datos1 = new HashMap<>();
		HashMap<Float, String> datosOrd = new HashMap<>();
		float sum=0;
		XQExpression xqe = conn.createExpression();
		String dist = "";	
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/Arxius_Consultes/arxius-consultes-"+year+".xml')//xml/arxius-consultes\n"
			        + "let $districte := data($p/Districte)\n" 				       
			        + "let $var := data($p/ConsultesPresencialsSalesDeConsulta)\n"
			        + "return concat($districte,',',$var)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				
				while (xqrs.next()) {					
					String line = xqrs.getItemAsString(null);
				      String[] output = line.split(",");
				      if(dist.equals(output[0])){
				    	  sum=sum+Float.parseFloat(output[1]);
				      }else{				    	  
				    	  sum=Float.parseFloat(output[1]);
				      }
				     datos.put(output[0], sum);	
				     dist=output[0];
			     }				
			}
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/Arxius_Usuaris/arxius-usuaris-"+year+".xml')//xml/arxius-usuaris\n"
			        + "let $districte := data($p/Districte)\n" 				       
			        + "let $var := data($p/UsuarisSalesDeConsulta)\n"
			        + "return concat($districte,',',$var)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				
				while (xqrs.next()) {					
					String line = xqrs.getItemAsString(null);
				      String[] output = line.split(",");
				      if(dist.equals(output[0])){
				    	  sum=sum+Float.parseFloat(output[1]);
				      }else{				    	  
				    	  sum=Float.parseFloat(output[1]);
				      }
				     dist=output[0];
				     datos1.put(output[0],sum);	
			     }				
			}
		
			for (Map.Entry<String, Float> entry : datos.entrySet()) {
				for (Map.Entry<String, Float> entry1 : datos1.entrySet()) {
					if(entry.getKey().equals(entry1.getKey())){
						datosOrd.put(entry.getValue()/entry1.getValue(), entry.getKey());						
					}
				}						  
			}
			Map<Float, String> treeMap = new TreeMap<Float, String>(datosOrd);

			return treeMap;
		}
	
	public static void showFilesInFolderByYear(int sel) throws Exception{
		for(int i = 0; i < anos.length; ++i){
			System.out.println(nombres[sel-1] + "-" + anos[i]);
		}
		System.out.println("Escull l'any del que vols veure les dades: ");
		String year = br.readLine();
		if(year.equals("2013") || year.equals("2014") || year.equals("2015")){
			String cad = "for $p in doc('/db/GRUP5A/" + carpetas[sel-1] + "/" + nombres[sel-1] + "-" + year
					+ ".xml')//xml/" + nombres[sel-1] + "\n" 
					+ "let $ambit := data($p/Ambit)\n" 
					+ "let $titularitat := data($p/Titularitat)\n"
					+ "let $tipuseq := data($p/TipusEquipament)\n" 
					+ "let $eq := data($p/Equipament)\n"
					+ "let $districte := data($p/Districte)\n" 
					+ "let $var := data($p/" + elementos[sel-1] + ")\n"
					+ "let $nota := data($p/Nota)\n"
					+ "return concat($ambit,',',$titularitat,',',$tipuseq,',',$eq,',',$districte,',',$var,',',$nota)\n";
			
			XQExpression xqe = conn.createExpression();
			XQResultSequence xqrs = xqe.executeQuery(cad);
			while (xqrs.next()) {
				String line = xqrs.getItemAsString(null);
				String[] output = line.split(",");
				String ambit = output[0];
				String titul = output[1];
				String tipus = output[2];
				String eq = output[3];
				String dist = output[4];
				String var = output[5];
//				String nota = output[6];
				
				System.out.println("Àmbit: "+ambit+"\nTitularitat: "+titul+"\nTipus Equipament: "+tipus+"\nEquipament: "+eq+"\nDistricte: "+dist+"\n"+elementos[sel-1]+": "
						+var);
				System.out.println();
			}
		}
		else{
			System.out.println("Data introduïda incorrecta. Torna-ho a intentar.");
		}		
	}
		
	
	
	public static Map<Float, String> prestamosVisitas(String year) throws XQException{
		
		HashMap<String, Float> datos = new HashMap<>();
		HashMap<String, Float> datos1 = new HashMap<>();
		HashMap<Float, String> datosOrd = new HashMap<>();
		float sum=0;
		XQExpression xqe = conn.createExpression();
		String dist = "";	
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/Biblio_Prestecs/biblioteques-prestecs-"+year+".xml')//xml/biblioteques-prestecs\n"
			        + "let $districte := data($p/Districte)\n" 				       
			        + "let $var := data($p/Prestecs)\n"
			        + "return concat($districte,',',$var)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				
				while (xqrs.next()) {					
					String line = xqrs.getItemAsString(null);
				      String[] output = line.split(",");
				      if(dist.equals(output[0])){
				    	  sum=sum+Float.parseFloat(output[1]);
				      }else{				    	  
				    	  sum=Float.parseFloat(output[1]);
				      }
				     datos.put(output[0], sum);	
				     dist=output[0];
			     }				
			}
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/Biblio_Visites/biblioteques-visites-"+year+".xml')//xml/biblioteques-visites\n"
			        + "let $districte := data($p/Districte)\n" 				       
			        + "let $var := data($p/Visites)\n"
			        + "return concat($districte,',',$var)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				
				while (xqrs.next()) {					
					String line = xqrs.getItemAsString(null);
				      String[] output = line.split(",");
				      if(dist.equals(output[0])){
				    	  sum=sum+Float.parseFloat(output[1]);
				      }else{				    	  
				    	  sum=Float.parseFloat(output[1]);
				      }
				     dist=output[0];
				     datos1.put(output[0],sum);	
			     }				
			}
		
			for (Map.Entry<String, Float> entry : datos.entrySet()) {
				for (Map.Entry<String, Float> entry1 : datos1.entrySet()) {
					if(entry.getKey().equals(entry1.getKey())){
						datosOrd.put(entry.getValue()/entry1.getValue(), entry.getKey());						
					}
				}						  
			}
			Map<Float, String> treeMap = new TreeMap<Float, String>(datosOrd);
			return treeMap;
	}
	
	public static Map<Float, String> visitasInternet(String year) throws XQException{
		HashMap<String, Float> datos = new HashMap<>();
		HashMap<String, Float> datos1 = new HashMap<>();
		HashMap<Float, String> datosOrd = new HashMap<>();
		float sum=0;
		XQExpression xqe = conn.createExpression();
		String dist = "";	
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/Usos_Internet/biblioteques-usosinternet-"+year+".xml')//xml/biblioteques-usosinternet\n"
			        + "let $districte := data($p/Districte)\n" 				       
			        + "let $var := data($p/UsosInternetTotals)\n"
			        + "return concat($districte,',',$var)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				
				while (xqrs.next()) {					
					String line = xqrs.getItemAsString(null);
				      String[] output = line.split(",");
				      if(dist.equals(output[0])){
				    	  sum=sum+Float.parseFloat(output[1]);
				      }else{				    	  
				    	  sum=Float.parseFloat(output[1]);
				      }
				     datos.put(output[0], sum);	
				     dist=output[0];
			     }				
			}
			for (int j=0;j<=anos.length-1;j++){
				String cad1 = "for $p in doc('/db/GRUP5A/Biblio_Visites/biblioteques-visites-"+year+".xml')//xml/biblioteques-visites\n"
			        + "let $districte := data($p/Districte)\n" 				       
			        + "let $var := data($p/Visites)\n"
			        + "return concat($districte,',',$var)\n";
				
				XQResultSequence xqrs = xqe.executeQuery(cad1);
				
				while (xqrs.next()) {					
					String line = xqrs.getItemAsString(null);
				      String[] output = line.split(",");
				      if(dist.equals(output[0])){
				    	  sum=sum+Float.parseFloat(output[1]);
				      }else{				    	  
				    	  sum=Float.parseFloat(output[1]);
				      }
				     dist=output[0];
				     datos1.put(output[0],sum);	
			     }				
			}
		
			for (Map.Entry<String, Float> entry : datos.entrySet()) {
				for (Map.Entry<String, Float> entry1 : datos1.entrySet()) {
					if(entry.getKey().equals(entry1.getKey())){
						datosOrd.put(entry.getValue()/entry1.getValue(), entry.getKey());						
					}
				}						  
			}
			Map<Float, String> treeMap = new TreeMap<Float, String>(datosOrd);
			return treeMap;
		}
	
	public static void addBiblio(String b, String d, String p) throws Exception{

	    XQExpression xqe = conn.createExpression();
	    String cad = "update insert \n" +"<biblioteques-prestecs><Any>2015</Any>"+"<Ambit>Lletres</Ambit>"+"<Titularitat>Consorci o fundació amb presència municipal</Titularitat>"
	        +"<Equipament>"+b+"</Equipament>"  
	        +"<Districte>" + d +"</Districte><Prestecs>" + p
	        + "</Prestecs><Nota/></biblioteques-prestecs> \n" + "preceding doc('/db/GRUP5A/Biblio_Prestecs/biblioteques-prestecs-2015.xml')//xml/biblioteques-prestecs[1]";
	    
	    xqe.executeCommand(cad);
	    System.out.println("Biblioteca afegida.\n");
	    
	  }
	  
	  public static void editBiblio() throws Exception{
	    XQExpression xqe = conn.createExpression();
	    System.out.println("Edició del primer registre dels préstecs de 2015");

	    System.out.println("Introdueix el nou nom de la biblioteca: ");
	    String newName = br.readLine();
	    System.out.println("Introdueix el nou número de préstecs: ");
	    String prests = br.readLine();
	    

	    String s1 = "update value \n" + "doc('/db/GRUP5A/Biblio_Prestecs/biblioteques-prestecs-2015.xml')//xml/biblioteques-prestecs[1]/Equipament\n"
	    + "with '"+newName+"'";
	    xqe.executeCommand(s1);
	    String s2 = "update value \n" + "doc('/db/GRUP5A/Biblio_Prestecs/biblioteques-prestecs-2015.xml')//xml/biblioteques-prestecs[1]/Prestecs\n"
	        + "with '"+prests+"'";
	    xqe.executeCommand(s2);

	    System.out.println("\nValors del primer registre actualitzats");
	  }
	  
	  public static void deleteBiblio() throws Exception{
	    System.out.println("Esborrat d'un registre. Recomanem esborrar-ne el primer.");
	    System.out.println("Escriu el nom de la biblioteca a esborrar: ");
	    String name = br.readLine();
	    
	    String cad = "update delete \n" + "doc('/db/GRUP5A/Biblio_Prestecs/biblioteques-prestecs-2015.xml')"
	        + "//xml/biblioteques-prestecs[1]/Equipament[.='"+name+"']/.."; 
	      
	    XQExpression xqe = conn.createExpression();
	    xqe.executeCommand(cad);
	    System.out.println("Registre esborrat.");
	  }
	
	

}
