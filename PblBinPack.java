import java.io.*;
import java.util.Arrays;

/**
 * Created by Antho on 14/11/16.
 */

public class PblBinPack extends PblDec
{
    private int nbObjets;
    private int poids[];
    private int cap;
    private int nbSacs;
    //  //
    public PblBinPack(int nbObjets, int entiers[], int nbSacs, int cap){
        this.nbObjets=nbObjets;
        this.poids=entiers;
        this.cap=cap;
        this.nbSacs=nbSacs;
    }


    public PblBinPack(String fileName, int nbSacs) {
        try {
            this.nbSacs = nbSacs;
            BufferedReader donnee  //le fichier qui contient les données du pb
                    = new BufferedReader (new FileReader(fileName));
            this.cap = Integer.parseInt(donnee.readLine());
            this.nbObjets = Integer.parseInt(donnee.readLine());
            this.poids = new int[nbObjets];
            for (int i=0;i< this.nbObjets;i++) {
                this.poids[i] = Integer.parseInt(donnee.readLine());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //retourne Vrai SSi le pb a une solution
    public boolean aUneSolution() {
        // essaie tous les certificats un à un jusqu'à en trouver un correct -si il existe	....
        CertificatBinPack cert = new CertificatBinPack(this);

        while (!cert.estDernier()) {
            //System.out.println(cert);

            if (cert.estCorrect())
            {
                System.out.println(cert);
                return true;
            }

            cert.suivant();
        }
        System.out.println(cert);
        return false;
    }

    //Algo non déterministe
    //si il y aune solution, au moins une exécution doit retourner Vrai
    // sinon, toutes les exécutions doivent retourner Faux
    public boolean aUneSolutionNonDeterministe() {
        //   A compléter
        //génère alétaoirement un certificat et vérifie si il est correct
        CertificatBinPack cert = new CertificatBinPack(this);
        cert.alea();

        System.out.println(cert);
        return cert.estCorrect();
    }


    public int getNbObjets() {
        return nbObjets;
    }

    public int getNbSacs() {
        return nbSacs;
    }

    public int[] getPoids() {
        return poids;
    }

    public int getCap() {
        return cap;
    }

    @Override
    public String toString() {
        return "PblBinPack{" +
                "nbObjets=" + nbObjets +
                ", poids=" + Arrays.toString(poids) +
                ", cap=" + cap +
                ", nbSacs=" + nbSacs +
                '}';
    }

    // différents accesseurs, fonctions affichage ...
}