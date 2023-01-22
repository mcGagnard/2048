import javax.swing.*;
import java.util.Scanner;

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
/*Voici notre classe principale de Jeu */
public class Jeu {

    public static  Partie myGame = new Partie();
    public static void main(String[] args){

		System.out.println("Voulez vous jouer dans la console (1) ou avec une interface graphique (2)");
		try (Scanner scan = new Scanner(System.in)) {
			int optionAffichage = scan.nextInt();

			while (!(optionAffichage == 1) && !(optionAffichage == 2)){
				System.out.println("Voulez vous jouer dans la console (1) ou avec une interface graphique (2)");
				optionAffichage = scan.nextInt();
				scan.close();
			}
			int score=0;
			switch (optionAffichage){
				case 1 : 
					myGame.init();
					score=myGame.jouerPartie();
					System.out.println("score max du joueur "+myGame.joueur.getPseudo()+ " = " + myGame.grille.calculMax());
					System.out.println("score jeu "+score);
					break;
				case 2 : 
					myGame.getWindow().addKeyListener(myGame) ;
					myGame.getWindow().getContentPane().add(myGame) ;
					
					/* La methode suivante permet de d'arreter le programme une fois que la fenetre est fermee*/
					myGame.getWindow().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE) ;
					myGame.getWindow().setSize(Partie.LARGEUR, Partie.HAUTEUR) ;  //Prend les dimensions de l'ecran donne en attributs de Partie
					myGame.getWindow().setLocationRelativeTo(null) ; // Pour que la fenetre soit au centre de l'ecran.
					myGame.getWindow().setVisible(true) ;    //Pour rendre la fenetre visible
					myGame.getWindow().setResizable(false) ; //Pour ne pas pouvoir redimensionner la fenetre
					break;
				}

			
		}
		catch (Exception e) {
			System.out.println("Une erreur est survenue dans main");
		}

		/*Le test ci-dessous permet de realiser plusieurs parties de 2048 par des IA et d'avoir une statistiques sur les moyennes
			 des tuiles maximum atteinte pendant la partie*/
		
			/*int scoremoyen = 0;
			int scoremax = 0;
			int temp = 0;
			Joueur joueur = new IAcoinVide();
			for (int i=0; i<20;i++){
			    System.out.println("jaffiche linitialisation");
			    myGame.init(joueur);

			    myGame.sleepms = 10;
			
			    //System.out.println("jaffiche le jeu");
			    myGame.jouerPartie();
			    
			    temp=myGame.grille.calculMax();
			    scoremax= Math.max(temp,scoremax);
			    scoremoyen += temp;
			}
			System.out.println("score moyen du joueur "+joueur.getPseudo()+ " = " + scoremoyen/20+" le max du tableau est "+scoremax);*/
    }

}
