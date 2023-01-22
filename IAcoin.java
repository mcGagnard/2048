/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*On ne va pas re-présenter les attributs et methodes de cette classe qui sont deja presentes dans la classe abstraite Joueur */

public class IAcoin extends Joueur{

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */
    
    /*Attributs*/
    private String pseudo;
    private int score;
        
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */
    
    /*Constructeurs*/
    public IAcoin(){
           this.pseudo="IA coin";
            this.score=0;
    }
        
    public IAcoin(String pseu, int sco){
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
    
    /*Methode donnerCoup :  Verifie si un mouvement est possible vers la droite, si oui bougera a droite
     *                      Verifie si un mouvement est possible vers le bas, si oui bougera en bas
     *                      Verifie si un mouvement est possible vers le haut, si oui bougera en haut
     *                      Sinon il bougera a gauche
     *                      L'ordre effectue ici est important car il ira en priorite a droite puis en bas, et enfin en haut et a gauche
     */

    public String donnerCoup(Grille grille){
        if (grille.verifMvtDroite()){
            return "r";
        }
        if (grille.verifMvtBas()){
            return "d";
        }
        if (grille.verifMvtHaut()){
            return "u";
        }
        else {return "l";}
    }
        
}
    
