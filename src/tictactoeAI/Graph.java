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
public class Graph {

    public int count; // num of vertices
    private Node vertices[];

    public Graph()
    {
        vertices = new Node[8];
        count = 0;
    }

    public void addNode(Node n)
    {
        if(count < 10)
        {
            vertices[count] = n;
            count++;
        }
        else
        {
            System.out.println("graph full");
        }
    }

    public Node[] getNode()
    {
        return vertices;
    }
}

