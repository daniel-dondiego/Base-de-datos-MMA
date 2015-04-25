package mx.unam.ciencias.proyectoBDD;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import java.util.ArrayList;

public class Componentes{

	/**
	* Crea un titulo con caracteristicas determinadas (tamaño y color)
	* @param x la coordenada x
	* @param y la coordenada y
	* @param contenido el titulo
	* @return Text un objeto tipo Text con el titulo
	*/
	public Text titulo(int x,int y,String contenido){
		Text titulo = new Text(x,y,contenido);
			titulo.setFont(new Font(50));
			titulo.setFill(Color.WHITE);
		return titulo;
	}

	/**
	* Crea un subtitulo con caracteristicas determinadas (tamaño y color)
	* @param x la coordenada x
	* @param y la coordenada y
	* @param contenido el titulo
	* @return Text un objeto tipo Text con el titulo
	*/
	public Text subtitulo(int x,int y,String contenido){
		Text titulo = new Text(x,y,contenido);
			titulo.setFont(new Font(30));
			titulo.setFill(Color.WHITE);
		return titulo;
	}
	/**
	* Crea un etiqueta con caracteristicas determinadas (tamaño y color)
	* @param x la coordenada x
	* @param y la coordenada y
	* @param contenido el texto de la etiqueta
	* @return Text un objeto tipo Text con el etiqueta
	*/
	public Text etiqueta(int x,int y,String contenido){
		Text etiqueta = new Text(x,y,contenido);
			etiqueta.setFont(new Font(20));
			etiqueta.setFill(Color.WHITE);
		return etiqueta;
	}

	/**
	* Crea un campo de texto con una posición especificada
	* @param x la coordenada x
	* @param y la coordenada y
	* @return TextField el componente creado
	*/
	public TextField tF(int x,int y){
		TextField tF = new TextField(){
            @Override
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[0-9]")) {
                    super.replaceText(start, end, text);   
                }
            }
 
            @Override
            public void replaceSelection(String text) {
                if (!text.matches("[0-9]")) {
                    super.replaceSelection(text);
                }
            }
        };
			tF.relocate(x,y);
			tF.setPrefColumnCount(20);
		return tF;
	}

	/**
	* verifica si un componente es vacío
	* @param t el componente a verificar
	* @return <tt>true</tt> si el componente está vacío,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean componenteVacio(TextField t){
		return t.getText() == null && t.getText().isEmpty();
	}

	/**
	* verifica si un componente es vacío
	* @param t el componente a verificar
	* @return <tt>true</tt> si el componente está vacío,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean componenteVacio(TextArea t){
		return t.getText() == null && t.getText().isEmpty();
	}

	/**
	* verifica si un componente es vacío
	* @param cB el componente a verificar
	* @return <tt>true</tt> si el componente está vacío,
	*         <tt>false</tt> en otro caso.
	*/
	public boolean componenteVacio(ComboBox cB){
		return cB.getValue() == null && cB.getValue().toString().isEmpty();
	}	

}