package grup5A;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuGrup5A {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static int menu() throws Exception, IOException{
		int op = 0;
		System.out.println("========================================");
		System.out.println("\tOPEN DATA BIBLIOTEQUES");
		System.out.println("========================================");
		System.out.println("\n1) Mostrar totes les dades. ");
		System.out.println("2) Mostrar totes les dades d'un any determinat.");
		System.out.println("3) Llistat de fitxers.");
		System.out.println("4) Consultar dades per districte.");
		System.out.println("5) Estadístiques.");
		System.out.println("6) Estadístiques Gràfiques");
		System.out.println("7) Afegir registre nou.");
		System.out.println("8) Modificar registre.");
		System.out.println("9) Eliminar registre.");
		System.out.println("0) Sortir.");
		System.out.println("Selecciona la teva opció: ");
		op = Integer.parseInt(br.readLine());
		return op;
	}	
	
	public static int menuEstat() throws NumberFormatException, IOException {
		
		int op = 0;
		System.out.println("========================================");
		System.out.println("\tESTADISTIQUES BIBLIOTEQUES");
		System.out.println("========================================");
		System.out.println("\n1) Mitjana de Consultes/Usuari. ");
		System.out.println("2) Mitjana de Préstecs/Visites.");
		System.out.println("3) Mitjana de Visites/Usos d'Internet.");
		System.out.println("0) Sortir.");
		System.out.println("Selecciona la teva opció: ");
		op = Integer.parseInt(br.readLine());
		return op;
		
		
	}
}