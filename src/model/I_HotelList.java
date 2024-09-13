package model;

public interface I_HotelList {
    boolean add(); //mặc định all hàm đều là abstract public
    void checkExist();
    boolean checkDuplicate(String id);
    boolean delete();
    void search();
    void update();
    void sort();
    void display();
    void writetoFile();
    void readFromFile();
}
