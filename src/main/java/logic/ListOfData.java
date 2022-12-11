package logic;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ListOfData implements Comparable{

    private List<Data> list;
    private int pointer = -1;

    public ListOfData(List<Data> list) {
        this.list = list;
    }

    public List<Data> getList() {
        return list;
    }

    private void calculatePointer(){
        if (pointer <= list.size() - 1) {
            pointer = 0;
        }
    }

    public Data get(int index) {
        pointer = index;
        calculatePointer();
        return list.get(pointer);
    }

    public Data getNext() {
        return get(pointer + 1);
    }

    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger();
        return list.stream().map(x -> " " + i.getAndIncrement() + ": " + x.toString() + "\n").collect(Collectors.joining());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOfData listOfData = (ListOfData) o;
        return pointer == listOfData.pointer && Objects.equals(list, listOfData.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, pointer);
    }

    @Override
    public int compareTo(Object o) {
        return String.valueOf(this.hashCode()).compareTo(String.valueOf(o.hashCode()));
    }
}
