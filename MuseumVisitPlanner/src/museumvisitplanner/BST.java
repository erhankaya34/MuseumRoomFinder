package museumvisitplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BST {
    private Node root;
    private Map<Integer, String> roomNumbers;

    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public BST() {
        roomNumbers = new HashMap<>();
        roomNumbers.put(20, "F1");
        roomNumbers.put(10, "R101");
        roomNumbers.put(25, "F2");
        roomNumbers.put(5, "R102");
        roomNumbers.put(15, "R103");
        roomNumbers.put(22, "R201");
        roomNumbers.put(30, "R204");
        roomNumbers.put(21, "R202");
        roomNumbers.put(24, "R203");
        roomNumbers.put(28, "R205");
        roomNumbers.put(35, "R206");
    }

    public Node search(int roomNumber) 
    {
        return search(root, roomNumber);
    }

    private Node search(Node node, int roomNumber) 
    {
        if (node == null || node.value == roomNumber) 
        {
            return node;
        }

        if (roomNumber < node.value) 
        {
            return search(node.left, roomNumber);
        } 
        
        else 
        {
            return search(node.right, roomNumber);
        }
    }

    public List<String> getPathTo(int roomNumber) {
        List<String> path = new ArrayList<>();
        getPathTo(root, roomNumber, path);
        return path;
    }

    private boolean getPathTo(Node node, int roomNumber, List<String> path) 
    {
        if (node == null) 
        {
            return false;
        }

        path.add(roomNumbers.get(node.value));

        if (node.value == roomNumber) 
        {
            return true;
        }

        if (roomNumber < node.value && getPathTo(node.left, roomNumber, path)) 
        {
            return true;
        }

        if (roomNumber > node.value && getPathTo(node.right, roomNumber, path)) 
        {
            return true;
        }

        path.remove(roomNumbers.get(node.value));
        return false;
    }

    public void insert(int roomNumber) 
    {
        root = insert(root, roomNumber);
    }

    private Node insert(Node node, int roomNumber) 
    {
       
        if (node == null) 
        {
            return new Node(roomNumber);
        }

        if (roomNumber < node.value)
        {
            node.left = insert(node.left, roomNumber);
        } 
            
        else if (roomNumber > node.value)
        {
            node.right = insert(node.right, roomNumber);
        }

        return node;
    }
 /*
        These codes were about the console output but we moved it it to the interface

        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing) 
        {
            System.out.print("Enter room number to find path to: ");
            String roomName = scanner.next();
            int roomNumber = roomNumbersReverse.getOrDefault(roomName, -1);
            
            if (roomNumber == -1) 
            {
                System.out.println("Room not found");
            } 
            
            else 
            
            {
                List<String> path = bst.getPathTo(roomNumber);
                System.out.println("Path to room " + roomName + ": " + String.join(" -> ", path) + " | END");
            }
            
            System.out.print("Do you want to find another room? (y/n): ");
            String answer = scanner.next();
            keepGoing = answer.equalsIgnoreCase("y");
        }
    }
    
    */
    
}
    


