import java.awt.Color;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

public class Tuile{
                            /*Attributs de la classe Tuile
                             * int LARGEUR         : pour l'affichage graphique nous utilisons des variables static (constantes)
                             * int HAUTEUR         : la hauteur d'une tuile
                             * int LARGEUR_ARC     : la largeur de l'arc de la tuile
                             * int HAUTEUR_ARC     : la hauteur de l'arc de la tuile
                             */

                             /*Constructeurs de la classe Tuile 
                              * Tuile()            : initialise la valeur d'une tuile a 0
                              * Tuile(int)         : initiliase la valeur d'une tuile a la valeur en entree
                             */

                             /*Accesseurs de la classe Tuile 
                              * getValeur()        : permet de recuperer la valeur d'une tuile
                              * getCouleur()       : permet de recuperer la couleur d'une tuile
                             */

                             /*Mutateurs de la classe Tuile 
                              * setValeur()        : permet de modifier la valeur d'une tuile a la valeur en entree
                             */

                             /*Methodes de la classe Tuile 
                              * toString()         : ecrira la valeur de la tuile (dans la console) avec 4 places de caracteres
                              * setCouleurFond()   : permet de donner une couleur de fond en fonction de la valeur de la tuile (pour l'interface graphique)
                             */


    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Attributs*/
    public static final int LARGEUR = 80; //largeur en pixel d'une tuile
    public static final int HAUTEUR = 80; //hauteur en pixel d'une tuile
    public static final int LARGEUR_ARC = 15;//largeur de l'arc autour de la tuile (effet de style)
    public static final int HAUTEUR_ARC = 15;

    public int valeurTuile;
    private Color fond;

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Constructeurs*/
    public Tuile(){
        this.valeurTuile=0;
    }

    public Tuile(int valT){
        this.valeurTuile=valT;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Accesseurs et Mutateurs*/

    public void setValeur(int valT){
        this.valeurTuile=valT;
    }

    public int getValeur(){
        return this.valeurTuile;
    }

    public Color getCouleur(){
        return this.fond;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode toString : nous permet de verifier la valeur de la tuile
     * utilisee dans l'affichage du jeu dans la console 
     * 4d pour 4 caracteres possibles (2048)*/
    public String toString(){
        return String.format("%4d", this.valeurTuile);
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode setCouleurFond : cette methode nous est utile dans la partie interface graphique car elle donne une couleur de fond pour chaque valeur de tuile*/

    public void setCouleurFond() {
		switch(this.getValeur()) {
			case 2 :
				this.fond = new Color(0xe9e9e9) ;
				break ;
			case 4 :
				this.fond = new Color(0xe6daab) ;
				break ;
			case 8 :
				this.fond = new Color(0xf79d3d) ;
				break ;
			case 16 :
				this.fond = new Color(0xf28007) ;
				break ;
			case 32 :
				this.fond = new Color(0xf55e3b) ;
				break ;
			case 64 :
				this.fond = new Color(0xff0000) ;
				break ;
			case 128 :
				this.fond = new Color(0xe9de84) ;
				break ;
			case 256 :
				this.fond = new Color(0xf6e873) ;
				break ;
			case 512 :
				this.fond = new Color(0xf5e455) ;
				break ;
			case 1024 :
				this.fond = new Color(0xf7e12c) ;
				break ;
			case 2048 :
				this.fond = new Color(0xffe400) ;
				break ;
			default :
				this.fond = new Color(0, 0, 0) ;
				break ;
		}
	}
}