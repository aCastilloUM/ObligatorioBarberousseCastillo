package stack;

import java.util.ArrayList;

public class Stack <T> implements MyStack<T>{

    private StackNode<T> top = null;

    int size = 0;

    public Stack() {
        top = null;
        size = 0;
    }


    @Override
    public void push(T value) {
        StackNode<T> newNode = new StackNode<>(value);
        if (size==0){
            this.top = newNode;
        }
        else{
            StackNode<T> head = this.top;
            this.top = newNode;
            newNode.setPrev(head);
            ;
        }
        size++;
    }

    @Override
    public void pop() throws EmptyStackException {
        if (size == 0) {
            throw new EmptyStackException();
        } else if (size == 1) {
            top = null;
            size = 0;
        } else {
            top = top.getPrev();
            size--;
        }
    }

    @Override
    public T peek() throws EmptyStackException {
        if (size==0) {
            throw new EmptyStackException();
        }
        else {
            return top.getValue();
        }
    }


    @Override
    public boolean isEmpty() {
        return (top == null && size == 0);
    }

    @Override
    public void printStack() {
        ArrayList<T> aux = new ArrayList<>();

        while (size!=0){
            aux.add(top.getValue());
            top=top.getPrev();
            size--;
        }
        System.out.print("[ ");
        for (int i = 0; i<aux.size(); i++){
            System.out.print(aux.get(i)+ " ");
        }
        System.out.print("]");

        for (int j = aux.size()-1; j>=0; j--){
            this.push((T) aux.get(j));
        }
    }

    @Override
    public boolean containsStack(T value) {
        ArrayList<T> aux = new ArrayList<>();

        while (size!=0){
            aux.add(top.getValue());
            top=top.getPrev();
            size--;
        }

        boolean exist = false;
        for (int i = 0; i<aux.size(); i++){
           if (value == ((T) aux.get(i))){
               exist = true;
           }
        }

        for (int j = aux.size()-1; j>=0; j--){
            this.push((T) aux.get(j));
        }
        return exist;
    }

}
