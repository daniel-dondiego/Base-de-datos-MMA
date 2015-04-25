package mx.unam.ciencias.proyectoBDD;
import javafx.scene.layout.StackPane;
public class ControlaVisibilidades{

	StackPane sP1;
	StackPane sP2;
	StackPane sP3;
	StackPane sP4;
	/**
	* Constructor único que recibe los StackPane de los cuales controlará las visibilidades
	* @param sP1 el primer StackPane
	* @param sP2 el segundo StackPane
	* @param sP3 el tercer StackPane
	* @param sP4 el cuarto StackPane
	*/
	public ControlaVisibilidades(StackPane sP1,StackPane sP2,StackPane sP3,StackPane sP4){
		this.sP1 = sP1;
		this.sP2 = sP2;
		this.sP3 = sP3;
		this.sP4 = sP4;
	}

	/**
	* Hace visible solamente al primer StackPane agregado como primer parametro en el constructor
	*/
	public void principalSPVisible(){
		visibilidades(true,false,false,false);

	}

	/**
	* Hace visible solamente al segundo StackPane agregado como segundo parametro en el constructor
	*/
	public void eventosSPVisible(){
		visibilidades(false,true,false,false);

	}

	/**
	* Hace visible solamente al tercer StackPane agregado como tercer parametro en el constructor
	*/
	public void empresasSPVisible(){
		visibilidades(false,false,true,false);

	}

	/**
	* Hace visible solamente al cuarto StackPane agregado como cuarto parametro en el constructor
	*/
	public void peleadoresSPVisible(){
		visibilidades(false,false,false,true);

	}

	/**
	* Método auxiliar para las visibilidades
	* @param b1 la visibilidad de el primer componente
	* @param b2 la visibilidad de el segundo componente
	* @param b3 la visibilidad de el tercer componente
	* @param b4 la visibilidad de el cuarto componente
	*/
	private void visibilidades(boolean b1,boolean b2,boolean b3,boolean b4){
		sP1.setVisible(b1);
		sP2.setVisible(b2);
		sP3.setVisible(b3);
		sP4.setVisible(b4);
	}

}