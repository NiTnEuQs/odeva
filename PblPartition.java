import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Antho on 05/12/16.
 */
public class PblPartition extends PblDec {
    private int [] entiers;
    private int sum;
    private int nbSacs;
    private int nbEntiers;

    public PblPartition(int nbEntiers, int entiers[]) {
        super();
        this.entiers = entiers;
        this.nbEntiers = nbEntiers;
    }

    public PblPartition(String fileName) {
        try {
            BufferedReader donnee  //le fichier qui contient les données du pb
                    = new BufferedReader (new FileReader(fileName));
            this.nbEntiers = Integer.parseInt(donnee.readLine());
            this.entiers = new int[nbEntiers];
            for (int i=0;i< this.nbEntiers;i++) this.entiers[i]=Integer.parseInt(donnee.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //retourne la moitié de la somme des entiers
    public int getHalfSum() {
        int sum = 0;

        for (int entier : entiers) {
            sum += entier;
        }
        return sum/2;
    }

    public PblBinPack reduction(){
        sum = getHalfSum();
        nbSacs = 2;
        return new PblBinPack(nbEntiers, entiers ,nbSacs, sum);
    }

    @Override
    public boolean aUneSolution() {
        return reduction().aUneSolution();
    }
}
