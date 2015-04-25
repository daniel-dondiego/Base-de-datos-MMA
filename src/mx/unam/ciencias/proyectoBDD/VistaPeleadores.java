package mx.unam.ciencias.proyectoBDD;

import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;
import javafx.scene.chart.*;
import java.sql.*;

/**
* Clase para la visualizacion de peleadores
*/
public class VistaPeleadores extends Vistas{
	private Componentes c;
	private Peleador p;
	private ConexionABase conn;
	private CreaPaginas creaP;
	private ArrayList<Peleador> listaP;
	private Pagination pag;
	private Peleador fighter;
	private int seleccion1;
	private String seleccion2;
	private Notificaciones not;

	/**
	* Constructor único que no recibe parametros
	*/
	public VistaPeleadores(){
		c = new Componentes();
		p = new Peleador();
		conn = new ConexionABase();
		creaP = new CreaPaginas();
		not = new Notificaciones();
		listaP = null;
	}

	/**
	* Crea una vista de la operación buscar peleadores
	* @return Pane el Pane que contiene la vista para buscar una peleadores
	*/
	public Pane vistaBuscar(){
		visibilidades(true,false,false);
		Pane pane = new Pane();
		ArrayList<Integer> edades = null;
		ArrayList<String> divisiones = null;
		final ComboBox<String> sp = new ComboBox<String>();
			sp.setVisible(false);
		final ComboBox<Integer> edadesCB = new ComboBox<Integer>();
		try{
			divisiones = conn.getDivisiones();
			edades = conn.getEdades();
			
		}catch(Exception e){

		}
		ComboBox<String> divisionesCBF = new ComboBox<String>();
			divisionesCBF.relocate(150,280);

			edadesCB.relocate(150,180);
			for (Integer i : edades) {
				edadesCB.getItems().add(i);
			}
			edadesCB.setPrefWidth(100);
			edadesCB.setOnAction((ActionEvent a) -> {
				if(edadesCB.getValue() != null && !edadesCB.getValue().toString().isEmpty()){
					seleccion1 = edadesCB.getValue();
					try{
						ArrayList<String> divisionesF = conn.getDivisiones(seleccion1);
							for (String s : divisionesF) {
								divisionesCBF.getItems().add(s);
							}
							
					}catch(Exception e){

					}

				}

			});

			divisionesCBF.setPrefWidth(200);
			divisionesCBF.setOnAction((ActionEvent b) -> {
				if(divisionesCBF.getValue() != null && !divisionesCBF.getValue().toString().isEmpty()){
					seleccion2 = divisionesCBF.getValue();
				}
			});

			GridPane gridpane = new GridPane();

			ScrollPane scrollP = new ScrollPane();
				scrollP.relocate(360,100);
				scrollP.setPrefSize(520,350);
				scrollP.setContent(gridpane);
				
			Button bBuscar = new Button("Buscar");
				bBuscar.relocate(400,500);
				bBuscar.setOnAction((ActionEvent a) -> {
					try{
						sp.getItems().removeAll();
						if(seleccion2 == null){
							listaP = conn.getPeleadores();
						}else{
							listaP = conn.filtradoPorEdadDiv(seleccion1,seleccion2);
						}
							int i=1;
							for(Peleador p1 : listaP){
								Pane pan1 = new Pane();
								Text t = new Text(p1.getNombre()+" " +p1.getApellido());
								t.relocate(10,50);
								Image ima = new Image("file:lib/imagenes/"+ p1.getUrlImagen(),200,500,true,true);
								ImageView im = new ImageView(ima);
									im.relocate(300,20);
								ObservableList <PieChart.Data> pieChartData =
										                FXCollections.observableArrayList(
										                new PieChart.Data("Peleas ganadas", p1.getGanadas()),
										                new PieChart.Data("Peleas perdidas", p1.getPerdidas()),
										                new PieChart.Data("Peleas empatadas", p1.getEmpatadas()));
										final PieChart chart = new PieChart(pieChartData);
											chart.setTitle("Record");
											chart.setLabelLineLength(5);
											chart.relocate(10,100);
											chart.setPrefSize(200,200);
									pan1.getChildren().addAll(im,chart,t);
								GridPane.setConstraints(pan1, 0, i++);
								gridpane.getChildren().add(pan1);
							}		
						}catch(Exception e){
	
					}
				});


			sp.setPrefWidth(200);
				sp.relocate(400,200);
			
		vistaBuscar.getChildren().addAll(c.titulo(200,50,"Buscar peleador"),c.etiqueta(30,100,"Filtrar por: "),
			c.etiqueta(30,200,"Edad: "),c.etiqueta(30,300,"División: "),edadesCB,pane,sp,bBuscar,scrollP,divisionesCBF);
		return vistaBuscar;
	}

	/**
	* Crea una vista de la operación agregar peleadores
	* @param stage el stage sobre el cual se mostrará el fileChooser
	* @return Pane el Pane que contiene la vista para agregar una peleadores
	*/
	public Pane vistaAgregar(Stage stage){
		visibilidades(false,true,false);
		TextField nombreTF = c.tF(30,130);
			nombreTF.setPromptText("Ingrese el nombre del peleador");
		TextField apodoTF = c.tF(30,180);
			apodoTF.setPromptText("Ingrese el apodo del peleador");
		TextField apellidoTF = c.tF(30,230);
			apellidoTF.setPromptText("Ingrese el apellido del peleador");
		TextField nacionalidadTF = c.tF(30,280);
			nacionalidadTF.setPromptText("Ingrese la nacionalidad del peleador");

		ComboBox<String> divisiones = new ComboBox<String>(p.getDivisionesDePeso());
			divisiones.relocate(135,390);
		ComboBox<Integer> edad = new ComboBox<Integer>();
			edad.relocate(135,340);
		for (Integer i=18;i<56;i++){
			edad.getItems().add(i);
		}
		ComboBox<Integer> pGanadas = new ComboBox<Integer>();
			pGanadas.relocate(520,130);
		ComboBox<Integer> pPerdidas = new ComboBox<Integer>();
			pPerdidas.relocate(520,180);
		ComboBox<Integer> pEmpatadas = new ComboBox<Integer>();
			pEmpatadas.relocate(520,230);
		for (Integer j=0;j<201;j++){
			pGanadas.getItems().add(j);
			pPerdidas.getItems().add(j);
			pEmpatadas.getItems().add(j);
		}

		ControlaInsercionImagen cII = new ControlaInsercionImagen(stage);

		Button agregarFoto = new Button();
			Image agregarFIm = new Image("file:lib/imagenes/agregarFoto.png",150,200,true,true);
			agregarFoto.setGraphic(new ImageView(agregarFIm));
			agregarFoto.relocate(630,150);
			agregarFoto.setOnAction((ActionEvent a) -> {
				
					String strIm = cII.cargaImagen();
				if(cII.bufferCargado()){
					Image cargaI = new Image("file:"+strIm,250,300,true,true);
					agregarFoto.setGraphic(new ImageView(cargaI));
				}
			});

			Button guardar = new Button();
				Image guardarIm = new Image("file:lib/imagenes/guardar.png",50,50,true,true);
				guardar.setGraphic(new ImageView(guardarIm));
				guardar.relocate(550,400);
				guardar.setOnAction((ActionEvent a) -> {
					if(cII.bufferCargado()){
						if(!c.componenteVacio(nombreTF)&&!c.componenteVacio(apellidoTF)&&!c.componenteVacio(nacionalidadTF)&&
							!c.componenteVacio(divisiones)&&!c.componenteVacio(pGanadas)&&!c.componenteVacio(pPerdidas)&&
							!c.componenteVacio(pEmpatadas)&&!c.componenteVacio(edad)){
							if(c.componenteVacio(apodoTF)){
								fighter = new Peleador(nombreTF.getText(),apellidoTF.getText(),nacionalidadTF.getText(),
														edad.getValue(),divisiones.getValue().toString(),pGanadas.getValue(),
														pPerdidas.getValue(),pEmpatadas.getValue(),cII.getNombreImagen());			
							}else{
								fighter = new Peleador(nombreTF.getText(),apodoTF.getText(),apellidoTF.getText(),nacionalidadTF.getText(),
														edad.getValue(),divisiones.getValue().toString(),pGanadas.getValue(),
														pPerdidas.getValue(),pEmpatadas.getValue(),cII.getNombreImagen());
							}
							try{
							if(conn.agregaPeleador(fighter)){
								cII.agregaImagen();
								not.notificacion(String.format("Se ha agregado a %s a la base de datos correctamente",nombreTF.getText()));
							}
							}catch(Exception e){

							}
							
						}
					}
					else
						System.out.println("seleciona una imagen");
				});

		vistaAgregar.getChildren().addAll(c.titulo(200,50,"Nuevo Peleador"),c.subtitulo(30,110,"Datos personales"),
			c.subtitulo(400,100,"Carrera"),c.etiqueta(30,360,"Edad: "),c.etiqueta(30,410,"División: "),
			c.etiqueta(320,150,"Peleas ganadas: "),c.etiqueta(320,200,"Peleas perdidas: "),
			c.etiqueta(320,250,"Peleas empatadas: "),c.etiqueta(650,100,"Fotografía:"),
			nombreTF,apodoTF,apellidoTF,nacionalidadTF,edad,divisiones,agregarFoto,pGanadas,pPerdidas,pEmpatadas,guardar);
		return vistaAgregar;
	}

	/**
	* Crea una vista de la operación eliminar peleadores
	* @return Pane el Pane que contiene la vista para eliminar una peleadores
	*/
	public Pane vistaEliminar(){
		visibilidades(false,false,true);
		Button b = new Button("adios");
		vistaEliminar.getChildren().addAll(b);
		
		return vistaEliminar;
	}

}