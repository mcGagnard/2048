# projet-jeu-2048

Pour compiler notre code 
Nous devons avoir tous les fichiers .java enregistre au meme endroit puis compiler par la commande : javac *.java
Puis pour l'execute : java Jeu


Au debut de chaque classe nous avons fait le choix de resumer tout ce qui pouvait se trouver dans cette classe, cest a dire les attributs, constructeurs, accesseurs, mutateurs et ses methodes en ordre. Ceci nous permettait de rendre le code plus lisible et lorsque nous cherchions une methode plus facile a trouver.

Nous repartissons le code de la maniere suivante :

Classe Jeu : notre classe main a execute 

Classe Joueur : Une classe abstraite qui a comme methode importante la methode donnerCoup (qui renvoie un string 'r', 'l', 'd', ou 'u') pour la direction       du prochain mouvement.
                A partir de la classe Joueur, nous pouvons creer les differents joueurs de la partie

Classe Utilisateur : Cette classe correspond a la classe du joueur. Sa methode donnerCoup est simplement entrer un des 4 string presentes precedemment au                   clavier et le mouvement sera effectue (dans la console). Avec l'interface graphique, le mouvement sera fait avec les fleches

Intelligence Artificielle
    Classe IAalea : La plus basique tire au sort une des 4 directions

    Classe IAcoinRandom : On choisit le coin inferieur a droite. On tire au sort un chiffre entre 0 et 99 et une probabilite plus forte sera donnee a la                    direction bas et droite et plus faible pour haut et gauche

    Classe IAcoin : Cette classe verifie si les mouvements sont possibles, d'abord a droite puis en bas (pour respecter le coin) et enfin en haut et a gauche.
    
    Classe IAcoinVide : La méthode donnerCoup de cette classe permets de faire un mouvement dans la grille pour faire la méthode du coin tout en maximisant          les cases vides de la grille.

    Classe IAminimax : Cette classe utilise l'algorithme minimax avec une méthode de maximisation du mouvement du joueur tout prenant le fait que                l'ordinateur souhaite le minimiser. Notre fonction utility permet de calculer la maximisation, la somme de toutes les tuiles du                 tableau / le nombre de cases occupées.

Classe Tuile : cette classe affecte une couleur a la valeur de chaque tuile pour l'interface graphique et on peut utiliser ses accesseurs pour les methodes       de grille

Classe Grille : notre plus grosse classe composee de toutes les petites methodes du jeu, les deplacements et toutes ses methode en lien, toutes les methodes utilisees par les differentes IA, etc ainsi que son affichage dans la console

Classe Partie : la classe qui s'occupe reellement du jeu, son initialisation et jouer, mais elle contient aussi le code de notre interface graphique

Classe Pair et PairMU : comme il sera explique dans le rapport et le code, nous avions besoin a deux reprises de recuperer plus d'une information donc nous                    sommes arrivees a la conclusion que le plus simple etait de coder une nouvelle classe


