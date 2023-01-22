import java.awt.Color;                  //Permet de manipuler avec les couleurs
import java.awt.Font;                   //Permet de manipuler les polices de texte
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;      //Permet l'interaction entre l'utilisateur au clavier et la machine
import java.util.Scanner; 				//Ceci importe la classe Scanner du package java.util
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

public class Partie extends JPanel implements KeyListener{

	  						/*Attributs de la classe Partie
                             * int LARGEUR              : constante pour la taille de l'ecran
                             * int HAUTEUR             	: constante pour la taille de l'ecran
                             * int TAILLE		        : constante pour la taille du jeu (4x4)
                             * int sleepms		        : valeur a utilisee dans la console pour le temps entre les mouvements de l'IA
							 * Grille grille			: une grille de jeu de la classe Grille
                             * int quelJoueur           : initialise a 0, 1 pour l'utilisateur et 2 pour une IA (100 lorsque l'IA est choisie) surtout utile pour l'interface graphique
							 * Joueur joueur			: un joueur de type Joueur classe abstraite qui sera initialise apres que le choix a ete fait
							 * JFrame window			: une fenetre de jeu pour l'interface graphique
                             */

                             /*Constructeurs de la classe Partie
                              * Partie()                : initialise quelJoueur a 0 et une nouvelle Grille par son Constructeur vide
							  * Partie(int joueur)		: change la valeur de quelJoueur par celui donne en entree et initialise une nouvelle Grille par son Constructeur vide
                             */

                             /*Accesseurs de la classe Partie
                              * JFrame getWindow()       : permet de recuperer le JFrame de la Partie
                             */

                             /*Methodes de la classe Partie
                              * void init()              	: initialise une partie dans la console (demande quel joueur puis initialise le tableau avec initialisationTuileJeu de Grille)
                              * void init(Joueur joueur)   	: methode overloading (dans l'interface graphique) initialise le jeu avec un joueur donne en entree
                              * int jouerPartie()           : methode de jeu qui renvoie le resultat (-1 perdu) et (2048 gagne)

							  //methode pour l'interface graphique
                              * void paint(Graphics g)      : la methode generale de notre interface graphique qui fait appel aux autres. represente tous les cas de la partie
                              * void drawChoixJoueurs()     : dessine la page qui demande avec quel joueur on souhaite jouer (IA ou Utilisateur)
                              * void drawChoixIA()          : si on decide de jouer avec une IA paint appelle drawChoixIA qui presente les differents choix de l'ia possible
                              * void drawGrille()           : dessine la grille en appelant drawTuile
                              * void drawTuile()            : dessine une tuile en recuperant sa valeur et la couleur attribuee a celle-ci

							  //methode de KeyListener
							  * void keyReleased()			: non utilisee
							  * void keyTyped()				: non utilisee
							  * void keyPressed()			: les actions realisees en fonction de la touche tapee au clavier et de quel joueur il s'agit
                             */

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    //Attributs

    public static final int LARGEUR = 400;                      //taille ecran
    public static final int HAUTEUR = 630;                      //taille ecran
	public static final int TAILLE = 4;
    public int sleepms = 250; //temps entre les mouvements

    Grille grille;
    private int quelJoueur; //numero qui attribuera avec quel joueur nous sommes

    Joueur joueur;
    private JFrame window = new JFrame("2048");

    private boolean continuer = false ; // Utilis pour un controle lors de l'affichage des options apres avoir gagne une partie (la continuer ou la quitter)

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    //Constructeurs

    public Partie(){
        this.quelJoueur=0;
		this.grille = new Grille();
    }

    public Partie(int joueur){
        this.quelJoueur=joueur;
		this.grille = new Grille();
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Accesseurs */

    public JFrame getWindow() {
		return this.window;
	}

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode jouerPartie*/

    public int jouerPartie(){//String msg
        int resultat=0;
        while (!(resultat == -1) && !(resultat == 2048)){
                resultat = this.grille.deplacement(joueur.donnerCoup(this.grille));
                this.grille.affichage();
                try {
                    Thread.sleep(this.sleepms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

        //
        if (joueur instanceof Utilisateur){
            if (resultat == 2048){
                System.out.println("Gagne ! ");
            }
            else{
                System.out.println("Perdu :( ");
            }
        }
        return resultat;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

    /*Methode init*/

    public void init(){
        System.out.println("Veuillez choisir votre mode de jeu : ");
    		System.out.println(" 1 utilisateur");
    		System.out.println(" 2 IA aleatoire ");
    		System.out.println(" 3 IA coinBete ");
    		System.out.println(" 4 IA coin ");
    		System.out.println(" 5 IA coinCasesVides");
    		System.out.println(" 6 IA minimax a profondeur 1");
    		System.out.println(" 7 IA minimax a profondeur 7");
        try (Scanner sc = new Scanner(System.in)) {
			int quelJoueur = 0;
			while ((quelJoueur < 1) || (quelJoueur > 7)){
				         quelJoueur = sc.nextInt();
			}
			sc.close();
			switch (quelJoueur){
			    case 1 :
			            this.joueur = new Utilisateur();
						          this.grille.enCours=true;
			            break;
			    case 2 :
			            this.joueur = new IAalea();
			            break;
			    case 3 :
			            this.joueur = new IAcoinRandom();
			            break;
			    case 4 :
			            this.joueur = new IAcoin();
			            break;
			    case 5 :
			            this.joueur = new IAcoinVide();
			            break;
				      case 6 :
			            this.joueur = new IAminimax(1);
			            break;
				      case 7 :
			            this.joueur = new IAminimax(9);
						          this.sleepms = 10;
			            break;
			}
			//sc.close();
		}
		catch (Exception e) {
			System.out.println("Une erreur est survenue procedure init");
		}
        this.grille.initialisationTuileJeu();
        this.grille.affichage();
    }

    //methode overloading : utiliser pour faire les tests avec une IA determinee
    public void init(Joueur joueur){
        this.joueur = joueur;

        this.grille.initialisationTuileJeu();

        this.grille.affichage();
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------- */

	/* Methode prenant en charge l'affichage de l'interface graphique */

	public void paint(Graphics g) {
		super.paint(g);

		if (this.quelJoueur == 0){
			drawChoixJoueurs(g);
		}
		else {
			//Si le joueur a ete initialise en tant qu'utilisateur on a son affichage
			if (this.joueur instanceof Utilisateur){
				//Si la partie n'a pas encore commencee, on lui demande de la lancer avec la touche ENTRER
				if (!(this.grille.enCours)){
						g.setColor(new Color(250, 239, 226));
						g.fillRect(0, 0, LARGEUR, HAUTEUR);
						g.setColor(new Color(92, 82, 69));
						g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 18));
						g.drawString("Appuyez sur 'Entrer' pour commencer", 40, 300) ;
						g.drawString("Tapez 'Q' pour quitter", 100, 350) ;
				}
				// Enfin, on affiche le plateau.
				else{
					g.setColor(new Color(173, 159, 140)) ;
          			g.fillRect(220 + 10 * Partie.TAILLE, 80, 85, 85) ;
					g.setColor(new Color(214, 201, 184)) ;
					g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 17));
					g.drawString("SCORE ", 230 + 10 * Partie.TAILLE, 110) ;
					g.setColor(Color.white) ;
					g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 27));
					g.drawString(" "+Math.abs(this.grille.score)+" ", 235 + 10 * Partie.TAILLE, 145) ;

					g.setColor(new Color(173, 159, 140)) ;
					g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 16));
					g.drawString("Utilisez les fleches pour bouger ", 18 + 10 * Partie.TAILLE, 185) ;

					g.setColor(new Color(242, 215, 92)) ;
					g.fillRect(17 + 10 * Partie.TAILLE, 60, 100, 100) ;
					g.setColor(Color.white) ;
					g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 32));
					g.drawString("2048 ", 20 + 10 * Partie.TAILLE, 120) ;
					this.drawGrille(g) ;
				}

			}
			else {
				//Si le joueur n'est pas encore initialise mais que l'on a choisit une resolution par IA et que le jeu n'a pas commence
				if ((this.quelJoueur == 2) && !(this.grille.enCours)){
					drawChoixIA(g);
				}
				//le joueur est initialise (une IA est choisie)
				else{
					//On demande a l'utilisateur de lancer la partie de l'IA avec la touche ESPACE
					if(!(this.grille.enCours) && this.grille.score>=0){
							g.setColor(new Color(250, 239, 226));
							g.fillRect(0, 0, LARGEUR, HAUTEUR);
							g.setColor(new Color(92, 82, 69));
							g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 18));
							g.drawString("Appuyez sur 'Espace' pour lancer l'IA", 40, 73 + 60 * Partie.TAILLE) ;
							g.drawString("Tapez 'Q' pour quitter", 100, 250 + 60 * Partie.TAILLE) ;
					}
					//la partie est en cours on affiche le score de l'IA (le nb de mouvements) et la tuile la plus grande sur la grille
					else{
						g.setColor(new Color(173, 159, 140)) ;
						g.fillRect(220 + 10 * Partie.TAILLE, 80, 85, 85) ;
						g.setColor(new Color(214, 201, 184)) ;
						g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 17));
						g.drawString("SCORE ", 230 + 10 * Partie.TAILLE, 110) ;
						g.setColor(Color.white) ;
						g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 27));
						g.drawString(" "+Math.abs(this.grille.score)+" ", 235 + 10 * Partie.TAILLE, 145) ;

						g.setColor(new Color(173, 159, 140)) ;
						g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 16));
						g.drawString("Mode : "+this.joueur.getPseudo(), 18 + 10 * Partie.TAILLE, 185) ;

						g.setColor(new Color(242, 215, 92)) ;
						g.fillRect(17 + 10 * Partie.TAILLE, 60, 100, 100) ;
						g.setColor(Color.white) ;
						g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 32));
						g.drawString("2048 ", 20 + 10 * Partie.TAILLE, 120) ;
						this.drawGrille(g);
						g.setColor(new Color(173, 159, 140)) ;
						g.fillRect(130 + 10 * Partie.TAILLE, 80, 85, 85) ;
						g.setColor(new Color(214, 201, 184)) ;
						g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 17));
						g.drawString("MAX ", 145 + 10 * Partie.TAILLE, 110) ;
						g.setColor(Color.white) ;
						g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 27));
						g.drawString(" "+this.grille.calculMax()+" ", 150 + 10 * Partie.TAILLE, 145) ;

						int resultat=0;
						if (!(this.grille.score < 0)){
							resultat = this.grille.deplacement(joueur.donnerCoup(this.grille));
							this.drawGrille(g) ;
							window.repaint();
							try {
								Thread.sleep(this.sleepms);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						if ((resultat == 2048) || (resultat == -1)){
							this.grille.enCours=false;
							this.grille.score=-Math.abs(this.grille.score);

						}
					}
				}
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Methode qui affiche le choix de joueur
	public void drawChoixJoueurs(Graphics g) {
		g.setColor(new Color(250, 239, 226));
		g.fillRect(0, 0, LARGEUR, HAUTEUR);
		g.setColor(new Color(92, 82, 69));
		g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 22));
		g.drawString("Bienvenue dans notre 2048", 50, 100) ;

		g.setColor(new Color(192, 156, 106));
		g.fillRoundRect(50, 150, 300, 100, 25, 25);
		g.setColor(new Color(92, 82, 69));
		g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 22));
		g.drawString("Utilisateur", 150, 210) ;

		g.setColor(new Color(192, 156, 106));
		g.fillRoundRect(50, 350, 300, 100, 25, 25);
		g.setColor(new Color(92, 82, 69));
		g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 22));
		g.drawString("Intelligence Artificielle", 90, 410) ;

		// On affiche les instructions...
		g.setColor(new Color(192, 156, 106));
		g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 15));
		g.drawString("Tapez 'U' ou 'I' pour choisir le mode jeu", 62, 480) ;
		g.drawString("Tapez 'Q' pour quitter", 120, 520) ;
	}


	/*---------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Methode qui affiche le plateau de jeu.
	public void drawGrille(Graphics g) {

		// Le fond de la grille en gris foncé
		g.setColor(new Color(173, 159, 140)) ;
        g.fillRect(17 + 10 * Partie.TAILLE, 200, 10 + 70 * Partie.TAILLE, 10 + 70 * Partie.TAILLE) ;

        // ...et on affiche toutes les cases du plateau.
        for(int l= 0; l < Grille.LIGNES ; l++) {
            for(int c = 0 ; c < Grille.COLONNES ; c++)
                drawTuile(g, this.grille.getTableau()[l][c], c * 70 + 27 + 10 * Partie.TAILLE, l * 70 + 210) ;
        }
	}

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Methode affichage graphique d'une tuile
	public void drawTuile(Graphics g, Tuile t, int x, int y) {
        int valTuile = t.getValeur() ;
		t.setCouleurFond();

        /* On recupere la longueur de la valeur de la case */
        int longueurValeur = String.valueOf(valTuile).length() ;

        // On affiche un carre aux coins arrondis beige gris clair
        g.setColor(new Color(214, 201, 184)) ;
        g.fillRoundRect(x, y, 60, 60, 5, 5) ;

        /* Si la case a une valeur on ira chercher celle-ci dans la classe Tuile (getCouleur) puis on l'affichera */
        if(((this.grille.enCours) && valTuile > 0) || (!(this.joueur instanceof Utilisateur) && valTuile > 0)) {
            g.setColor(t.getCouleur());
            g.fillRoundRect(x, y, 60, 60, 5, 5) ;
            g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 16)) ;

            /* Les tuiles de valeur 2 et 4 seront de texte de couleur blanc *
			 * les autres en noir */
			if((!this.grille.enCours)){//(this.joueur instanceof Utilisateur) &&
				g.setColor(Color.BLUE);
				g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 16));
			}
            else{
				if(t.getValeur() > 4)
            		g.setColor(Color.white);
				else
					g.setColor(new Color(92, 82, 69)); ;
			}

            // Pour l'affichage des valeurs, on se sert des coordonnees de la case concernee.
			g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 15)) ;
            g.drawString("" + valTuile, (int)(x + 27 - 3.3 * longueurValeur), y + 33) ;
        }

    }

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    	// Methode prenant en charge l'affichage des options de type de joueur : Utilisateur ou Intelligence Artificielle.
	public void drawChoixIA(Graphics g) {
		// On commence par mettre un fond gris sur la fenetre.
		  g.setColor(new Color(250, 239, 226));

		//On remplit un rectangle de la taille de l'ecran (parametres donnes en attributs) centre
		g.fillRect(0, 0, Partie.LARGEUR, Partie.HAUTEUR) ;

        // On change de police
  		g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 20));
  		g.setColor(new Color(192, 156, 106));

		/*On remplit les rectangles avec les options
		* ce sont des rectangles avec des coins arrondis*/
      	g.fillRoundRect(30, 100, 350, 100, 25, 25) ;
    	g.setColor(new Color(92, 82, 69));
    	g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 22));
    	g.drawString("Methode aleatoire ('A')", 80, 155) ;

    	g.setColor(new Color(192, 156, 106));
    	g.fillRoundRect(30, 220, 350, 150, 25, 25) ;
    	g.setColor(new Color(92, 82, 69));
    	g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 22));
    	g.drawString("Methode coin", 120, 260) ;
     	g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 19));
    	g.drawString("Methode coin aleatoire ('Z')", 40, 290) ;
    	g.drawString("Methode coin (simple) ('E')", 40, 320) ;
     	g.drawString("Methode maximisant cases vides ('R')", 40, 350) ;

		g.setColor(new Color(192, 156, 106));
		g.fillRoundRect(30, 390, 350, 100, 25, 25) ;
		g.setColor(new Color(92, 82, 69));
		g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 22));
		g.drawString("Methode minimax '(T)'", 80, 450) ;

		g.setColor(new Color(192, 156, 106));
		// Instructions
  		g.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 15));
  		g.drawString("Tapez les lettre indiquees pour choisir le mode d'IA", 10, 510) ;
  		g.drawString("Tapez 'Q' pour quitter", 100, 540) ;

		//Le titre du jeu
  		g.setColor(new Color(92, 82, 69));
  		g.setFont(new Font("Bebas Neue Regular", Font.BOLD, 30));
  		g.drawString("2048", 150, 70) ;
	}

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /*Methode de KeyListener*/

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    /* Methode gerant les actions a realiser selon la touche sur laquelle l'utilisateur appuiera */
    public void keyPressed(KeyEvent e) {

    	/* Le programme s'arrete automatiquement en fermant la fenetre de jeu en tapant sur la touche 'Q'*
    	 *on peut arreter ou quitter la partie a n'importe quel moment du jeu                            */
    	if(e.getKeyCode() == KeyEvent.VK_Q)
    		window.dispose() ;

    	//Joueur non initialise, on se trouve sur la page de choix de joueur
    	if(this.quelJoueur == 0) {
    		window.repaint();
    		switch(e.getKeyCode()) {
				case KeyEvent.VK_U :// Touche u pour le mode Utilisateur
						this.joueur = new Utilisateur() ;
						this.quelJoueur=1;
						System.out.println("jai cree le nouvel utilisateur humain : "+joueur.getPseudo());
						break ;
				case KeyEvent.VK_I : // Touche i pour l'Intelligence Artificielle
						this.quelJoueur=2;
						break ;
    		}
    	}
    	else {
    		//On passe en mode Utilisateur
    		if(this.joueur instanceof Utilisateur) {
				if (!(this.grille.enCours)){
					/*Afin de lancer une partie, l'utilisateur doit appuyer sur entree*/
					if(e.getKeyCode() == KeyEvent.VK_ENTER) { // Touche ENTRER
						init(joueur); //on initialise une grille avec des 0 et 2 tuiles de 2 avec deja un joueur de type utilisateur
						grille.enCours = true; //la partie est consideree comme enCours donc on bascule le flag
						window.repaint() ;
					}
          if(e.getKeyCode() == KeyEvent.VK_SPACE) { // Touche ESPACE
						this.quelJoueur=0;
						window.repaint() ;
					}
				}
				else {
					//Une partie est en cours
					/* L'utilisateur choisit donc ses mouvements a partir des fleches au clavier*/
					if(!(this.grille.calculMax() == 2048) || this.continuer) {

						switch(e.getKeyCode()) {
							case KeyEvent.VK_UP : // Fleche vers le haut
									this.grille.deplacementHaut();
									window.repaint() ;
									break ;
							case KeyEvent.VK_DOWN : // Fleche vers le bas
									this.grille.deplacementBas();
									window.repaint() ;
									break ;
							case KeyEvent.VK_LEFT : // Fleche vers la gauche
									this.grille.deplacementGauche();
									window.repaint() ;
									break ;
							case KeyEvent.VK_RIGHT : // Fleche vers la droite
									this.grille.deplacementDroit();
									window.repaint() ;
									break ;
						}
					}
				}
    	    }
		}

    		// Mode Intelligence Artificielle
    		if(this.quelJoueur == 2) {
				if (!(this.grille.enCours)){

    	    		window.repaint();

    	    		switch(e.getKeyCode()) {
    				case KeyEvent.VK_A : // Touche A pour la ia aleatoire
      	    			this.joueur = new IAalea();
      						init(joueur);
      						quelJoueur=100; //quelJoueur prend la valeur 100 pour dire que l'ia est initialisee donc on peut voir son jeu
      						window.repaint() ;
          				break ;
    				case KeyEvent.VK_Z : // Touche Z pour la methode du coin aleatoire
                  			this.joueur = new IAcoinRandom();
  	        				init(joueur);
      						quelJoueur=100;
      						window.repaint() ;
        					break ;
    				case KeyEvent.VK_E: // Touche E pour la methode du coin simple
        					this.joueur = new IAcoin() ;
    	        			init(joueur);
      						quelJoueur=100;
      						window.repaint() ;
        					break ;
    				case KeyEvent.VK_R : // Touche R pour la methode de prevision a 2 niveaux qui maximise le nombre de cases vides
							this.joueur = new IAcoinVide();
							init(joueur);
							quelJoueur=100;
							window.repaint();
        					break ;
    				case KeyEvent.VK_T : // Touche T pour la methode de prevision a 2 niveaux qui maximise le score
							this.joueur = new IAminimax(9);
							init(joueur);
							quelJoueur=100;
							window.repaint();
							break ;
    	    		}
    	    	}
    		}
			else {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) { // Touche ESPACE
					grille.enCours = true; //la partie est consideree comme enCours donc on bascule le flag
				}
			}
			window.repaint();
    	}

	/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        public static void main(String[] args){
        Partie myGame = new Partie();

    		System.out.println("Voulez vous jouer dans la console (1) ou avec une interface graphique (2)");
    		try (Scanner scan = new Scanner(System.in)) {
    			int optionAffichage = scan.nextInt();

    			while (!(optionAffichage == 1) && !(optionAffichage == 2)){
    				System.out.println("Voulez vous jouer dans la console (1) ou avec une interface graphique (2)");
    				optionAffichage = scan.nextInt();
    			}
    			int score=0;
    			switch (optionAffichage){
    				case 1 :
    					myGame.init();
    					score=myGame.jouerPartie();
    					System.out.println("score max du joueur "+myGame.joueur.getPseudo()+ " = " + score);
    					break;
    				case 2 :
    					myGame.getWindow().addKeyListener(myGame) ;
    					myGame.getWindow().getContentPane().add(myGame) ;
    					/* La fenetre disparait lors de sa fermeture mais le programme ne s'arrete pas.                              *
    					* Donc, on utilise la methode suivante pour forcer l'arret du programme suite a la fermeture de la fenetre. */
    					myGame.getWindow().setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE) ;
    					myGame.getWindow().setSize(Partie.LARGEUR, Partie.HAUTEUR) ;   // Donne les dimensions de la fenetre de jeu.
    					myGame.getWindow().setLocationRelativeTo(null) ; // Pour que la fenetre soit au centre de l'ecran.
    					myGame.getWindow().setVisible(true) ;    // Rend visible la fenetre de jeu.
    					myGame.getWindow().setResizable(false) ; // Ne permet pas de redimensionner la fenetre de jeu.
    					break;
				}
			/*Le test ci-dessus permet de realiser plusieurs parties de 2048 par des IA et d'avoir une statistiques sur les moyennes
			 des tuiles maximum atteinte pendant la partie*/
			/*
			int scoremoyen = 0;
			int scoremax = 0;
			int temp = 0;
			Joueur joueur = new IAalea();
			for (int i=0; i<100;i++){
			    System.out.println("jaffiche linitialisation");
			    myGame.init(joueur);

			    myGame.sleepms = 10;

			    System.out.println("jaffiche le jeu");
			    //myGame.jouerPartie("joueur = "+joueur.getPseudo()+", # partie = "+i+" meilleur score "+scoremax+" score moyen "+scoremoyen/(i+1));

			    temp=myGame.grille.calculMax();
			    scoremax= Math.max(temp,scoremax);
			    scoremoyen += temp;
			}
			System.out.println("score moyen du joueur "+joueur.getPseudo()+ " = " + scoremoyen/100+" le max du tableau est "+scoremax);
			*/
		}
    }

}
