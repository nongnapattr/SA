package yote.workschedule.Service;

public interface DataSource<T> {
    T readData();
    void writeData(T t);
}
