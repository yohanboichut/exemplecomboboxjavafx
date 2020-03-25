package view;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import modele.Cheval;
import modele.ChevalNotFoundException;

import java.util.Collection;
import java.util.Objects;

public class VueChevaux implements Vue, ControleurAware {

    private Stage stage;
    private Scene scene;
    private ComboBox<Cheval> chevalComboBox;
    private Controleur controleur;


    private VueChevaux(Stage stage) {
        this.stage = stage;
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        this.chevalComboBox = new ComboBox<>();


        Button afficher = new Button("Afficher");
        afficher.setOnAction(e -> selectionner());

        hBox.getChildren().addAll(chevalComboBox,afficher);
        borderPane.setCenter(hBox);

        Label titre = new Label("Les chevaux");
        titre.setFont(Font.font(30));

        borderPane.setTop(titre);
        Button creerCheval = new Button("Ajouter un cheval");


        creerCheval.setOnAction(e -> controleur.creationCheval());
        borderPane.setBottom(creerCheval);
        BorderPane.setAlignment(creerCheval,Pos.CENTER);
        this.scene = new Scene(borderPane);
    }

    private void selectionner() {
        Cheval cheval = this.chevalComboBox.getSelectionModel().getSelectedItem();
        if (Objects.isNull(cheval)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez sélectionner un cheval !!!", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            try {
                this.controleur.afficherCheval(cheval.getIdCheval());
            } catch (ChevalNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le cheval sélectionné n'existe pas", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    public static VueChevaux creer(Stage stage) {
        return new VueChevaux(stage);
    }

    /**
     * Initialisation des données -- récupération des données vers le contrôleur
     */

    public void initialisationDonnees() {
        Collection<Cheval> lesChevaux = this.controleur.getChevaux();
        this.chevalComboBox.setItems(FXCollections.observableArrayList(lesChevaux));
        this.chevalComboBox.setConverter(new StringConverter<Cheval>() {
            @Override
            public String toString(Cheval cheval) {
                return Objects.isNull(cheval)?"":cheval.getIdCheval()+" - "+ cheval.getNom();
            }

            @Override
            public Cheval fromString(String s) {
                throw new RuntimeException();
            }
        });

        if (lesChevaux.size() > 0) {
            this.chevalComboBox.getSelectionModel().selectFirst();
        }
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
