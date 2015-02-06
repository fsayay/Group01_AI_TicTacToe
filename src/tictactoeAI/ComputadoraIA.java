/**********************************************************************/
/*
 * Program that must playing the Tic Tac Toe game versus another people
 * or versus the computer. Implementing the breadth First and Depth First Search algorithm.
 *
/**********************************************************************/

package tictactoeAI;
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ComputadoraIA{
    public final int DFS = 1;
    public final int BFS = 2;
    private int algorithm = 0;
    
    /*Árboles para los movimientos del Tic Tac Toe. */
    class NodeG{
        /*The Best move.*/
        int bestMove;
        /*Nodes children.*/
        NodeG nodes[];
        /*Game board.*/
        public int board[];
        /*Turno de la computadora.*/
        boolean myTurn = false;
        /*Indice de la pocision.*/
        int index;
        /*Winner.*/
        int winner = 0;
        /*State.*/
        State state;
        

        /*Constructor.*/
        NodeG(){            
            /*Inicializamos las variables.*/
            board = new int[9];               
        }
        
        public int[] getBoard(){
            return this.board;
        }
        
        public NodeG[] getChild(){
            return this.nodes;
        }
    }
    
    /*tree root*/
    NodeG tree = new NodeG();
    
    /*Atributs.*/
    int[] board;
        
    public ComputadoraIA(int algorithm){
        this.algorithm = algorithm;
    }
    
    /*Metodo que nos devuelve los espacios disponibles.*/
    public int availableMoves( int[] board ){
        int mov = 0;
        for ( int i = 0; i < 9; i ++ )
            if ( board[i] == 0 )
                mov++;
        return mov;
    }
    
    /*Metodo que nos devuelve los indices del tablero de las pocisiones vacías.*/
    public int[] emptyPositions( int[] board ){
        /*Creamos el vector con los índices.*/
        int[] indices = new int[availableMoves(board)];
        int index = 0;
        
        /*Recorremos y guardamos los indices.*/
        for ( int i = 0; i < 9; i ++ ){
            if ( board[i] == 0 ){
                indices[index] = i;
                index++;
            }
        }
        return indices;
    }
    
    /*Method that recive the board with current state.*/
    public int move( int[] board ){
        Queue<NodeG> queue = new PriorityQueue<NodeG>();
        /*Asignamos el tablero.*/
        
        this.board = board;
        
        
        /*Copiamos el tablero a nuestro nodo raíz.*/
        for ( int i = 0; i < 9; i ++ )
            this.tree.board[i] = this.board[i];
               
        if(this.algorithm == DFS)    
            computerMoveDFS( tree );
        else
            computerMoveBFS( tree );
        
        /*return your best move*/
        return tree.bestMove;       
    }
   
    /*Method to find the best position with DFS algorithm.*/
    public void computerMoveDFS( NodeG root ){
        
        /*Number of available moves and their indices on the board.*/
        int movimientos = availableMoves(root.board);
        int indices[] = emptyPositions(root.board);
        
        /*Initialize the node. */
        root.nodes = new NodeG[movimientos];
        
        /*Check for a winner.*/
        int ganador = finished(root.board);
        if ( ganador == 1 ) ganador = -1;
        else if ( ganador == 2 ) ganador = 1;
  
        if ( ganador!= 0 || movimientos == 0){
            root.winner = ganador;
        }else{

            /*We create the data for each child.*/
            for( int i = 0; i < movimientos; i ++ ){
                
                /*Initialize the child nodes of the tree.*/
                root.nodes[i] = new NodeG();
                
                /*We pass the board.*/
                for ( int j = 0; j < 9; j ++ )
                    root.nodes[i].board[j] = root.board[j];
                                
                /*We create different possible moves.*/
                if ( root.myTurn )
                    root.nodes[i].board[indices[i]] = 1;
                else
                    root.nodes[i].board[indices[i]] = 2;
                
                /*We changed the turn of the children*/
                root.nodes[i].myTurn = !root.myTurn;
                
                
                /*Save index move.*/
                root.nodes[i].index = indices[i];
                    
                computerMoveDFS(root.nodes[i]);
                                
            }

            /*Choose the best move*/
            if (!root.myTurn)
                root.winner = Winner(root);
            else
                root.winner = Loser(root);
  
       }    

    }
   
    public int Winner( NodeG root ){
        int win = -1;
        /*We find where the computer win.*/
        for (int i = 0; i < root.nodes.length; i++){
          
            if (root.nodes[i].winner > win){
               
                win = root.nodes[i].winner;
                root.bestMove = root.nodes[i].index;
               
                if (win == 1) break;
            }
         }
        
        /*Clean nodes.*/
        root.nodes = null;
        
        return win;
    }
    
    public int Loser( NodeG root ){
        int lost = 1;
        /*Value where the player lost*/
        for (int i = 0; i < root.nodes.length; i++)
          if (root.nodes[i].winner < lost ){
            lost = root.nodes[i].winner;
            root.bestMove = root.nodes[i].index;
            if (lost == -1) break;
          }
        
        root.nodes = null;
        
        return lost;
    }
     
    /*Method to find the best position with BFS algorithm.*/
    public void computerMoveBFS( NodeG root ){
        
        /*Number of available moves and their indices on the board.*/
        int movimientos = availableMoves(root.board);
        int indices[] = emptyPositions(root.board);
        
        /*Initialize the node. */
        root.nodes = new NodeG[movimientos];
        
        /*Check for a winner.*/
        int ganador = finished(root.board);
        if ( ganador == 1 ) ganador = -1;
        else if ( ganador == 2 ) ganador = 1;
  
        if ( ganador!= 0 || movimientos == 0){
            root.winner = ganador;
        }else{

            /*We create the data for each child.*/
            for( int i = 0; i < movimientos; i ++ ){
                
                /*Initialize the child nodes of the tree.*/
                root.nodes[i] = new NodeG();
                
                /*We pass the board.*/
                for ( int j = 0; j < 9; j ++ )
                    root.nodes[i].board[j] = root.board[j];
                                
                /*We create different possible moves.*/
                if ( root.myTurn )
                    root.nodes[i].board[indices[i]] = 1;
                else
                    root.nodes[i].board[indices[i]] = 2;
                
                /*We changed the turn of the children*/
                root.nodes[i].myTurn = !root.myTurn;
                
                
                /*Save index move.*/
                root.nodes[i].index = indices[i];
                    
                
            }
            /*Choose the best move*/
            if (!root.myTurn)
                root.winner = Winner(root);
            else
                root.winner = Loser(root); 
       }
    } 

    public int finished( int[] board ){
        /*Rows*/
        if ( board[0] == board[1] && board[0] == board[2] && board[0] != 0 )
            return board[0];
        else if ( board[3] == board[4] && board[3] == board[5]  && board[3] != 0  )
            return board[3];
        else if ( board[6] == board[7] && board[6]== board[8]  && board[6] != 0 )
            return board[6];
        /*Columns*/
        else if( board[0] == board[3] && board[0] == board[6]  && board[0] != 0 )
            return board[0];
        else if ( board[1] == board[4] && board[1] == board[7]  && board[1] != 0  )
            return board[1];
        else if ( board[2] == board[5] && board[2] == board[8]  && board[2] != 0 )
            return board[2];
        /*Diagonals*/
        else if ( board[0] == board[4] && board[0] == board[8] && board[0] !=0 )
            return board[0];
        else if ( board[2] == board[4] && board[2] == board[6] && board[2] != 0 )
            return board[2];
        
        return 0;
        
    }      
}