
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

public abstract class Joueur {

	  						/*Attributs de la classe Joueur
                             * String pseudo            : constante pour la taille de l'ecran
                             * int score             	: constante pour la taille de l'ecran
                            */

                             /*Constructeurs de la classe Joueur 
                              * Joueur()                        : initialise son pseudo a " " et son score a 0
							  * Joueur(String pseu, int sco)	: initialise son pseudo a pseu et son score a sco
                              */

                             /*Accesseurs et Mutateurs de la classe Joueur 
                              * String toString                 : nous donnera le nom du joueur et son score
                              * int getScore()                  : le score nous sera renvoye
                              * String getPseudo()              : le pseudo nous sera renvoye
                              * void setScore(int sco)          : le score du joueur sera modifie a sco
                              * void setPseudo(String pseu)     : le pseudo du joueur sera modifie a pseu
                             */

                             /*Methodes de la classe Joueur 
                              * String donnerCoup()             : la seule methode qui sera strictement differente pour toutes les classes qui herite de joueur 
                                                                 Utilisateur : dans la console on devra entrer "r","l","u","d" pour les directions
                                                                 Ia aleatoire : une de ces 4 directions sera tiree et renvoyee au hasard (fonction random)
                                                                 Ia coin random : on choisit le coin bas a droite et on tire un chiffre entre 0 et 99 une probabilite plus forte de tomber sur la direction bas et droite
                                                                 Ia coin     : si le mouvement est possible a droite, l'ia choisit a droite, sinon on verifie en bas, puis haut et sinon on retourne gauche
                             */

    
    private String pseudo;
    private int score;

    public Joueur(){
        this.pseudo="";
        this.score=0;
    }

    public Joueur(String pseu, int sco){
        this.pseudo = pseu;
        this.score = sco;
    }

    public abstract String toString();

    public abstract void setScore(int sco);

    public abstract void setPseudo(String pseu);

    public abstract int getScore();

    public abstract String getPseudo();

    public abstract String donnerCoup(Grille grille);
}
