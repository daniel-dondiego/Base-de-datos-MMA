package mx.unam.ciencias.proyectoBDD;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.*;
/**
 * Clase abtracta para las diferentes vistas de la interfaz gráfica
 * operaciones 
 */
public abstract class Vistas{
	protected Pane vistaBuscar;
	protected Pane vistaAgregar;
	protected Pane vistaModificar;
	protected Pane vistaEliminar;

	/**
	 * Constructor único que incializa los Pane.
	 */
	public Vistas(){
		vistaBuscar = new Pane();
		vistaAgregar = new Pane();
		vistaModificar = new Pane();
		vistaEliminar = new Pane();
	}

	/**
	* Permite crear una vista de la operación agregar correspondiente
	* @return Pane el Pane que contiene la vista para agregar
	*/
	public abstract Pane vistaBuscar();

	/**
	* Permite crear una vista de la operación agregar correspondiente
	* @param stage el stage para los filechooser
	* @return Pane el Pane que contiene la vista para agregar
	*/
	public abstract Pane vistaAgregar(Stage stage);

	/**
	* Permite crear una vista de la operación eliminar correspondiente
	* @return Pane el Pane que contiene la vista para eliminar
	*/
	public abstract Pane vistaEliminar();

	/**
	* Método que nos permite modificar las visibilidades dependiendo
	* de cuál Pane queremos visualizar
	* @param p1 el primer pane
	* @param p2 el segundo pane
	* @param p3 el tercer pane
	*/
	public void visibilidades(boolean p1, boolean p2, boolean p3){
		vistaBuscar.setVisible(p1);
		vistaAgregar.setVisible(p2);
		vistaEliminar.setVisible(p3);
	}

}