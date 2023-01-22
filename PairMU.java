/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*Comme le package de java Pair ne marchait pas, nous avons du creer une classe permettant de rendre deux variables (pour l'IAminimax et ses methodes)*/

public class PairMU{

    //Attributs
    String mvt;
    int utility;

    //Constructeur
    public PairMU(){
        this.mvt="0";
        this.utility=-1;
    }
}
