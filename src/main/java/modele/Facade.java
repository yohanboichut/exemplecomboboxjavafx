package modele;

import java.util.*;

public class Facade {


    private Map<Integer,Cheval> chevauxEnregistres;

    private Facade() {
        this.chevauxEnregistres = new HashMap<>();
    }

    public static Facade creer() {
        return new Facade();
    }

    public int ajouterCheval(String nom, int age) {
        Cheval nouveauCheval = Cheval.creer(nom, age);
        chevauxEnregistres.put(nouveauCheval.getIdCheval(),nouveauCheval);
        return nouveauCheval.getIdCheval();

    }

    public Cheval getChevalById(int id) throws ChevalNotFoundException {
        Cheval cheval =  this.chevauxEnregistres.get(id);
        if (Objects.isNull(cheval)) {
            throw new ChevalNotFoundException();
        }
        return cheval;
    }



    public Collection<Cheval> getChevaux() {
        return this.chevauxEnregistres.values();
    }


}
