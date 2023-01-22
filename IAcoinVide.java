/*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

public class IAcoinVide extends Joueur{

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Attributs*/
    private String pseudo;
    private int score;
    
    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Constructeurs*/
    public IAcoinVide(){
            this.pseudo="IA optimisation coin vides";
            this.score=0;
    }
    
    public IAcoinVide(String pseu, int sco){
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

    /*Methode donnerCoup :  méthode du coin en maximisant le nb de cases vides*/
    /*                      on part du principe qu'on travaille avec le coin en bas à droite */
    
    public String donnerCoup(Grille grille){
        String coup="";
        int nbCasesVides = 0;

        Grille temp = new Grille();
    
        //Vérifions qu'on peut aller en bas ou a droite
        if (grille.verifMvtBas() || grille.verifMvtDroite()) {
            //si les 2 mvt sont possibles on effectue celui maximisant le nb de cases vides
            
            if (grille.verifMvtDroite() && grille.verifMvtBas()) {
                //on copie la grille actuelle puis on effectue le deplacement vers la droite pour avoir le nb de cases vides apres coup
                temp.tableau=temp.dupliquer(grille);
                temp.deplacementDroit();
                nbCasesVides = temp.getNbCasesVides();
                coup = "r";
                //on reinitialise temp a la grille de depart, on fait le meme test que precedemment mais en bas
                temp.tableau=temp.dupliquer(grille);
                temp.deplacementBas();
                if(temp.getNbCasesVides() > nbCasesVides) {
                    nbCasesVides = temp.getNbCasesVides();
                    coup = "d";
                }
            }
            //si un seul des 2 mouvements est possible on le bouge
            else{
                if(grille.verifMvtDroite()){coup = "r";}
                if(grille.verifMvtBas()){coup = "d";}
            }
        }
        //si on ne peut aller ni en bas ni a droite
        else{
            //si on peut aller en haut et à gauche
            if (grille.verifMvtHaut() && grille.verifMvtGauche()) {
                //si la ligne supérieure, la colonne gauche ou les deux sont remplies
                if((grille.getNbCasesVidesDroite() ==0)||(grille.getNbCasesVidesBas()==0)) {
                //si seulement l'un des deux est remplie, on effectue le mvt qui laissera la colonne ou la ligne intacte
                    if((grille.getNbCasesVidesDroite() != 0) || (grille.getNbCasesVidesBas() != 0)) {
                                if(grille.getNbCasesVidesBas() == 0)
                                    coup = "l";
                                if(grille.getNbCasesVidesDroite() == 0)
                                    coup = "u";
                    }
                    // Si la ligne supérieure et la colonne gauche sont remplies
                    else {
                        // Si la plus grande valeur atteinte se trouve dans le coin inferieur droit
                        if(grille.calculMax() == grille.getTableau()[3][3].getValeur()) {
                            // Si la deuxième plus grande valeur atteinte se trouve juste à coté de la plus grande
                            if((grille.calcul2Max() == grille.getTableau()[3][2].getValeur()) || (grille.calcul2Max() == grille.getTableau()[2][3].getValeur())){
                                // Si la deuxième plus grande valeur atteinte se trouve juste à gauche de la plus grande
                                if(grille.calcul2Max() == grille.getTableau()[3][2].getValeur())
                                    coup = "l";
                                    // Si la deuxième plus grande valeur atteinte se trouve juste en dessous de la plus grande
                                    if(grille.calcul2Max() == grille.getTableau()[2][3].getValeur())
                                        coup = "u";
                                }
                                else{
                                    temp.tableau=temp.dupliquer(grille);
                                    temp.deplacementGauche();
                                    nbCasesVides = grille.getNbCasesVides();
                                    coup = "l";
                        
                                    temp.tableau=temp.dupliquer(grille);
                                    temp.deplacementHaut();
                                    if(temp.getNbCasesVides() > nbCasesVides){
                                        nbCasesVides = temp.getNbCasesVides();
                                        coup = "u";
                                    }
                                }
                        }
                        //si la plus grande valeur atteinte ne se trouve pas dans le coin en bas a droite
                        else{
                            temp.tableau=temp.dupliquer(grille);
                            temp.deplacementGauche();
                            nbCasesVides = temp.getNbCasesVides();
                            coup = "l";
                            
                            temp.tableau=temp.dupliquer(grille);
                            temp.deplacementHaut();
                            if(temp.getNbCasesVides() > nbCasesVides){
                                nbCasesVides = temp.getNbCasesVides();
                                coup = "u";
                            }
                        }
                    }   
                }
                else{
                    //Si ni la ligne inferieure ni la colonne droite sont remplies
                    //déterminons le mouvement qui max le nb de cases vides
                    temp.tableau=temp.dupliquer(grille);
                    temp.deplacementGauche();
                    nbCasesVides = temp.getNbCasesVides();	
                    coup = "l";
                           
                    temp.tableau=temp.dupliquer(grille);
                    temp.deplacementHaut();
                    if(temp.getNbCasesVides() > nbCasesVides) {
                        nbCasesVides = temp.getNbCasesVides();
                        coup = "u";
                    }
                }
            }
            //si on ne peut aller uniquement qu'en haut ou gauche
            else{
                if(grille.verifMvtGauche()){coup = "l";}
                if(grille.verifMvtHaut()){coup = "u";}
            }
   
        }
    return coup;
    }
}

   
