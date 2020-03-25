package controleur;


import javafx.stage.Stage;
import modele.Cheval;
import modele.ChevalNotFoundException;
import modele.Facade;
import view.VueCheval;
import view.VueChevaux;
import view.VueCreationCheval;

import java.util.Collection;

/**
 * Created by utilisateur on 07/03/2019.
 */
public class Controleur {

    private Facade facade;
    private Stage stage;
    private VueChevaux vueChevaux;
    private VueCheval vueCheval;
    private Cheval chevalSelectionne;
    private VueCreationCheval vueCreationCheval;


    public Controleur(Stage primaryStage, Facade facade) {
        this.stage = primaryStage;
        this.facade = facade;
        this.vueCreationCheval = VueCreationCheval.creer(this.stage);
        this.vueChevaux = VueChevaux.creer(this.stage);
        this.vueCheval = VueCheval.creer(this.stage);
        this.vueCreationCheval.setControleur(this);
        this.vueChevaux.setControleur(this);
        this.vueChevaux.initialisationDonnees();
        this.vueCheval.setControleur(this);


    }


    public void run() {
        this.vueChevaux.show();

    }

    public Collection<Cheval> getChevaux() {
        return facade.getChevaux();
    }

    public void afficherCheval(int idCheval) throws ChevalNotFoundException {
        this.chevalSelectionne = this.facade.getChevalById(idCheval);
        this.vueCheval.initialisationDonnees();
        this.vueCheval.show();
    }

    public Cheval getChevalSelectionne() {
        return this.chevalSelectionne;
    }

    public void back() {
        this.vueChevaux.show();
    }

    public void creationCheval()  {
        vueCreationCheval.show();
    }


    public void creerCheval(String nom, int age) throws ChevalNotFoundException {
        this.facade.ajouterCheval(nom, age);
        this.vueChevaux.initialisationDonnees();
        this.vueChevaux.show();
    }
}
