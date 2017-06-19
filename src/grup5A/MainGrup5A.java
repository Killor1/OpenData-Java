package grup5A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.xml.xquery.XQException;

public class MainGrup5A {
	public static int op1=6;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int op = 0;
		
	     
		GestorGrup5A.establirConnexio();
		
		do{
			op = MenuGrup5A.menu();
			switch(op){
				case 1:
					GestorGrup5A.showAllData();
					break;
				case 2:
					System.out.println("Introdueix l'any del que vols saber les dades (2013/2014/2015):");
					String year = br.readLine();
					GestorGrup5A.showAllDataByYear(year);
					break;
				case 3:
					System.out.println("Categories:");
					for(int i = 1; i < GestorGrup5A.carpetas.length; ++i){
						System.out.println(i+") "+GestorGrup5A.carpetas[i-1]);
					}
					System.out.println("Selecciona la categoria: ");
					int sel = Integer.parseInt(br.readLine());
					GestorGrup5A.showFilesInFolderByYear(sel);
					break;
				case 4:
					GestorGrup5A.loadDistricNames();
					System.out.println("\n-----------------------\nSelecciona el districte del que vols saber les dades[Codi o Nom del districte]: ");
					String distSel = br.readLine();
					GestorGrup5A.showByDistrict(distSel);
					break;
				case 5:
					estadMenu();					
					break;
				case 6:
					estadMenugraf();
					break;
				case 7:
					  System.out.println("\nInserció de dades d'una biblioteca i els seus préstecs de l'any 2015.");
			          System.out.println("---------------------------------------------------------------------------");
			          System.out.println("Posa el nom de la biblioteca: ");
			          String bibl = br.readLine();
			          System.out.println("Selecció de districte");
			          /*for (int i = 0; i < distritos.size(); ++i) {
			            System.out.println(distritos);
			          }*/
			          System.out.println("Escull el districte on és la biblioteca. Ex: (01. Ciutat Vella) ");
			          String dist = br.readLine();
			          System.out.println("Introdueix el número de prèstecs: ");
			          String prest = br.readLine();
			          
			          GestorGrup5A.addBiblio(bibl, dist, prest);
					break;
				case 8:
					 GestorGrup5A.editBiblio();
					break;
				case 9:
					GestorGrup5A.deleteBiblio();
					break;
				case 0:
					System.out.println("Sortint de l'aplicació.");
					break;
				default:
					System.out.println("Opció incorrecta.");
			}
		}while(op != 0);
	}

	private static void estadMenu() throws IOException, XQException {
		int op1=6;
		while(op1!=0){
			op1=MenuGrup5A.menuEstat();
			if (op1 == 1){
				System.out.println("Introdueix l'any del que vols saber les dades (2013/2014/2015):");
				String year1 = br.readLine();
				
				Map<Float, String> treeMap = GestorGrup5A.consultasPorUsuario(year1);
				for (Map.Entry<Float, String> entry : treeMap.entrySet()) {
					System.out.println("Districte: "+entry.getValue()+" Mitjana: "+entry.getKey());
				}
			}else if (op1==2){
				System.out.println("Introdueix l'any del que vols saber les dades (2013/2014/2015):");
				String year1 = br.readLine();
				
				Map<Float, String> treeMap = GestorGrup5A.prestamosVisitas(year1);
				for (Map.Entry<Float, String> entry : treeMap.entrySet()) {
					System.out.println("Districte: "+entry.getValue()+" Mitjana: "+entry.getKey());
				}
			}else if (op1==3){
				System.out.println("Introdueix l'any del que vols saber les dades (2013/2014/2015):");
				String year1 = br.readLine();

				Map<Float, String> treeMap = GestorGrup5A.visitasInternet(year1);
				for (Map.Entry<Float, String> entry : treeMap.entrySet()) {
					System.out.println("Districte: "+entry.getValue()+" Mitjana: "+entry.getKey());
				}
			}
		}
	}
	
	private static void estadMenugraf() throws NumberFormatException, IOException{
		op1=MenuGrup5A.menuEstat();
		
			new Thread() {
	            @Override
	            public void run() {
	            	try{
	            		javafx.application.Application.launch(EstatsGraficas.class);
	            	}catch(IllegalStateException e){
	            		System.out.println("/n");
	            		System.out.println("solo se pueden mostrar unas estadisticas graficas por ejecucion...");
	            		System.out.println("Selecciona la teva opció:");
	            	}	                	                
	            }
	        }.start();
		}		
	}


