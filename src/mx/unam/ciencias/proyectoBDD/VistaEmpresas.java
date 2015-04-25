package mx.unam.ciencias.proyectoBDD;

import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.sql.*;

/**
* Clase para la visualización de empresas
*/
public class VistaEmpresas extends Vistas{
	private Componentes c;
	private ConexionABase conn;
	private Notificaciones not;

	/**
	* Constructor único que no recibe parámetros
	*/
	public VistaEmpresas(){
		c = new Componentes();
		conn = new ConexionABase();
		not = new Notificaciones();
	}

	/**
	* Crea una vista de la operación buscar empresa
	* @return Pane el Pane que contiene la vista para buscar una empresa
	*/
	public Pane vistaBuscar(){
		visibilidades(true,false,false);
		vistaBuscar.getChildren().addAll();
		
		return vistaBuscar;
	}

	/**
	* Crea una vista de la operación agregar empresa
	* @param stage el stage sobre el cual se ejecutará el filechooser
	* @return Pane el Pane que contiene la vista para agregar una empresa
	*/
	public Pane vistaAgregar(Stage stage){
		visibilidades(false,true,false);

		TextField nombreTF = c.tF(300,80);

		TextArea descripcionTA = new TextArea();
			descripcionTA.relocate(100,160);
			descripcionTA.setWrapText(true);
			descripcionTA.setPrefSize(380,150);

		ComboBox<Integer> fundacionCB = new ComboBox<Integer>();
			fundacionCB.relocate(300,330);
			for (int i=1800;i<2015;i++){
				fundacionCB.getItems().add(i);
			}

		TextField sitioWebTF = c.tF(300,380);

		ControlaInsercionImagen cII = new ControlaInsercionImagen(stage);

		Button agregarFoto = new Button();
			Image agregarFIm = new Image("file:lib/imagenes/agregarImagen.png",150,200,true,true);
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
			guardar.relocate(550,450);
			guardar.setOnAction((ActionEvent a) -> {
				if(cII.bufferCargado()){
					if(!c.componenteVacio(nombreTF)&&!c.componenteVacio(descripcionTA)&&!c.componenteVacio(fundacionCB)&&
						!c.componenteVacio(sitioWebTF)){
						Empresa emp = new Empresa(nombreTF.getText(),descripcionTA.getText(),fundacionCB.getValue(),sitioWebTF.getText(),cII.getNombreImagen());
						try{
							cII.agregaImagen();
						if(conn.agregarEmpresa(emp)){
							not.notificacion(String.format("Se ha agregado %s a la base de datos correctamente",nombreTF.getText()));
						}
						}catch(Exception e){

						}
						
					}
				}
				else
					System.out.println("seleciona una imagen");
			});
		
		vistaAgregar.getChildren().addAll(c.titulo(200,50,"Nueva Empresa"),
			c.etiqueta(100,100,"Nombre: "),c.etiqueta(100,150,"Descripción: "),
			c.etiqueta(100,350,"Año de fundación: "),c.etiqueta(100,400,"Sitio Web: "),
			nombreTF,descripcionTA,fundacionCB,sitioWebTF,agregarFoto,guardar);
		return vistaAgregar;
	}


	/**
	* Crea una vista de la operación eliminar empresa
	* @return Pane el Pane que contiene la vista para eliminar una empresa
	*/
	public Pane vistaEliminar(){
		visibilidades(false,false,true);
		Button b = new Button("adios");
		vistaEliminar.getChildren().addAll(b);
		
		return vistaEliminar;
	}
	
}