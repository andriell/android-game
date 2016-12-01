package com.andriell.mygame.base;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Андрей on 01.12.2016.
 */

public class VeryFastSet<E> {
    LinkedList<E> data = new LinkedList<>();
    ArrayList<E> add = new ArrayList<>();
    ArrayList<E> remove = new ArrayList<>();

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean contains(E o) {
        return data.contains(o);
    }

    public Iterator<E> iterator() {
        return data.iterator();
    }

    public boolean add(E e) {
        return add.add(e);
    }

    public boolean remove(E o) {
        return remove.add(o);
    }

    public void clear() {
        data.clear();
    }

    public void update() {
        synchronized (this) {
            for (E e:add) {
                data.add(e);
            }
            add.clear();
            for (E e:remove) {
                data.remove(e);
            }
            remove.clear();
        }
    }
}
