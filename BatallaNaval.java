import java.util.Scanner;

class BatallaNaval{
    static final int MAX_FIL = 5;
    static final int MAX_COL = 5;

    static final int MAX_BARC = 7;

    static char[][] tablero;
    static char[][] barcosEnemigos;
    static int[] barco;
    static int[] destruir;

    static final char NO_VISTO = '*';
    static final char ATINE = '!';
    static final char NO_BARCO = 'X';
    static final char VACIO = 'V';
    static final char BARCO = 'B';

    static Scanner scanPos = null;
    static int posRecibida = -1;

    static Scanner scanMenu = null;

    static final int SEGUIR = 1;
    static final int SALIR = 0;
    static final int DEF_OPC = -1;
    static int opcionMenu = DEF_OPC;

    static final boolean DEF_GANO = false;
    static final boolean GANO = true;
    static boolean gano = DEF_GANO;

    static final int NINGUN_BARCO = 0;
    static int barcosDestruidos = NINGUN_BARCO;


    public static void mostrarTablero(){   
        System.out.print("\n*** Barcos destruidos: " + barcosDestruidos);     
        System.out.println(" ***\n\n    [0]  [1]  [2]  [3]  [4]");
        for(int i = 0; i<MAX_FIL ; i++){
            for(int j = 0; j<MAX_COL ; j++){
                if(j==0){
                    System.out.print("["+i+"] ["+tablero[i][j]+"] ");
                }else{
                    System.out.print(" ["+tablero[i][j]+"] ");
                }
            }
            System.out.println();
        }
        System.out.println("\n• No lo he visto[*]  • Le atine [!]  • No hay barco [X]");
    }

    static void pedirCoordenadas(){
        for(int i = 0; i<MAX_BARC; i++){
            System.out.println("Ingrese pos Fila para el barco nº " + (i+1));
            barco[0] = scanPos.nextInt();        
            System.out.println("Ingrese pos Columna para el barco nº " + (i+1));
            barco[1] = scanPos.nextInt();        
            barcosEnemigos[barco[0]][barco[1]] = BARCO;
        }
    }

    /*Pre: Matriz correctamente inicializada. El valor ingresado debe estar dentro del rango de la matriz
     *Post: Guarda las coordenadas que ingreso el usuario para intentar destruir un barco
    */
    static void pedirCoordenadasJugador(){
        System.out.println("-----------------------------------------------\n");
        System.out.printf("Ingrese coordenadas para intentar destuir un barco:\n");
        System.out.printf("Fila:");
        destruir[0] = scanPos.nextInt();
        System.out.print("Columna:");
        destruir[1] = scanPos.nextInt();
    }

    static void comprobarDestruccion(){
        System.out.print("*** Resultado: ");
        if(barcosEnemigos[destruir[0]][destruir[1]] == BARCO){
            System.out.println("DESTRUISTE UN BARCO ***");
            tablero[destruir[0]][destruir[1]] = ATINE;
            barcosEnemigos[destruir[0]][destruir[1]] = VACIO;
            barcosDestruidos++;
        }else if(barcosEnemigos[destruir[0]][destruir[1]] == VACIO){
            System.out.println("Barco actualmente destruido ***");
        }else{
            tablero[destruir[0]][destruir[1]] = NO_BARCO;
            System.out.println("No hay nada xd ***");
        }
    }

    static void opcionSalir(){
        if(barcosDestruidos != MAX_BARC){
            System.out.println("\n\t\t***Menu***");
            System.out.println("• Seguir jugando [1]");
            System.out.println("• Me enoje >:c   [0]");
            opcionMenu = scanMenu.nextInt();
            System.out.println("-----------------------------------------------\n");
        }else{
            gano = GANO;
            System.out.println("\t\tGANASTE !!! :D");
        }
        
    }

    static void comenzarJuego(){
        scanPos = new Scanner(System.in);
        scanMenu = new Scanner(System.in);
        pedirCoordenadas();
        mostrarTablero();
        while(opcionMenu != SALIR && !gano){
            pedirCoordenadasJugador();
            comprobarDestruccion();
            mostrarTablero();
            opcionSalir();
        }
    }

    static void llenarTablero(){
        for(int i = 0; i<MAX_FIL ; i++){
            for(int j = 0; j<MAX_COL ; j++){
                tablero[i][j] = NO_BARCO;
            }
        }
    }

    public static void main(String arg[]){
        tablero = new char[MAX_FIL][MAX_COL];
        barcosEnemigos = new char[MAX_FIL][MAX_COL];
        barco = new int[2];
        destruir = new int[2];
        llenarTablero();
        comenzarJuego();
    }
}
