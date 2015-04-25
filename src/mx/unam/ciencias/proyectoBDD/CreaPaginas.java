package mx.unam.ciencias.proyectoBDD;

import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
/**
* Clase que crea paginas para mostrarse en la vista
*/
public class CreaPaginas{
    
    /**
    * crea paginas para usarse en el componente pagination
    * @param arL el arreglo con los nombres de las imágenes
    * @param pageIndex el indice de la página
    * @return StackPane la página hecha
    */
	public StackPane createPageImage(ArrayList<String> arL,int pageIndex) {

        StackPane sP = new StackPane();

        final Image imagen = new Image("file:lib/imagenes/"+ arL.get(pageIndex));
            final ImageView im = new ImageView(imagen);
            sP.getChildren().add(im);
      
        return sP;
    }

}