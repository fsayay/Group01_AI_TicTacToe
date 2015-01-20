/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoeAI;

/**
 *
 * @author fabian
 */
import tictactoeAI.State;
public class Node {

    public Node[] child;
    public int childCount;
    private String vertex;
    public State state;

    public Node(String vertex)
    {
        this.vertex = vertex;
    }

    public Node(String vertex, int childlen)
    {
        this.vertex = vertex;
        childCount = 0;
        child = new Node[childlen];
    }

    public void addChildNode(Node adj)
    {
        adj.state = State.Unvisited;
        if(childCount < 30)
        {
            this.child[childCount] = adj;
            childCount++;
        }
    }

    public Node[] getChild()
    {
        return child;
    }

    public String getVertex()
    {
        return vertex;
    }

}
