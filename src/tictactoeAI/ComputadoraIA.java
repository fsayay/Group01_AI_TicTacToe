/**********************************************************************/
/*
 * Program that must playing the Tic Tac Toe game versus another people
 * or versus the computer. Implementing the breadth First and Depth First Search algorithm.
 *
/**********************************************************************/

package tictactoeAI;
import java.lang.Runnable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ComputadoraIA{
    
    public static int tiradas = 0;
    
    /*�rboles para los movimientos del Gato.*/
    class NodoG{
        
        
        /*Mejor movimiento.*/
        int mejorMovimiento;
        /*Nodos hijos.*/
        NodoG nodos[];
        /*Tablero del juego.*/
        public int tablero[];
        /*Turno de la computadora.*/
        boolean miTurno = false;
        /*Indice de la pocision.*/
        int indice;
        /*Ganador.*/
        int ganador = 0;

        /*Constructor.*/
        NodoG(){
            
            /*Inicializamos las variables.*/
            tablero = new int[9];
               
        }
    }
    
    /*Ra�z del �rbol*/
    NodoG arbol = new NodoG();
    
    /*Atributos.*/
    int[] tablero;
    
    /*Mi ficha.*/
    public final int miFICHA = 2;
    
    /*M�todo que nos devuelve los espacios disponibles.*/
    public int movDisponibles( int[] tablero ){
        int mov = 0;
        
        for ( int i = 0; i < 9; i ++ )
            if ( tablero[i] == 0 )
                mov++;
        
        return mov;
    }
    
    /*M�todo que nos devuelve los indices del tablero de las pocisiones vac�as.*/
    public int[] posVacias( int[] tablero ){
        /*Creamos el vector con los �ndices.*/
        int[] indices = new int[movDisponibles(tablero)];
        int indice = 0;
        
        /*Recorremos y guardamos los indices.*/
        for ( int i = 0; i < 9; i ++ ){
            if ( tablero[i] == 0 ){
                indices[indice] = i;
                indice++;
            }
        }
        return indices;
    }
    
    /*Clase que recibe el tablero actual.*/
    public int movimiento( int[] tablero ){
        /*Asignamos el tablero.*/
        this.tablero = tablero;
        tiradas ++;
        
        /*Copiamos el tablero a nuestro nodo ra�z.*/
        for ( int i = 0; i < 9; i ++ )
            this.arbol.tablero[i] = this.tablero[i];
        
        /*Calculamos el mejor movimiento del �rbol, desde las hojas hasta la raiz.*/
        movComputadora( arbol );
        
        /*Devolvemos el mejor movimiento.*/
        return arbol.mejorMovimiento;
    }

    /*M�todo recursivo, que genera los nodos con los movimientos.*/
    public void movComputadora( NodoG raiz ){
        
        /*N�mero de movimientos disponibles y sus indices en el tablero.*/
        int movimientos = movDisponibles(raiz.tablero);
        int indices[] = posVacias(raiz.tablero);
        int Max, Min;
        
        /*Inicializamos el nodo.*/
        raiz.nodos = new NodoG[movimientos];
        
        /*Verificamos si hay un ganador.*/
        int ganador = terminado(raiz.tablero);
        if ( ganador == 1 ) ganador = -1;
        else if ( ganador == 2 ) ganador = 1;
  
        if ( ganador!= 0 || movimientos == 0){
            raiz.ganador = ganador;
        }else{

            /*Creamos los datos de cada hijo.*/
            for( int i = 0; i < movimientos; i ++ ){
                
                /*Inicializamos los nodos hijos del �rbol.*/
                raiz.nodos[i] = new NodoG();
                
                /*Les pasamos su tablero.*/
                for ( int j = 0; j < 9; j ++ )
                    raiz.nodos[i].tablero[j] = raiz.tablero[j];
                                
                /*Creamos los diferentes movimientos posibles.*/
                if ( raiz.miTurno )
                    raiz.nodos[i].tablero[indices[i]] = 1;
                else
                    raiz.nodos[i].tablero[indices[i]] = 2;
                
                /*Cambiamos el turno de los hijos*/
                raiz.nodos[i].miTurno = !raiz.miTurno;
                
                
                /*Guardamos el indice de su movimiento.*/
                raiz.nodos[i].indice = indices[i];
                    
                movComputadora(raiz.nodos[i]);
                                
            }

            /*Minimax*/
            if (!raiz.miTurno)
                raiz.ganador = Max(raiz);
            else
                raiz.ganador = Min(raiz);
            
       }    

    }
   
    /*M�todo que calcula el M�XIMO de los nodos hijos de MIN*/
    public int Max( NodoG raiz ){
        int Max = -111;
        /*M�ximo para la computadora, buscamos el valor donde gane.*/
        for (int i = 0; i < raiz.nodos.length; i++){
          /*Preguntamos por un nodo con un valor alto MAX*/
            if (raiz.nodos[i].ganador > Max){
                /*Lo asignamos y pasamos el mejor movimiento a la ra�z.*/
                Max = raiz.nodos[i].ganador;
                raiz.mejorMovimiento = raiz.nodos[i].indice;
                /*Terminamos de buscar.*/
                if (Max == 1) break;
            }
         }
        
        /*Borramos los nodos.*/
        raiz.nodos = null;
        
        return Max;
    }
    
    /*M�todo que calcula el M�NIMO de los nodos hijos de MAX.*/
    public int Min( NodoG raiz ){
        int Min = 111;
        /*M�nimo para el jugador*/
        for (int i = 0; i < raiz.nodos.length; i++)
          if (raiz.nodos[i].ganador < Min ){
            Min = raiz.nodos[i].ganador;
            raiz.mejorMovimiento = raiz.nodos[i].indice;
            if (Min == -1) break;
          }
        
        /*Borramos los nodos.*/
        raiz.nodos = null;
        
        return Min;
    }
    
    public void dfs(Node root)
    {       
        //Avoid infinite loops
        if(root == null) return;

        System.out.print(root.getVertex() + "\t");
        root.state = State.Visited;
        
        //for every child
        for(Node n: root.getChild())
        {
            //if childs state is not visited then recurse
            if(n.state == State.Unvisited)
            {
                dfs(n);
            }
        }
    }

    public void bfs(Node root)
    {
        //Since queue is a interface
        Queue<Node> queue = new LinkedList<Node>();

        if(root == null) return;

        root.state = State.Visited;
         //Adds to end of queue
        queue.add(root);

        while(!queue.isEmpty())
        {
            //removes from front of queue
            Node r = queue.remove(); 
            System.out.print(r.getVertex() + "\t");

            //Visit child first before grandchild
            for(Node n: r.getChild())
            {
                if(n.state == State.Unvisited)
                {
                    queue.add(n);
                    n.state = State.Visited;
                }
            }
        }


    }
                
    /*M�todo que dice si el juego est� terminado.*/
    /*Regresa 0 si nadie gana, 1 si gana jugador 1 y 2 si gana jugador 2*/
    public int terminado( int[] tablero ){
        /*Comprobamos si el juego termin�.*/
        /*Filas*/
        if ( tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[3] == tablero[4] && tablero[3] == tablero[5]  && tablero[3] != 0  )
            return tablero[3];
        else if ( tablero[6] == tablero[7] && tablero[6]== tablero[8]  && tablero[6] != 0 )
            return tablero[6];
        /*Columnas*/
        else if( tablero[0] == tablero[3] && tablero[0] == tablero[6]  && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[1] == tablero[4] && tablero[1] == tablero[7]  && tablero[1] != 0  )
            return tablero[1];
        else if ( tablero[2] == tablero[5] && tablero[2] == tablero[8]  && tablero[2] != 0 )
            return tablero[2];
        /*Diagonales*/
        else if ( tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] !=0 )
            return tablero[0];
        else if ( tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0 )
            return tablero[2];
        
        return 0;
        
    }
    
    /*M�todo que nos dice si gana la computadora.*/
    public boolean puedoGanar(int[] tablero){
        return terminado(tablero) == 2;
    }
    
    /*M�todo que nos dice si pierde la computadora.*/
    public boolean puedoPerder(int[] tablero){
        return terminado(tablero) == 1;
    }
    
    /*M�todo que imprime un vector como un gato. xD*/
    public void imprime(int[] gato){
        for ( int i = 0; i < 9; i ++ ){
            System.out.print(gato[i]+"");
            if ( i == 2 || i == 5 )
                System.out.println();
        }
        
        System.out.println("\r\n");
    }
}
