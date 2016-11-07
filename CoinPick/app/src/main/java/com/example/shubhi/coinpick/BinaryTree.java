package com.example.shubhi.coinpick;
//package com.example.shubhi.coinpick;

import android.util.Log;

/**
 * Created by Shubhi on 05-11-2016.
 */

class BTNode
{
    BTNode left,right;
    int data,score,count;

    public BTNode(){
        left=null;
        right=null;
        data=0;
        score=0;
        count=0;

    }
    public BTNode(int n,int s,int c)
    {
        left=null;
        right=null;
        data=n;
        score=s;
        count=c;
        //count

    }
    public void setLeft(BTNode n)
    {
        left = n;
    }
    /* Function to set right node */
    public void setRight(BTNode n)
    {
        right = n;
    }
    /* Function to get left node */
    public BTNode getLeft()
    {
        return left;
    }
    /* Function to get right node */
    public BTNode getRight()
    {
        return right;
    }
    /* Function to set data to node */
    public void setData(int d)
    {
        data = d;
    }
    /* Function to get data from node */
    public int getData()
    {
        return data;
    }
}
class BT{
    private BTNode root;
    public BT()
    {
        root = null;
    }
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Functions to insert data */
    public void insert(int data)
    {
        root = insert(root, data,0,1);
    }
    public BTNode getRoot()
    {
        return root;
    }

    /* Function to insert data recursively */
    private BTNode insert(BTNode node, int data,int score,int count)
    {
        int l,r;
        l=count+1;
        r=count+1;
        if(data<0)
        {
            return node;
        }
        if (node == null )
            node = new BTNode(data,score,count);
        //insert(data);
        //else
        {
            //if (node.getRight() == null)
            node.right = insert(node.right, data-3,3,l);
            //else
            node.left = insert(node.left, data-1,1,r);
        }
        return node;
    }
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(BTNode r)
    {
        if (r != null)
        {
            inorder(r.getLeft());
            System.out.print(r.getData() +" ");
            inorder(r.getRight());
        }
    }

    void printPaths(BTNode node)
    {
        int path[] = new int[1000];
        printPathsRecur(node, path, 0);
    }

    /* Recursive helper function -- given a node, and an array
       containing the path from the root node up to but not
       including this node, print out all the root-leaf paths.*/
    void printPathsRecur(BTNode node, int path[], int pathLen)
    {
        if (node == null)
            return;

        /* append this node to the path array */
        path[pathLen] = node.data;
        pathLen++;

        /* it's a leaf, so print the path that led to here  */
        if (node.left == null && node.right == null && node.count%2==1)
        {
            printArray(path, pathLen);
            //printf("\n");
           // System.out.println(node.count);
        }
        else
        {
            /* otherwise try both subtrees */
            printPathsRecur(node.left, path, pathLen);
            printPathsRecur(node.right, path, pathLen);
        }
    }
    void printArray(int ints[], int len)
    {
        int i;
        for (i = 0; i < len; i++)
        {
            //System.out.print(ints[i] + " ");
            Log.e("TAG","Result"+ints[i]);
        }
        //System.out.println("");
    }
}
public class BinaryTree {

    //  BinaryTree l
    public static void main(String[] args)
    {
        BTNode root;
        BT bt = new BT();
        bt.insert(8);
        //bt.inorder();
        root=bt.getRoot();
        bt.printPaths(root);
    }
}
