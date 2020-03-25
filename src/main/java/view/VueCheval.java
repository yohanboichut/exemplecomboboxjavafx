package view;

import controleur.Controleur;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.Cheval;

import java.util.Collection;

public class VueCheval implements Vue, ControleurAware {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    private TextField idCheval;
    private TextField nomCheval;
    private TextField ageCheval;


    private VueCheval(Stage stage) {
        this.stage = stage;
        Label titre = new Label("Le cheval");
        titre.setFont(Font.font(30));

        VBox vBox = new VBox();

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10d);
        Label id = new Label("Identifiant du cheval");
        Label nom = new Label("Nom du cheval");
        Label age = new Label("Age du cheval");

        this.idCheval = new TextField();
        this.idCheval.setDisable(true);
        this.ageCheval = new TextField();
        this.ageCheval.setDisable(true);
        this.nomCheval = new TextField();
        this.nomCheval.setDisable(true);

        Button back = new Button("Back");
        back.setOnAction(e -> controleur.back());

        vBox.getChildren().addAll(titre,id,idCheval,nom,nomCheval,age,ageCheval,back);
        this.scene = new Scene(vBox);
    }

    public static VueCheval creer(Stage stage) {
        return new VueCheval(stage);
    }

    /**
     * Initialisation des données -- récupération des données vers le contrôleur
     */

    public void initialisationDonnees() {

        Cheval cheval = this.controleur.getChevalSelectionne();
        this.nomCheval.setText(cheval.getNom());
        this.ageCheval.setText(String.valueOf(cheval.getAge()));
        this.idCheval.setText(String.valueOf(cheval.getIdCheval()));

    }



    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
