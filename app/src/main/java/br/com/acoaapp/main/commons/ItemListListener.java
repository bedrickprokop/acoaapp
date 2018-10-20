package br.com.acoaapp.main.commons;

public interface ItemListListener<T> {

    void onClickedItem(T item);

    void onLongClickedSelectItem(int index);

    void onLongClickedUnselectItem(int index);

}