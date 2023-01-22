
import javax.swing.JPanel;

import java.util.*;

/*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

public class Grille extends JPanel {
                            
                            /*Attributs de la classe Grille
                             * int LIGNES               : constante du jeu 4 lignes dans le tableau
                             * int COLONNES             : constante du jeu 4 colonnes dans le tableau
                             * Tuile[][] tableau        : la grille du jeu, un tableau de tuile
                             * boolean enCours          : un flag qui permet de dire si la partie est en cours (apres initialisation), ou non (avant le choix de joueur ou apres avoir gagné/perdu)
                             * int score                : calcul le nombre de deplacements effectues
                             */

                             /*Constructeurs de la classe Grille 
                              * Grille()                : cree la grille du jeu (var tableau) a un tableau de taille LIGNES et COLONNES (4x4)
                             */

                             /*Accesseurs de la classe Grille 
                              * Tuile[][] getTableau()  : permet de recuperer la tableau de tuiles
                             */

                             /*Methodes de la classe Grille 
                              * Tuile[][] initialisationTuileJeu()              : initialise le tableau avec que des 0 et 2 tuiles de 2 placees aleatoirement

                              * int parcoursLigneDR(int ligne, int colonne)     : methode qui parcourt une ligne entree pour verifier si des mouvements sont possibles et les realisent

                              * void rotateCW()                                 : methode qui tourne le tableau dans le sens des aiguilles d'une montre
                              * int deplacementDroit()                          : methode qui effectue les deplacements vers la droite a l'aide de le methode parcoursLigneDR
                                                                            retourne 0 si aucun mouvement n'a ete fait (pas possible), sinon la valeur maximale du tableau ou 
                                                                            -1 si plus aucun mouvement sont possibles (partie perdue)
                              * int deplacementGauche()                         : a l'aide de deplacementDroit() et rotateCW() on peut effectue tous les mouvements
                              * int deplacementBas()                            : a l'aide de deplacementDroit() et rotateCW() on peut effectue tous les mouvements
                              * int deplacementHaut()                           : a l'aide de deplacementDroit() et rotateCW() on peut effectue tous les mouvements
                              * int deplacement(String coup)                    : methode utile dans la console, effectue le mouvement donner en entre

                              * int calculMax()                                 : calcule la valeur maximale qui se trouve dans le tableau
                              * int calcul2Max()                                : calcule la 2eme plus grande valeur qui se trouve dans le tableau

                              * boolean verifVide()                             : renvoie vrai si au moins une case du tableau contient un 0, faux si tout est rempli
                              * int getNbCasesVides                             : renvoie le nombre de cases qui contiennent un 0
                              * int getNbCasesVidesHaut()                       : donne le nombre de cases vides sur la ligne supérieure
                              * int getNbCasesVidesGauche()                     : donne le nombre de cases vides sur la colonne suivante

                              * boolean verifMvtBas()                           : renvoie vrai un mouvement est possible vers le bas
                              * boolean verifMvtGauche()                        : renvoie vrai un mouvement est possible vers la gauche
                              * boolean verifMvtDroite()                        : renvoie vrai un mouvement est possible vers la droite
                              * boolean verifMvtHaut()                          : renvoie vrai un mouvement est possible vers le haut

                              * Tuile[][] dupliquer()                           : methode qui permet de dupliquer un tableau (utile pour des tableaux temporaires)

                              * void affichage()                                : methode utilisee dans la console pour afficher la grille de 2048

                              *Methodes pour l'IA de minimax:
                              * boolean estTerminal(Grille grille,String qui)   : methode qui verifie si la grille est en etat terminal cest-a-dire qu'il n'y a plus de mouvement possibles et la grille est pleine
                              *                                                   en fonction de qui est en train de jouer (Max = joueur, min=ordinateur qui place un 2 aleatoirement)
                              * int utility(Grille grille)                      : methode qui permet de calculer l'optimisation sur une grille. Ici nous avons choisi de faire la somme de toutes les valeurs dans le tableau / le nombre de cases pleines (differente de 0)
                              * List<Pair> getEmpty()                           : methode qui permet de renvoyer une variable avec 2 attributs indiquant la position des cases vides (=0) dans le tableau
                              * PairMU maximize(int a, int b, int p)            : methode principale de l'IAminimax, permet a l'ordinateur d'utiliser les fonctions creer precedemment pour calculer le meilleur coup possible en descendant jusqu'a p coups d'avance (p niveau de profondeur)
                              * int minimize(int a,int b, int p)                : methode appelee dans maximize et methode appelant maximize (des methodes recursives) qui calcul le cout pour l'ordinateur et son placement possible du 2   

                             
                             */

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */
    
    //Attributs 

    public static int LIGNES = 4;
    public static int COLONNES = 4;

    public Tuile[][] tableau; //grille commence a 0 

    public boolean enCours;
    public int score=0;
    public boolean grilleSimulee=false;
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */
    
    //Constructeurs et Accesseurs

    public Grille(){
        this.tableau= new Tuile[LIGNES][COLONNES];
        this.enCours = false;
    }
    
    public Tuile[][] getTableau(){
        return this.tableau;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    //Methode initialisationTuileJeu : permet d'initialiser une grille de Tuile remplie de 0 avec 2 tuiles de 2 placees aleatoirement dans le tableau
    
    public Tuile[][] initialisationTuileJeu(){ 
        int i=0,j=0;        //variables pour les compteurs
        this.tableau = new Tuile[LIGNES][COLONNES];
        //initialisation du tableau de 0
        for (i=0;i<LIGNES;i++){
            for (j=0;j<COLONNES;j++){
                tableau[i][j]=new Tuile(0);
            }
        }

        //creation de 2 tuiles de valeur 2
        Tuile tuileRandom1 = new Tuile(2);
        Tuile tuileRandom2 = new Tuile(2);
        int tuileRandom1x=(int)(Math.random() * 4);
        int tuileRandom1y=(int)(Math.random() * 4);
        int tuileRandom2x=(int)(Math.random() * 4);
        int tuileRandom2y=(int)(Math.random() * 4);

        //verifie que les deux tuiles aléatoire n'ont pas les memes coordonnees
        while ((tuileRandom1x==tuileRandom2x) && (tuileRandom1y==tuileRandom2y)){
            tuileRandom2x=(int)(Math.random() * 4);
            tuileRandom2y=(int)(Math.random() * 4);
        }

        //placement de ces tuiles dans le tableau
        this.tableau[tuileRandom1x][tuileRandom1y]=tuileRandom1;
        this.tableau[tuileRandom2x][tuileRandom2y]=tuileRandom2;
        return tableau;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode parcoursLigneDR : permet de parcourir le tableau et voir les deplacements vers la droite possibles
    renvoie les valeurs suivantes : -1 si les valeurs de lignes et colonnes entrees sortent du tableau
                                    0 si on a parcouru le tableau et aucun deplacement vers la droite est possible
                                    on ajoute 1 a chaque deplacement effectue*/
    public int parcoursLigneDR(int ligne, int colonne){ 
        int c = colonne-1;
        //test ligne et colonne < 4
        if ((ligne < 0) || (colonne < 0) || (ligne > 3) || (colonne > 3)) 
        {
            return -444; //code erreur
        }

        if (this.tableau[ligne][colonne].getValeur() == 0){
            while ((c >= 0) && (this.tableau[ligne][c].getValeur() == 0)){
                c--;
            }
            if (c == -1) { return 0; } //sortie du tableau finit la ligne et aucun changement
            this.tableau[ligne][colonne].valeurTuile = this.tableau[ligne][c].valeurTuile;
            this.tableau[ligne][c].valeurTuile = 0;
            return (parcoursLigneDR(ligne, colonne)+10); //on ajoute 1 car deplacement effectue
        }
        else {
            while ((c >= 0) && (this.tableau[ligne][c].getValeur() == 0)){
                c--;
            }
            if (c == -1) { return 0; } //sortie du tableau finit la ligne aucun changement
            if (this.tableau[ligne][c].valeurTuile == this.tableau[ligne][colonne].valeurTuile){
                this.tableau[ligne][colonne].valeurTuile = this.tableau[ligne][colonne].valeurTuile + this.tableau[ligne][colonne].valeurTuile;
                this.tableau[ligne][c].valeurTuile = 0;
                return (parcoursLigneDR(ligne, colonne-1)+1);
            }
            else{
                return (parcoursLigneDR(ligne, colonne-1));
            }
        }
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode rotateCW : permet de faire pivoter le tableau de 90 degres dans le sens des aiguilles d'une montre (=clockwise)*/

    public void rotateCW(){
        Tuile[][] valeurTableau = new Tuile[LIGNES][COLONNES];
		for (int i = 0; i < COLONNES; i++) {
			for (int j = 0; j < LIGNES; j++) {
				valeurTableau[i][j] = this.tableau[COLONNES - 1 - j][i];
			}
		}
		this.tableau = valeurTableau;
	}

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode deplacementDroit : permet de d'effectuer le deplacement vers la droite a l'aide de la methode parcourir
    Retourne 0 si pas bouge, -1 si tableau complet (partie perdue), et val max apres deplacement
    Cette fonction est separee en deux etapes :     -premierement on fait le parcours de toutes les lignes
                                                    -deuxiemement si un deplacement vers la droite est effectue, on rajoute une tuile de valeur 2 dans une case choisie aleatoirement vide*/

    public int deplacementDroit(){ 
        
        boolean pasBouge=true;              //flag si aucune des 4 lignes ne change   
        boolean verifVide=true;             //verifie si la case trouvée est bien vide (=0) puis verifier s'il reste des cases vides dans le tableau

        //D'abord je fais le parcours ligne sur toutes les lignes
        for (int i=0;i<LIGNES;i++){
            pasBouge= (parcoursLigneDR(i, 3) == 0) && pasBouge;
        }

        //Deuxieme etape : faire apparaitre un 2
        if (pasBouge){
            return 0;
        }
        else {
            if (!this.grilleSimulee){
                while (verifVide){
                    int l = (int)(Math.random() * 4) ;
                    int c = (int)(Math.random() * 4) ;

                    // On verifie que la valeur de la case est 0.
                    if(this.tableau[l][c].getValeur() == 0) {
                            this.tableau[l][c].setValeur(2);
                            verifVide = false ;
                    }
                }
            }
            this.score++;
            if (verifVide() || verifMvtDroite() || verifMvtBas()){
                return calculMax();
            }
            else{
                this.enCours = false;
                this.score=-Math.abs(this.score);
                return -1; //plus aucun mouvement possible -- partie perdue
            }
    }   
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode des 3 autres deplacements : on revient a effectuer un deplacement vers la droite a l'aide des rotateCW*/

    public int deplacementGauche(){ 
        rotateCW();
        rotateCW();
        int res = deplacementDroit();
        rotateCW();
        rotateCW();
        return res;
    }

    public int deplacementHaut(){ 
        rotateCW();
        int res = deplacementDroit();
        rotateCW();
        rotateCW();
        rotateCW();
        return res;
    }


    public int deplacementBas(){ 
        rotateCW();
        rotateCW();
        rotateCW();
        int res = deplacementDroit();
        rotateCW();
        return res;
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode deplacement : on donne le coup en entree et en fonction de celui-ci on effectue le mouvement souhaite*/
    
    public int deplacement(String coup) {
        if (coup.equals("r")){
            return deplacementDroit();
        }
        if (coup.equals("l")){
            return deplacementGauche();
        }
        if (coup.equals("u")){
            return deplacementHaut();
        }
        if (coup.equals("d")){
            return deplacementBas();
        }
        System.out.println("coup non valide");
        //0 signifie que la grille n'a pas bougee
        return 0;
    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode calculMax : donne la plus grande valeur dans le tableau */
    public int calculMax(){
        int max=0;
        for (int k=0;k<LIGNES;k++){
            for (int j=0;j<COLONNES;j++){
                if (max < this.tableau[k][j].getValeur()){
                    max = this.tableau[k][j].getValeur();
                }
            }
        }
        return max;
    }

    /*Méthode calcul2Max : donne la deuxième plus grande valeur dans le tableau */
    public int calcul2Max(){
        int deuxMax = 0;
        for (int k=0; k<LIGNES; k++){
            for (int j=0; j<COLONNES; j++){
                if ((this.tableau[k][j].getValeur() > deuxMax) && (this.tableau[k][j].getValeur() != this.calculMax())){
                    deuxMax = this.tableau[k][j].getValeur();
                }
            }
        }
        return deuxMax;
    }
        

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode verifVide : verifie si au moins une case est vide*/

    public boolean verifVide(){
        boolean myVerif=false;
        for (int k=0;k<LIGNES;k++){
            for (int j=0;j<COLONNES;j++){
                myVerif= (myVerif || (this.tableau[k][j].getValeur()==0)); //trouver une case vide 
            }
        }
        return myVerif;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */
    
    /*Methode getNbCasesVides() : donne le nombre de case vide */
    public int getNbCasesVides(){
        int res = 0;
        for (int k=0; k < LIGNES; k++) {
            for (int j=0; j< COLONNES; j++) {
                if (this.tableau[k][j].getValeur() == 0){ 
                    res ++;
                }
            }
        }
        return res;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode getNbCasesVidesBas : donne le nombre de cases vides sur la ligne inférieure*/
   
    public int getNbCasesVidesBas(){
        int res = 0;
        for(int j=0; j < COLONNES; j++){
            if (this.tableau[0][j].getValeur() == 0){
                res ++;
            }
        } 
        return res;
    }


    /*Méthode getNbCasesVidesDroite: donne le nombre de cases vides sur la colonne droite*/

    public int getNbCasesVidesDroite(){
        int res=0;
        for (int k=0; k<LIGNES; k++){
            if (this.tableau[k][0].getValeur() == 0){
                res ++;
            }
        }
        return res;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode verifMvtBas : renvoie vrai si un mouvement est possible vers le bas et faux sinon*/

     public boolean verifMvtBas(){
        boolean myVerif=false;
        for (int k=0;k<LIGNES-1;k++){
            for (int j=0;j<COLONNES;j++){
                myVerif= (myVerif || (!(this.tableau[k][j].getValeur()==0) && ((this.tableau[k][j].getValeur()==this.tableau[k+1][j].getValeur()) || (this.tableau[k+1][j].getValeur()==0)))); 
            }
        }
        return myVerif;
    
    }

    /*Methode verifMvtDroite : renvoie vrai si un mouvement est possible vers la droite et faux sinon*/

    public boolean verifMvtDroite(){
        boolean myVerif=false;
        for (int k=0;k<LIGNES;k++){
            for (int j=0;j<COLONNES-1;j++){
                myVerif= (myVerif || (!(this.tableau[k][j].getValeur()==0) && ((this.tableau[k][j].getValeur()==this.tableau[k][j+1].getValeur()) ||(this.tableau[k][j+1].getValeur()==0)))); 
            }
        }
        return myVerif;
    }
	
    /*Methode verifMvtGauche : renvoie vrai si un mouvement est possible vers la gauche et faux sinon*/

	public boolean verifMvtGauche(){
        boolean myVerif=false;
        for (int k=0;k<LIGNES;k++){
            for (int j=COLONNES-1;j>0;j--){
                myVerif= (myVerif || (!(this.tableau[k][j].getValeur()==0) && ((this.tableau[k][j].getValeur()==this.tableau[k][j-1].getValeur()) ||(this.tableau[k][j-1].getValeur()==0)))); 
            }
        }
        return myVerif;
    }

    /*Methode verifMvtHaut : renvoie vrai si un mouvement est possible vers le haut et faux sinon*/

    public boolean verifMvtHaut(){
        boolean myVerif=false;
        for (int k=LIGNES-1;k>0;k--){
            for (int j=0;j<COLONNES;j++){
                myVerif= (myVerif || (!(this.tableau[k][j].getValeur()==0) && ((this.tableau[k][j].getValeur()==this.tableau[k-1][j].getValeur()) || (this.tableau[k-1][j].getValeur()==0)))); 
            }
        }
        return myVerif;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */
   
    /*Methode affichage : permet d'afficher dans la console la grille de jeu*/

    public Tuile[][] dupliquer(Grille grid) {
        Tuile[][] tab = new Tuile[LIGNES][COLONNES];

		for(int l=0; l<LIGNES ;l++) {
			for(int c=0; c<COLONNES ;c++) {
                Tuile t = new Tuile(grid.tableau[l][c].getValeur());
				tab[l][c]=t;
			}
		}
        return tab;
	}

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode estTerminal : verifie si l'on est dans un cas de jeu terminal cest-a-dire qu'il n'y a plus de mouvements possibles et la grille est pleine*/
    
    public boolean estTerminal(Grille grille, String qui){
        //max est le joueur, celui qui cherche a atteindre le 2048
        if (qui == "max"){
            if (verifMvtHaut()){return false;}
            if (verifMvtDroite()){return false;}
            if (verifMvtGauche()){return false;}
            if (verifMvtBas()){return false;}
            //aucun mouvement possible pour le joueur
            return true;
        }
        else{
            //min est l'ordinateur (pas IA), mais celui qui place les tuiles aleatoires de 2 dans le tableau
            if (qui == "min"){
                for (int i=0;i<LIGNES;i++){
                    for (int j=0;j<COLONNES;j++){
                        if (grille.tableau[i][j].getValeur() == 0){
                            //il reste des cases a 0 donc l'ordinateur peut placer des tuiles de 2
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*Methode utility : methode qui permet de calculer l'optimisation sur une grille. Ici nous avons choisi de faire la somme de toutes les valeurs dans le tableau / le nombre de cases pleines (differente de 0) */
    public int utility(Grille grille){
        int compt = 0;
        int somme = 0;
        for (int i=0; i<LIGNES; i++){
            for (int j=0; j<COLONNES; j++){
                somme = somme + grille.tableau[i][j].getValeur();
                if (!(grille.tableau[i][j].getValeur() == 0)){
                    compt++;
                }
            }
        }
        return (int)(somme/compt);
    }

    public List<Pair> getEmpty(){
        List<Pair> myListofPair = new ArrayList<Pair>();
        for (int k=0; k < LIGNES; k++) {
            for (int j=0; j< COLONNES; j++) {
                if (this.tableau[k][j].getValeur() == 0){ 
                    Pair p = new Pair();
                    p.ligne=k;
                    p.colonne=j;
                    myListofPair.add(p);
                }
            }
        }
        return myListofPair;

    }
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode affichage : permet d'afficher dans la console la grille de jeu*/
 
    public PairMU maximize(int a, int b, int p){
        //int maxEnfant = 0;
        int maxUtility = -1;
        PairMU resultatPair = new PairMU();
        int tempUtility = -1;
        int p2=p-1;

        //System.out.println("je maximise avec une profondeur "+p);
        if ((p==0) || (estTerminal(this,"max"))){
            resultatPair.utility=utility(this);
            resultatPair.mvt = "0"; //impossible de faire mvt
            return resultatPair;
        }

        Grille grilleTemp = new Grille();
        grilleTemp.grilleSimulee=true;
        grilleTemp.tableau=dupliquer(this);
        if (verifMvtDroite()){
            grilleTemp.deplacementDroit();
            tempUtility =grilleTemp.minimize(a,b,p2);
            if (maxUtility < tempUtility){
                resultatPair.utility=tempUtility;
                resultatPair.mvt = "r";
            }
            //avec prunning
            if (resultatPair.utility >= b)
                return resultatPair;
            if (maxUtility > a)
                a = maxUtility;
        }
        grilleTemp.tableau=dupliquer(this);
        if (verifMvtGauche()){
            grilleTemp.deplacementGauche();
            tempUtility =grilleTemp.minimize(a,b,p2);
            if (resultatPair.utility < tempUtility){
                resultatPair.utility=tempUtility;
                resultatPair.mvt = "l";
            }
            if (resultatPair.utility >= b)
                return resultatPair;
            if (maxUtility > a)
                a = maxUtility;
        }
        grilleTemp.tableau=dupliquer(this);
        if (verifMvtBas()){
            grilleTemp.deplacementBas();
            tempUtility =grilleTemp.minimize(a,b,p2);
            if (resultatPair.utility < tempUtility){
                resultatPair.utility=tempUtility;
                resultatPair.mvt = "d";
            }
            if (resultatPair.utility >= b)
                return resultatPair;
            if (maxUtility > a)
                a = maxUtility;
        }
        grilleTemp.tableau=dupliquer(this);
        if (verifMvtHaut()){
            grilleTemp.deplacementHaut();
            tempUtility =grilleTemp.minimize(a,b,p2);
            if (resultatPair.utility < tempUtility){
                resultatPair.utility=tempUtility;
                resultatPair.mvt = "u";
            }
        }
        return resultatPair;
    }

    public int minimize(int a, int b, int p){
        int minUtility = 1000000;
        Pair pairTemp = new Pair();
        PairMU tempMU = new PairMU();

        if ((p==0) || (estTerminal(this,"min"))){
            return utility(this);
        }

        Grille grilleTemp = new Grille();
        grilleTemp.grilleSimulee=true;
        List<Pair> listeEnfants = getEmpty();
        for (int i = 0; i < listeEnfants.size(); i++) {
            grilleTemp.tableau=grilleTemp.dupliquer(this);
            pairTemp = listeEnfants.get(i);
            grilleTemp.tableau[pairTemp.ligne][pairTemp.colonne].valeurTuile=2;
            tempMU = grilleTemp.maximize(a, b, p-1);
            if (minUtility > tempMU.utility){
                minUtility = tempMU.utility;
            }
            //avec prunning
            if (minUtility <= a)
                break;
            if (minUtility < b)
                b = minUtility;
        }
        return minUtility;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode affichage : permet d'afficher dans la console la grille de jeu*/

    public void affichage(){// s'occupe de l'affichage propre
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i=0;i<LIGNES;i++){
            for (int j=0;j<COLONNES;j++){
                System.out.print("| "+tableau[i][j].toString()+" ");
            }
            System.out.println(" | ");
        }
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode utilisée pour tester un tableau */
    /* 
        public Tuile[][] testTab(){ 
        int i=0,j=0;        //variables pour les compteurs
        int c=0;
        Tuile[][] test = new Tuile[LIGNES][COLONNES];
        //initialisation du tableau de 0
        for (i=0;i<LIGNES;i++){
            for (j=0;j<COLONNES;j++){
                test[i][j]=new Tuile(c);
                c++;
            }
        }
        return test;
    }*/
   
    /* 
    public static void main(String[] args){
       Grille grigri = new Grille();
       /* 
       System.out.println("j'initialise la grille");
       grigri.tableau=grigri.initialisationTuileJeu();
       grigri.affichage();

        Utilisateur joueur = new Utilisateur();
        while (true){
            grigri.affichage();
            grigri.deplacement(joueur.donnerCoup(grigri));
        }
        

       
    }*/
  
}
