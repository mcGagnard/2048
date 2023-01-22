//Ceci importe la classe Scanner du package java.util
import java.util.Scanner; 

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*On ne va pas re-présenter les attributs et methodes de cette classe qui sont deja presentes dans la classe abstraite Joueur */

public class Utilisateur extends Joueur { //on pourra ainsi redefinir les elements (methodes, constructeurs, mutateurs et accesseurs de Joueur)

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    //Attributs
    private String pseudo;
    private int score;

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    //Constructeurs
    public Utilisateur(){
        this.pseudo="utilisateur humain";
        this.score=0;
    }

    public Utilisateur(String pseu, int sco){
        this.pseudo = pseu;
        this.score = sco;
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    //Mutateurs
    public void setScore(int sco){
        this.score=sco;
    }

    public void setPseudo(String pseu){
        this.pseudo=pseu;
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    //Accesseurs
    public int getScore(){
        return this.score;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    //Methode toString
    public String toString(){
        String res = "";
        res= "joueur = "+this.pseudo+", score = "+this.score;
        return res;
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /*Methode donnerCoup : cette methode demande dans la console a l'utilisateur d'entrer une direction de mouvement */

    public String donnerCoup(Grille grille){ 
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Veuillez saisir une direction (l:Left, r:Right, u:Up, d:Down) :");
            System.out.println("test 1");
            String coup = "0";
            //String coup = scanner.nextLine();
            while (!(coup.equals("r")) && !(coup.equals("u")) && !(coup.equals("l")) && !(coup.equals("d"))){
                System.out.println("Veuillez saisir une direction (l:Left, r:Right, u:Up, d:Down) :");
                coup = scanner.nextLine();
            }
            System.out.println("string "+coup);
            scanner.close();
            return coup;
        }
        catch (Exception e) {
			System.out.println("Une erreur est survenue donnerCoup"+e);
            return ("");
		}
    }
}
