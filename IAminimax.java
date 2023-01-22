/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*On ne va pas re-présenter les attributs et methodes de cette classe qui sont deja presentes dans la classe abstraite Joueur */

public class IAminimax extends Joueur{

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Attributs*/
    private String pseudo;
    private int score;
    private int profondeur=1;           //parametres initialise a 1 mais sera change en creeant un objet de classe IAminimax
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Constructeurs*/
     public IAminimax(int p){
            this.pseudo="IA minimax";
            this.score=0;
            this.profondeur=p;
    }
    
    public IAminimax(String pseu, int sco){
        this.pseudo = pseu;
        this.score = sco;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methodes : Accesseurs et Mutateurs*/
    
    public String toString(){
            String res = "";
            res= "joueur = "+this.pseudo+", score = "+this.score;
            return res;
    }
    
    public void setScore(int sco){
            this.score=sco;
    }
    
    public void setPseudo(String pseu){
            this.pseudo=pseu;
    }
    
    public int getScore(){
            return this.score;
    }
    
    public String getPseudo(){
            return this.pseudo;
    }
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode donnerCoup :  on utilise la methode du minimax donc toutes les methodes utiles pour donner ce coup sont dans grille
     *                      on ne renvoie que le resultat de ceux-ci*/

     public String donnerCoup(Grille grille){
        //comme java ne permet pas de rendre 2 variables, nous avons cree une classe PairMU qui contient 2 attributs utility et mvt
        PairMU myPairMU = grille.maximize(-1, 10000, this.profondeur);
        return myPairMU.mvt;
    }
    
}
