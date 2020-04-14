import java.util.Scanner;

class BatallaNaval{
    static final int MAX_FIL = 5;
    static final int MAX_COL = 5;

    static final int MAX_BARC = 7;

    static char[][] tablero;
    static char[][] barcosEnemigos;
    static int[] barco;
    static int[] destruir;

    static Scanner scanPos = null;
    static int posRecibida = -1;

    static Scanner scanMenu = null;
    static int opcRecibida = -1;

    static final int SALIR = 0;
    static final int DEF_OPC = -1;
    static int opcionMenu = DEF_OPC;


    public static void mostrarTablero(){
        for(int i = 0; i<MAX_FIL ; i++){
            for(int j = 0; j<MAX_COL ; j++){
                System.out.print(" ["+tablero[i][j]+"] ");
            }
            System.out.println();
        }
    }

    static void pedirCoordenadas(){
        for(int i = 0; i<2 ; i++){
            System.out.println("Ingrese pos Fila para el barco nº " + (i+1));
            barco[0] = scanPos.nextInt();        
            System.out.println("Ingrese pos Columna para el barco nº " + (i+1));
            barco[1] = scanPos.nextInt();        
            barcosEnemigos[barco[0]][barco[1]] = 'B';
        }
    }

    static void pedirCoordenadasJugador(){
        System.out.println("Ingrese coordenadas para intentar destuir un barco:");
        System.out.print("Fila:");
        destruir[0] = scanPos.nextInt();
        System.out.print("Columna:");
        destruir[1] = scanPos.nextInt();
    }

    static void comprobarDestruccion(){
        if(barcosEnemigos[destruir[0]][destruir[1]] == 'B'){
            System.out.println("DESTRUISTE UN BARCO");
            tablero[destruir[0]][destruir[1]] = '!';
        }else{
            tablero[destruir[0]][destruir[1]] = 'X';
            System.out.println("No hay nada xd");
        }
    }

    static void comenzarJuego(){
        scanPos = new Scanner(System.in);
        scanMenu = new Scanner(System.in);
        pedirCoordenadas();
        mostrarTablero();
        while(opcionMenu != SALIR){
            pedirCoordenadasJugador();
            comprobarDestruccion();
            mostrarTablero();
        }
    }

    static void llenarTablero(){
        for(int i = 0; i<MAX_FIL ; i++){
            for(int j = 0; j<MAX_COL ; j++){
                tablero[i][j] = '*';
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
        mostrarTablero();
    }
}
