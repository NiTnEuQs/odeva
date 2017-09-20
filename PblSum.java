import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Antho on 05/12/16.
 */
public class PblSum extends PblDec {
    private int [] entiers;
    private int cible;
    private int nbSousEnsemble;
    private int nbEntiers;

    public PblSum(int[] entiers, int cible, int nbEntiers) {
        super();
        this.entiers = entiers;
        this.cible = cible;
        this.nbEntiers = nbEntiers;
    }

    public PblSum(String fileName) {
        try {
            BufferedReader donnee  //le fichier qui contient les données du pb
                    = new BufferedReader (new FileReader(fileName));
            this.nbEntiers = Integer.parseInt(donnee.readLine());
            this.entiers = new int[nbEntiers];
            for (int i=0;i< this.nbEntiers;i++) this.entiers[i]=Integer.parseInt(donnee.readLine());

            this.cible = Integer.parseInt(donnee.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PblPartition reduction(){
        int somme = 0;
        for(int i = 0; i < nbEntiers; i++) {
            somme += entiers[i];
        }
        int nbEntiers2 = this.nbEntiers + 1;

        int entiers2[] = new int[nbEntiers2];

        int elementRemplissageSacDeux = 0;

        //Pour réduire dans partition on cherche avoir deux sous ensemble égaux
        //Si la cible * 2 est supérieur a la somme des éléments il faut ajouter un objet
        //de poids 2 * cible - somme
        //de facon a ce que sac1 + (sac2 + newVal) = 2 x cible
        //2*cible car on cherche a tout mettre dans 1 seul sac
        if (2*cible >= somme){
            elementRemplissageSacDeux = 2*cible-somme;
            entiers2[nbEntiers2-1] = elementRemplissageSacDeux;
        }
        else {
            //Ceci va aggrandir la taille du deuxieme sac
            //On ajoute un élement fictif qui sera dans le bon sac
            //mais au final on aura réussi a mettre les objets dans le bon sac
            elementRemplissageSacDeux = somme - (2 * cible);
            entiers2[nbEntiers2-1] = elementRemplissageSacDeux;
        }

        for(int i = 0; i < nbEntiers2-1; i++){
            entiers2[i] = this.entiers[i];
        }

        return new PblPartition(nbEntiers2, entiers2);
    }

    @Override
    public boolean aUneSolution() {
        return reduction().aUneSolution();
    }

}
