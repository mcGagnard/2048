/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*On ne va pas re-présenter les attributs et methodes de cette classe qui sont deja presentes dans la classe abstraite Joueur */

public class IAalea extends Joueur{

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Attributs*/
    private String pseudo;
    private int score;
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Constructeurs*/
    public IAalea(){
            this.pseudo="IA aleatoire";
            this.score=0;
    }
    
    public IAalea(String pseu, int sco){
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

    /*Methode donnerCoup :  tire aleatoirement un chiffre entre 0 et 3 qui sera attribué à une direction
                            methode de resolution la moins efficace mais permet d'avoir une IA aléatoire pour débuter */

    public String donnerCoup(Grille grille){
        String coup="";
        int valCoup = (int)(Math.random() * 4) ;
        switch (valCoup){
            case 0 :
                    coup = "r";
                    break;
            case 1 :
                    coup = "d";
                    break;
            case 2 :
                    coup = "l";
                    break;
            case 3 :
                    coup = "u";
                    break;
        }
        return coup;
    }
    
}
