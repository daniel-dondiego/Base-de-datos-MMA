package mx.unam.ciencias.proyectoBDD;

import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.sql.*;

/**
* Clase para la visualización de eventos
*/
public class VistaEventos extends Vistas{
	private Componentes c;
	private Notificaciones not;
	private ConexionABase conn;

	public VistaEventos(){
		c = new Componentes();
		not = new Notificaciones();
		conn = new ConexionABase();
	}

	/**
	* Crea una vista de la operación buscar eventos
	* @return Pane el Pane que contiene la vista para buscar eventos
	*/
	public Pane vistaBuscar(){
		visibilidades(true,false,false);
		Button b = new Button("como te va?");
		vistaBuscar.getChildren().addAll(b);
		
		return vistaBuscar;
	}

	/*
	* Crea una vista de la operación agregar un evento
	* @return Pane el Pane que contiene la vista para agregar eventos
	*/
	public Pane vistaAgregar(Stage stage){
		visibilidades(false,true,false);

		TextField nombreTF = c.tF(450,80);

		TextField lugarTF = c.tF(450,130);

		DatePicker fechaDP = new DatePicker();
			fechaDP.relocate(100,230);
			fechaDP.setEditable(false);

		Button guardar = new Button();
			Image guardarIm = new Image("file:lib/imagenes/guardar.png",50,50,true,true);
			guardar.setGraphic(new ImageView(guardarIm));
			guardar.relocate(550,450);
			guardar.setOnAction((ActionEvent a) -> {
				if(!c.componenteVacio(nombreTF)&&!c.componenteVacio(lugarTF)){
					Evento event = new Evento(Integer.parseInt(fechaDP.getValue().toString().substring(8,10)),Integer.parseInt(fechaDP.getValue().toString().substring(5,7)),Integer.parseInt(fechaDP.getValue().toString().substring(0,4)),nombreTF.getText(),lugarTF.getText());
					try{
						if(conn.agregarEvento(event)){
							not.notificacion(String.format("Se ha agregado el evento %s a la base de datos correctamente",nombreTF.getText()));
						}
					}catch(Exception e){

					}
						
				}
				
				else
					System.out.println(fechaDP.getValue());
			});

		vistaAgregar.getChildren().addAll(c.titulo(200,50,"Nueva Evento"),
			c.etiqueta(100,100,"Nombre del evento: "),
			c.etiqueta(100,150,"Lugar del evento(País o ciudad): "),
			c.etiqueta(200,200,"Fecha: "),nombreTF,lugarTF,fechaDP,guardar);
		return vistaAgregar;
	}


	/**
	* Crea una vista de la operación eliminar eventos
	* @return Pane el Pane que contiene la vista para eliminar eventos
	*/
	public Pane vistaEliminar(){
		visibilidades(false,false,true);
		Button b = new Button("adios");
		vistaEliminar.getChildren().addAll(b);
		
		return vistaEliminar;
	}

}