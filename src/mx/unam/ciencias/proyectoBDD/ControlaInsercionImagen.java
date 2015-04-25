package mx.unam.ciencias.proyectoBDD;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* Clase que controla la inserción de imágenes
*/
public class ControlaInsercionImagen{
	private Stage stage;
	private Path cFrom;
	private Path cTo;
	private String imCargada;
	private File selectedFile;

	/**
	* Constructor que inicializa los valores
	* @param stage el stage sobre el cual se mostrará el filechooser
	*/
	public ControlaInsercionImagen(Stage stage){
		this.stage = stage;
		cFrom = null;
		cTo = null;
		selectedFile = null;
		imCargada = "";
	}
	/**
	* carga en la vista la imagen seleccionada
	* @return String la url de la imagen cargada
	*/
	public String cargaImagen(){
		FileChooser fC = new FileChooser();
		fC.setTitle("Agregar imagen");
		fC.setInitialDirectory(new File(System.getProperty("user.home"))); 
		fC.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		 selectedFile = fC.showOpenDialog(stage);
		try{
			imCargada = selectedFile.getAbsolutePath();
			 if (selectedFile != null) {
				cFrom = Paths.get(selectedFile.getAbsolutePath().replace(selectedFile.getName(),""), selectedFile.getName());
				cTo = Paths.get("lib/imagenes", cFrom.getFileName().toString());
			 }
			 return imCargada;
		}catch(Exception e){
			return "";
		}
	}
	/**
	* agrega la imagen cargada en la vista
	*/
	public void agregaImagen(){
		try {
			      Files.copy(cFrom, cTo, REPLACE_EXISTING, COPY_ATTRIBUTES,
			          NOFOLLOW_LINKS);
			   
			    } catch (IOException e) {
			    	System.err.print(e);
			    }
	}
	/**
	* nos dice si hay una imagen cargada en la vista
	* @return <tt>true</tt> si hay una imagen cargada,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean bufferCargado(){
		return cFrom != null;
	}

	/**
	* regresa el nombre de la imagen agregada
	* @return String el nombre de la imagen agregada
	*/
	public String getNombreImagen(){
		return selectedFile.getName();
	}
}