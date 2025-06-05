package sanbernardo.acambieri.teamcreator.model;

import java.util.ArrayList;
import java.util.List;

public class Group<T extends Item> implements Cloneable {

    public List<T> items;

    public Group(){
        items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    public T swapWithRandom(T item){
        T removed = removeRandom();
        items.add(item);
        return removed;
    }

    public T removeRandom(){
        return items.remove((int) (Math.random() * (items.size() - 1)));
    }

    public void add(T item){
        items.add(item);
    }

    @Override
    public String toString() {
        return "Group{" +
                "items=" + items.toString() +
                '}';
    }

    public Double getAverageScore(){
        return items.stream().mapToDouble(T::getScore).average().orElse(0D);
    }


    @Override
    public Group<T> clone() {
        Group<T> t = new Group<>();
        items.forEach(i -> t.add((T) i.clone()));
        return t;
    }
}
