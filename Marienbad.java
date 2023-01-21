/**
 * Jeu de Marienbad
 * @author Hugo Montiège
 */
class Marienbad{
    void principal(){
        testEstFini();
        testAffichage();
        testEnlever();
        testFin();
        jeu();
    }
    
    /**
     * Permet d'exécuter le jeu et lance toutes les instructions une à une tant que le jeu n'est pas fini
     * Ne prend rien en paramètre
     * Ne retourne rien
     */
    void jeu(){
        int nbTour = 0;
        String joueur;
        int[] alumettes = {1,3,5,7};
        String j1 = SimpleInput.getString("Donnez le nom du joueur 1 : ");
        String j2 = SimpleInput.getString("Donnez le nom du joueur 2 : ");
        
        while(estFini(alumettes)==false){
            affichage(alumettes);
            if (nbTour%2==0){
                joueur = j1;
            }
            else {
                joueur = j2;
            }
            enlever(alumettes, joueur);
            nbTour ++;
        }
        
        nbTour = nbTour-1;
        if (nbTour%2==0){
            joueur = j1;
        }
        else {
            joueur = j2;
        }
        
        System.out.println(fin(joueur));
    }
    /**
     * Affiche les allumettes restante du jeu
     * @param allumettes est un tableau d'entier. Chaque élement correspond au nombre d'alumettre restante sur chaque ligne
     * Ne retourne rien
     */
    void affichage(int [] alumettes){
        int ligne = 0;
        for(int i=0; i<alumettes.length; i++){
            System.out.print(ligne + " : ");
            ligne ++;
            for (int j=0; j<alumettes[i]; j++){
                System.out.print("| ");
            }
            System.out.println(" ");
            
        }
    }
    
    /**
     * Permet de savoir si le jeu est terminer ou non
     * @param allumettes tableau d'entier
     * @return vrai ssi le jeu est terminé cad qu'il n'y a plus d'allumette
     */
    boolean estFini(int [] alumettes){
        boolean res = true;
        for (int i = 0; i<alumettes.length ; i++){
            if (alumettes[i]!=0){
                res=false;
            }
        }
        return res;
    }
    
    /**
     * Permet d'enlever des allumettes à la ligne choisi par le joueur
     * @param allumettes un tableau d'entier contenant le nombre d'allumettes sur chaque ligne
     * @param joueur le nom du joueur qui joue
     * Ne retourne rien
     */
    void enlever (int [] alumettes, String joueur){
        int ligne;
        int nbAlumettes;
        
        System.out.println("C'est au tour de " + joueur);
        do {
            ligne = SimpleInput.getInt("Sur quelle ligne voulez vous enlever des alumettes ? ");
        } while (ligne<0 || ligne>3 || alumettes[ligne]==0);
        
        do {
            nbAlumettes = SimpleInput.getInt("Combien d'alumettes voulez vous enlever ? ");
        } while (nbAlumettes<1 || nbAlumettes>alumettes[ligne]);
        
        alumettes[ligne]=alumettes[ligne]-nbAlumettes;
        
    }
    
    /**
     * Permet d'afficher le nom du joueur gagnant
     * @param joueur le nom du joueur
     * @return une phrase afin de féliciter le gagnant
     */
    String fin(String joueur){
        String res = "Bien joué, " + joueur + " à gagner";
        
        return res;
    }
    
    

    /**
     * Teste la méthode estFini()
     */
     void testEstFini(){
         System.out.println();
         System.out.println("***testEstFini()");
         
         
         int [] a = {0,0,0,0};
         int [] b = {1,3,5,7};
         int [] c = {1,-3,5,15};
         testCasEstFini (a,true);
         testCasEstFini (b,false);
         testCasEstFini (c,false);
         
     }
     
     /**
      * teste un appel de estfini()
      * @param t tableau d'entier contenant le nombre d'alumettes
      * @param result resultat attendu
      **/
      void testCasEstFini (int [] t, boolean result) {
          // Arrange
          System.out.print ("estFini ({");
          for (int i=0; i<t.length; i++){
              System.out.print(t[i] + ",");
          }
          System.out.print("})= " + result + "\t : ");
          // Act
          boolean resExec = estFini(t);
          // Assert
          if (resExec == result){
              System.out.println ("OK");
          }
          else {
              System.err.println ("ERREUR");
          }
      }
      
      
    /**
     * Teste la méthode affichage()
     */
     void testAffichage(){
         System.out.println();
         System.out.println("***testAffichage()");
         
         
         int [] a = {1,3,5,7};
         int [] b = {0,0,0,0};
         int [] c = {0,4,5,1};
         testCasAffichage (a);
         testCasAffichage (b);
         testCasAffichage (c);
         
     }    

    /**
      * teste un appel de affichage()
      * @param t tableau d'entier contenant le nombre d'allumettes
      **/
      void testCasAffichage (int [] t) {
          
          // Arrange
          System.out.print ("affichage ({");
          for (int i=0; i<t.length; i++){
              System.out.print(t[i] + ",");
          }
          System.out.println("})");
          affichage(t);
      }
      
    /**
     * Teste la méthode enlever()
     */
     void testEnlever(){
         System.out.println();
         System.out.println("***testEnlever()");
         
         
         int [] a = {1,3,5,7};
         testCasEnlever (a,"test");
         testCasEnlever (a,"Ordinateur");
         
     }    

    /**
      * teste un appel de enlever()
      * @param t tableau d'entier contenant le nombre d'allumettes
      * @param joueur le nom du joueur
      * Ne renvoit rien
      **/
      void testCasEnlever (int [] t, String joueur) {
          
          // Arrange
          System.out.print ("enlever ({");
          for (int i=0; i<t.length; i++){
              System.out.print(t[i] + ",");
          }
          System.out.println("}, " + joueur + ")");
          enlever(t, joueur);
          affichage(t);
      }
      
    /**
     * Teste la méthode fin()
     * Ne prend pas de parametre
     * Ne renvoit rien
     */
     void testFin(){
         System.out.println();
         System.out.println("***testFin()");
         testCasFin ("Test");
         testCasFin ("");
         
     }
     
     /**
      * teste un appel de fin()
      * @param nom le nom du joueur
      * Ne renvoit rien
      **/
      void testCasFin (String nom) {
          // Arrange
          System.out.print ("fin (" + nom + ")= \t : ");
          // Act
          System.out.println(fin(nom));
      }
}
