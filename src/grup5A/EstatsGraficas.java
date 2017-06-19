package grup5A;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.xquery.XQException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes")
public class EstatsGraficas extends Application {
	
	public static BarChart<String,Number> bc;
	
    @Override public void start(Stage stage) throws XQException, NumberFormatException, IOException {
    	GestorGrup5A.establirConnexio();
        stage.setTitle("Estadístiques");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Taula de Barres");
        xAxis.setLabel("Districte");       
        yAxis.setLabel("Valor");         
       
        Scene scene =switchScene(bc);
        stage.setScene(scene);
        stage.show();
    }

	public static Scene switchScene(final BarChart<String, Number> bc) throws XQException {
		 String[] anos={"2013","2014","2015"};   
		Scene scene = null;
		if(MainGrup5A.op1==1){
        	scene=showConsultasUser(anos, bc);
        }else if(MainGrup5A.op1==2){
        	scene=showPrestecsVisits(anos, bc);
        }
        else if(MainGrup5A.op1==3){
        	scene=showVisitInternet(anos, bc);
        }
		return scene;
	}

	@SuppressWarnings("unchecked")
	private static Scene  showConsultasUser(String[] anos,BarChart<String,Number> bc) throws XQException {
		 bc.setTitle("Consultes per Usuari");
		ArrayList<XYChart.Series> datos = new ArrayList<>();
		for (int i=0;i<anos.length;i++){
        	XYChart.Series series1 = new XYChart.Series();
            series1.setName(anos[i]); 
            Map<Float, String> treeMap = GestorGrup5A.consultasPorUsuario(anos[i]);
			for (Map.Entry<Float, String> entry : treeMap.entrySet()) {
				series1.getData().add(new XYChart.Data(entry.getValue(),entry.getKey()));
			}
			datos.add(series1);
        }
		Scene scene  = new Scene(bc,800,600);
        for (XYChart.Series xy:datos){
        	bc.getData().add(xy);
        }        
       
		return scene;
	}
	
	@SuppressWarnings("unchecked")
	private static Scene  showVisitInternet(String[] anos,BarChart<String,Number> bc) throws XQException {
		 bc.setTitle("Usos Internet per Visites");
		ArrayList<XYChart.Series> datos = new ArrayList<>();
		for (int i=0;i<anos.length;i++){
        	XYChart.Series series1 = new XYChart.Series();
            series1.setName(anos[i]); 
            Map<Float, String> treeMap = GestorGrup5A.visitasInternet(anos[i]);
			for (Map.Entry<Float, String> entry : treeMap.entrySet()) {
				series1.getData().add(new XYChart.Data(entry.getValue(),entry.getKey()));
			}
			datos.add(series1);
        }
		Scene scene  = new Scene(bc,800,600);
        for (XYChart.Series xy:datos){
        	bc.getData().add(xy);
        }        
       
		return scene;
	}
	
	@SuppressWarnings("unchecked")
	private static Scene  showPrestecsVisits(String[] anos,BarChart<String,Number> bc) throws XQException {
		 bc.setTitle("Prestecs per Visita");
		ArrayList<XYChart.Series> datos = new ArrayList<>();
		for (int i=0;i<anos.length;i++){
        	XYChart.Series series1 = new XYChart.Series();
            series1.setName(anos[i]); 
            Map<Float, String> treeMap = GestorGrup5A.prestamosVisitas(anos[i]);
			for (Map.Entry<Float, String> entry : treeMap.entrySet()) {
				series1.getData().add(new XYChart.Data(entry.getValue(),entry.getKey()));
			}
			datos.add(series1);
        }
		Scene scene  = new Scene(bc,800,600);
        for (XYChart.Series xy:datos){
        	bc.getData().add(xy);
        }       
		return scene;
	}
 
    public static void main(String[] args) {
        Application.launch(args);
    }
}