package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;
import modele.Facade;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{

        // init du modele

        Facade facade = Facade.creer();
        facade.ajouterCheval("Jolly Jumper",60);
        facade.ajouterCheval("Silas",35);
        facade.ajouterCheval("Tornado",35);


        Controleur monControleur = new Controleur(primaryStage,facade);
        monControleur.run();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
