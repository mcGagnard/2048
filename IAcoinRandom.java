/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*On ne va pas re-présenter les attributs et methodes de cette classe qui sont deja presentes dans la classe abstraite Joueur */

public class IAcoinRandom extends Joueur{

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Attributs*/
    private String pseudo;
    private int score;
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Constructeurs*/
    public IAcoinRandom(){
            this.pseudo="IA coin aléatoire";
            this.score=0;
    }
    
    public IAcoinRandom(String pseu, int sco){
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

    /*Methode donnerCoup :  tire aleatoirement un chiffre entre 0 et 100 qui sera attribue a une direction selon le classement suivant 
                            de 0-39 a droite, de 40-79 en bas, de 80-89 en haut et de 90-99 à gauche 
                            ceci permet d'avoir une plus grande probabilité de respecter la méthode du coin mais dans le cas ou les 
                            mouvements a droite et en bas sont plus possibles on effectue en plus petites probabilites des mouvements gauche et haut*/

    public String donnerCoup(Grille grille){
        int valCoup = (int)(Math.random() * 100) ;
        if (valCoup < 40){
                return "r";
        }
        if (valCoup < 80){
                return "d";
        }
        if (valCoup < 90){
                return "u";
        }
        else {return "l";}
    }
    
}
