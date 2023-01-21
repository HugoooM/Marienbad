/**
 * Jeu de Marienbad
 * @author Hugo Montiège
 */
class Marienbad2{
    void principal(){
        //testEstFini();
        //testConversion();
        //testAffichage();
        //testEnlever();
        //testFin();
        //testJeuOrdi();
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
        char premierJoueur;
        int[] allumettes = {1,3,5,7};
        String nom = SimpleInput.getString("Donnez le nom du joueur 1 : ");
        String j1;
        String j2;
        
        do{
            premierJoueur=SimpleInput.getChar("Qui joue en premier ? Vous (\"a\") ou l'ordinateur (\"b\") ? ");
        } while (premierJoueur!='a' && premierJoueur!='b');
        
        
        if (premierJoueur=='b'){
            j1 = "Ordinateur";
            j2 = nom;
        }  
        else{
            j1 = nom;
            j2 = "Ordinateur";
        }    
        
        
        while(estFini(allumettes)==false){
            affichage(allumettes);
            if (nbTour%2==0){
                joueur = j1;
            }
            else {
                joueur = j2;
            }
            enlever(allumettes, joueur);
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
    void affichage(int [] allumettes){
        int ligne = 0;
        for(int i=0; i<allumettes.length; i++){
            System.out.print(ligne + " : ");
            ligne ++;
            for (int j=0; j<allumettes[i]; j++){
                System.out.print("| ");
            }
            System.out.println(" ");
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
     * Permet de savoir si le jeu est terminer ou non
     * @param allumettes tableau d'entier
     * @return vrai ssi le jeu est terminer cad qu'il n'y a plus d'allumettes
     */
    boolean estFini(int [] allumettes){
        boolean res = true;
        for (int i = 0; i<allumettes.length ; i++){
            if (allumettes[i]!=0){
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
    void enlever (int [] allumettes, String joueur){
        int ligne;
        int nballumettes;
        
        System.out.println("C'est au tour de " + joueur);
        
        if (joueur!="Ordinateur"){        

            do {
                ligne = SimpleInput.getInt("Sur quelle ligne voulez vous enlever des allumettes ? ");
            } while (ligne<0 || ligne>3 || allumettes[ligne]==0);
            
            do {
                nballumettes = SimpleInput.getInt("Combien d'allumettes voulez vous enlever ? ");
            } while (nballumettes<1 || nballumettes>allumettes[ligne]);
            
            allumettes[ligne]=allumettes[ligne]-nballumettes;
        }
        else {
            ligne = jeuOrdi(allumettes)[0];
            nballumettes = jeuOrdi(allumettes)[1];
            System.out.println("Sur quelle ligne voulez vous enlever des allumettes ? " + ligne);
            System.out.println("Combien d'allumettes voulez vous enlever ? " + nballumettes);
            allumettes[ligne]=allumettes[ligne]-nballumettes;
        }
        
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
     * Permet à l'Ordinateur de savoir sur quelle ligne jouer et combien d'allumettes enlever
     * @param allumettes un tableau d'entier comprenant le nombre d'allumettes sur chaque lignes
     * @return ret un tableau de 2 d'entier où le premier entier est la ligne où l'ordinateur va jouer et le deuxieme le nombre
     * d'allumettes à enlever 
     */
    int[] jeuOrdi(int [] allumettes){
        int [] ret = new int [2];
        int [] possibiliteAllumettes = new int [allumettes.length];
        int i=0;
        int j=1;
        boolean gagnant=false;
        int nbLigne=0;
        
        //detecte si il reste une seule allumette et la joue
        for (int compteur=0; compteur<allumettes.length; compteur++){
            if (allumettes[compteur]!=0){
                nbLigne++;
            }
        }
        if (nbLigne==1){
            for (int compteur=0; compteur<allumettes.length; compteur++){
                if (allumettes[compteur]!=0){
                    ret[0]=compteur;
                    ret[1]=1;
                }
            }
        }
        
        
        
        //Essai de jouer en utilisant la strategie gagnante
        while (i<allumettes.length && gagnant==false){
            for (int k=0; k<allumettes.length;k++){
                possibiliteAllumettes[k]=allumettes[k];
            }       
            
            while (j<=allumettes[i] && gagnant==false){
                for (int k=0; k<allumettes.length;k++){
                    possibiliteAllumettes[k]=allumettes[k];
                } 
                
                possibiliteAllumettes[i]=possibiliteAllumettes[i]-j;
                gagnant=conversion(possibiliteAllumettes);
                if (gagnant==true){
                    ret[0]=i;
                    ret[1]=j;
                }
                
                j++;
            }
            
            j=1;
            i++;
        }
            
        //si ce n'est pas possible de jouer en utilisant la stratégie gagnante, alors l'ordinateur va enlever une allumettes
        //sur la derniere lignes comprennant encore des allumettes
        if (ret[1] == 0){
            ret[1]=1;
            for (int k=0; i<allumettes.length;k++){
                if (allumettes[k]!=0){
                    ret[0]=k;
                }
            }
        }
            
        
        return ret;
    }
    
    /**
     * Teste la méthode jeuOrdi()
     */
     void testJeuOrdi(){
         System.out.println();
         System.out.println("***testJeuOrdi()");
         
         
         int [] a = {0,3,5,7};
         int [] b = {1,2,3,4};
         int [] c = {1,1};
         int [] d = {3,4};
         testCasJeuOrdi (a,c);
         testCasJeuOrdi (b,d);
         
     }
     
     /**
      * teste un appel de jeuOrdi()
      * @param t tableau d'entier contenant le nombre d'allumettes
      * @param result resultat attendu
      * Ne renvoit rien
      **/
      void testCasJeuOrdi (int [] t, int [] result) {
          // Arrange
          System.out.print ("jeuOrdi ({");
          for (int i=0; i<t.length; i++){
              System.out.print(t[i] + ",");
          }
          System.out.print("})= ");
          
          for (int i=0; i<result.length; i++){
              System.out.print(result[i]);
          }
          System.out.println("\t : ");
          // Act
          int [] resExec = jeuOrdi(t);
          // Assert
          
          boolean egalite = true;
          int j = 0;
          
          
          while (j<result.length && egalite==true){
              if (resExec[j]!=result[j]){
                  egalite = false;
              }
              j++;
          }
          if (egalite == true){
              System.out.println ("OK");
          }
          else {
              System.err.println ("ERREUR");
          }
      }
    
    /**
     * Converti une liste en binaire et renvoie true ssi la somme des élements de la liste collone par colonne est pair
     * @param allumettes un tableau d'entier stockant le nombre d'allumettes sur chaque ligne
     * @return ret un boolean, true ssi la somme de toutes les colonnes en binaires est paire
     */
     
    boolean conversion(int [] allumettes){
        boolean ret;
        int [] binaire = new int [allumettes.length];
        int[] somme = new int [3];
        
        for (int i = 0 ; i<allumettes.length ; i++){
            binaire[i]=Integer.valueOf(Integer.toBinaryString(allumettes[i]));
        }
        
        somme[0]= (binaire[2]/100) + (binaire[3]/100);
        
        int tmp=binaire[1]/10;
        for (int i=2; i<binaire.length; i++){
            int division=binaire[i]/10;
            if (division>=10){
                division = division-10;
            }
            tmp = tmp + division;
        }
        somme[1]=tmp;
        
        tmp=0;
        for (int i=0; i<binaire.length; i++){
            tmp = tmp + (binaire[i]%2);
        }
        
        somme[2]=tmp;
        
        if (somme[0]%2==0 && somme[1]%2==0 && somme[2]%2==0){
            ret = true;
        } 
        else{
            ret = false;
        }
            
        return ret;
        
    }
    
    
    /**
     * Teste la méthode estFini()
     */
     void testConversion(){
         System.out.println();
         System.out.println("***testconversion()");
         
         
         int [] a = {1,3,5,7};
         int [] b = {0,11,100,111};
         int [] c = {0,11,101,111};
         int [] d = {1,10,11,100};
         testCasConversion (a,true);
         testCasConversion (b,true);
         testCasConversion (c,false);
         testCasConversion (d,false);
         
     }
     
     /**
      * teste un appel de conversion()
      * @param t tableau d'entier contenant le nombre d'allumettes
      * @param result resultat attendu
      * Ne renvoit rien
      **/
      void testCasConversion (int [] t, boolean result) {
          // Arrange
          System.out.print ("conversion ({");
          for (int i=0; i<t.length; i++){
              System.out.print(t[i] + ",");
          }
          System.out.print("})= " + result + "\t : ");
          // Act
          boolean resExec = conversion(t);
          // Assert
          if (resExec == result){
              System.out.println ("OK");
          }
          else {
              System.err.println ("ERREUR");
          }
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
      * @param t tableau d'entier contenant le nombre d'allumettes
      * @param result resultat attendu
      * Ne renvoit rien
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
}
