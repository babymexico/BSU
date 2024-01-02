package org.example;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReverseRecursion {
    Node head;

    public LinkedListReverseRecursion() {
        head = null;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private Node reverseRecursive(Node current, Node prev) {
        if (current == null) {
            return prev;
        }
        Node next = current.next;
        current.next = prev;
        return reverseRecursive(next, current);
    }

    public void reverse() {
        head = reverseRecursive(head, null);
    }

    public static void main(String[] args) {
        LinkedListReverseRecursion list = new LinkedListReverseRecursion();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println("Original List:");
        list.display();

        list.reverse();

        System.out.println("Reversed List:");
        list.display();
    }
}
