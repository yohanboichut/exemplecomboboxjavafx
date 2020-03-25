package view;

import controleur.Controleur;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modele.ChevalNotFoundException;

public class VueCreationCheval implements Vue, ControleurAware {

    private Stage stage;
    private Scene scene;
    private Controleur controleur;

    private TextField nomCheval;
    private TextField ageCheval;


    private VueCreationCheval(Stage stage) {
        this.stage = stage;
        Label titre = new Label("Nouveau cheval");
        titre.setFont(Font.font(30));

        VBox vBox = new VBox();

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10d);
        Label nom = new Label("Nom du cheval");
        Label age = new Label("Age du cheval");

        this.ageCheval = new TextField();

        this.nomCheval = new TextField();

        Button valider = new Button("Créer");
        valider.setOnAction( e -> creerCheval(e));
        Button back = new Button("Back");
        back.setOnAction(e -> controleur.back());

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(15);
        hBox.getChildren().addAll(valider,back);
        vBox.getChildren().addAll(titre,nom,nomCheval,age,ageCheval,hBox);
        this.scene = new Scene(vBox);
    }


    private void creerCheval(Event e) {
        if (nomCheval.getText().length()==0 || ageCheval.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Les champs sont obligatoires !", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            try {
                controleur.creerCheval(nomCheval.getText(),Integer.parseInt(ageCheval.getText()));
            } catch (ChevalNotFoundException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Problème lors de la création du cheval !", ButtonType.OK);
                alert.showAndWait();
            }
        }

    }

    public static VueCreationCheval creer(Stage stage) {
        return new VueCreationCheval(stage);
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
