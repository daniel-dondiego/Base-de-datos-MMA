package mx.unam.ciencias.proyectoBDD;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


/*
*<p> Clase main </p>
*/
public class Vista extends Application{
	public static void main(String [] args){

		launch(args);

	}

		private Pagination pagination;
		private Controlador controlador = new Controlador();
		private CreaPaginas cPag = new CreaPaginas();
		
		@Override public void start(Stage stage) {	

			Group root = new Group();
			Image icono = new Image("file:lib/imagenes/mmaIcono.png");
			stage.getIcons().add(icono);
				stage.setTitle("Base de datos MMA");
				Scene scene = new Scene(root, 900, 590);
				BorderPane principalBP = new BorderPane();
				StackPane principal = new StackPane();
				stage.setResizable(false);
				
				//panes
			StackPane paneEmpresas = new StackPane();
				final Image fondoAgregarEmpresa = new Image("file:lib/imagenes/fondoEmpresas.jpg");
				final ImageView imEmp = new ImageView(fondoAgregarEmpresa);
				VistaEmpresas vE = new VistaEmpresas();
				paneEmpresas.getChildren().addAll(imEmp,vE.vistaAgregar(stage),vE.vistaEliminar());
				paneEmpresas.setVisible(false);

			StackPane paneEventos = new StackPane();
				final Image fondoEvento = new Image("file:lib/imagenes/fondoEventos.jpg");
				final ImageView imEv = new ImageView(fondoEvento);
				VistaEventos vEv = new VistaEventos();
				paneEventos.getChildren().addAll(imEv,vEv.vistaAgregar(stage),vEv.vistaEliminar());
				paneEventos.setVisible(false);

			StackPane panePeleadores = new StackPane();
				final Image fondoPeleador = new Image("file:lib/imagenes/fondoPeleadores.jpg");
				final ImageView imPel = new ImageView(fondoPeleador);
				VistaPeleadores vPel = new VistaPeleadores();
				panePeleadores.getChildren().addAll(imPel,vPel.vistaBuscar(),vPel.vistaAgregar(stage),vPel.vistaEliminar());
				panePeleadores.setVisible(false);

			final MenuBar menuPrincipal = new MenuBar();
				menuPrincipal.setStyle("-fx-background-color: #453f3f;");
				principalBP.setTop(menuPrincipal);
				ControlaVisibilidades controlaV = new ControlaVisibilidades(principal,paneEventos,paneEmpresas,panePeleadores);
				final Menu inicio = new Menu("inicio");
					final MenuItem ini = new MenuItem("volver");
						ini.setOnAction((ActionEvent e) -> {
							controlaV.principalSPVisible();
							principalBP.setCenter(principal);
						});
					inicio.getItems().add(ini);
				final Peleador fighters = new Peleador();

					final Menu peleadoresM = new Menu("Peleadores");
							final MenuItem buscaPeleador = new MenuItem("Buscar peleador");
								buscaPeleador.setOnAction((ActionEvent a) -> {
									controlaV.peleadoresSPVisible();
									vPel.vistaBuscar();
									principalBP.setCenter(panePeleadores);
								});
							final MenuItem agregarPeleador = new MenuItem("Agregar peleador");
								agregarPeleador.setOnAction((ActionEvent a) -> {
									controlaV.peleadoresSPVisible();
									vPel.vistaAgregar(stage);
									principalBP.setCenter(panePeleadores);
								});
							
						peleadoresM.getItems().addAll(buscaPeleador,agregarPeleador);

					final Menu empresasM = new Menu("Empresas");
						
						final MenuItem agregarEmpresa = new MenuItem("Agregar Empresa");
							agregarEmpresa.setOnAction((ActionEvent a) -> {
								controlaV.empresasSPVisible();
								vE.vistaAgregar(stage);
								principalBP.setCenter(paneEmpresas);
							});
						
						final MenuItem eliminarEmpresa = new MenuItem("Eliminar Empresa");
							eliminarEmpresa.setOnAction((ActionEvent c) -> {
								controlaV.empresasSPVisible();
								vE.vistaEliminar();
								principalBP.setCenter(paneEmpresas);
							});
						empresasM.getItems().addAll(agregarEmpresa,eliminarEmpresa);


					final Menu eventosM = new Menu("Eventos");	
						final MenuItem agregarEvento = new MenuItem("Agregar Evento");
							agregarEvento.setOnAction((ActionEvent t) -> {
								controlaV.eventosSPVisible();
								vEv.vistaAgregar(stage);
								principalBP.setCenter(paneEventos);

							});
						final MenuItem eliminarEvento = new MenuItem("Eliminar Evento");
							eliminarEvento.setOnAction((ActionEvent t1) -> {
								controlaV.eventosSPVisible();
								vEv.vistaEliminar();
								principalBP.setCenter(paneEventos);

							});	
						eventosM.getItems().addAll(agregarEvento,eliminarEvento);	
						
				menuPrincipal.getMenus().addAll(inicio,peleadoresM,empresasM,eventosM);



				pagination = new Pagination(4);
				pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
				ArrayList<String> arrIm = new ArrayList<>();
				arrIm.add("cainVelasquez.jpg");
				arrIm.add("mma1.jpg");
				arrIm.add("mma2.jpg");
				arrIm.add("mma3.jpg");

				pagination.setPageFactory((Integer pageIndex) -> cPag.createPageImage(arrIm,pageIndex));
				
				final Image fondo = new Image("file:lib/imagenes/mmaFondo.png");
				final ImageView im = new ImageView(fondo);
				principal.getChildren().addAll(im,pagination);
				principalBP.setCenter(principal); 

				stage.setOnCloseRequest((t) -> {
					try{
						controlador.cierraConexion();
					}catch(Exception e){
					}
				});

				root.getChildren().addAll(principalBP); 
				stage.setScene(scene);
				stage.show();
		}

}