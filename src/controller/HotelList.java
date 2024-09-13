package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.I_HotelList;
import utils.Utils;

public class HotelList extends ArrayList<Hotel> implements I_HotelList {

    @Override
    public boolean checkDuplicate(String id) {
        for (Hotel h : this) {
            if (id.equals(h.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add() {
        String id;
        do {
            id = Utils.getString("Enter ID: ");
        } while ((!id.matches("^H\\d{2}") && (!id.matches("^H\\d{3}"))) || checkDuplicate(id));
        String name = Utils.getString("Enter name: ");

        int roomAvailable = Utils.getInteger("Enter available room(s) 1->10: ", 1, 10);
        String address = Utils.getString("Enter address: ");
        String phone;
        do {
            phone = Utils.getString("Enter phone: ");
        } while (!phone.matches("^0[\\d]{9}") && !phone.matches("^0[\\d]{11}"));
        String rating = Utils.getString("Enter rating: ");
        Hotel h = new Hotel(id, name, roomAvailable, address, phone, rating);
        this.add(h);
        return true;
    }

    @Override
    public void search() {
        int choice;
        do {
            System.out.println("1. By Hotel ID");
            System.out.println("2. By Hotel Name");
            choice = Utils.getInteger("Choose your option: ", 1, 2);
        } while (choice != 1 && choice != 2);
        switch (choice) {
            case 1: //id
                String id = Utils.getString("Enter id: ");
                for (Hotel h : this) {
                    if (id.equals(h.getId())) {
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("ID%s|Name%16s|Room Available|Address%63s|Phone%7s|Rating \n", " ", " ", " ", " ");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println(h.display());
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        return;
                    }
                }
                System.out.println("Not found!!");
                break;
            case 2: //name
                String name = Utils.getString("Enter name: ");
                for (Hotel h : this) {
                    if (name.equals(h.getName())) {
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("ID%s|Name%16s|Room Available|Address%63s|Phone%7s|Rating \n", " ", " ", " ", " ");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println(h.display());
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
                        return;
                    }
                }
                System.out.println("Not found!!");
                break;
        }
    }

    @Override
    public void update() {
        String id = Utils.getString("Enter hotel's ID to update: ");
        for (Hotel hotel : this) {
            if (id.equals(hotel.getId())) {
                String name = Utils.updateString("Old Name: " + hotel.getName() + ", New NAME: ", hotel.getName());
                int roomAvailable = Utils.updateInteger("Old Available Room: " + hotel.getRoomAvailable() + ", New AVAILABLE ROOM (1-10): ", 1, 10, hotel.getRoomAvailable());
                String address = Utils.updateString("Old Address: " + hotel.getAddress() + ", New ADDRESS: ", hotel.getAddress());
                String pattern = "^0[\\d]{9}", phone;
                do {
                    phone = Utils.updateString("Old Phone: " + hotel.getPhone() + ", New PHONE: ", hotel.getPhone());

                } while (!phone.matches(pattern));
                String rating = Utils.updateString("Old Rating: " + hotel.getRating() + ", New RATING: ", hotel.getRating());
                hotel.setName(name);
                hotel.setRoomAvailable(roomAvailable);
                hotel.setAddress(address);
                hotel.setPhone(phone);
                hotel.setRating(rating);
                return;
            }
        }
        System.out.println("Hotel does not exist");

    }

    @Override
    public void sort() { //sắp xếp 1 mớ ds, ds 
        Collections.sort(this, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel o1, Hotel o2) {
                return o2.getName().compareTo(o1.getName());
            } //o2 gần z hơn(o2 lớn hơn), o1 gần a hơn (o1 nhỏ hơn)
        });
    }

    @Override
    public void display() {
        sort();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("ID%s|Name%16s|Room Available|Address%63s|Phone%7s|Rating \n", " ", " ", " ", " ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        for (Hotel h : this) {
            System.out.println(h.display());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        }

    }

    @Override
    public void checkExist() {
        String id = Utils.getString("Enter hotel's ID: ");
        if (checkDuplicate(id)) {
            System.out.println("Exist Hotel!");
        } else {
            System.out.println("No Hotel Found!");
        }
    }

    @Override
    public boolean delete() {
        boolean check = true;
        String id = Utils.getString("Enter hotel's ID: ");
        if (!checkDuplicate(id)) {
            System.out.println("Error id.");
            check = false;
        } else if (!Utils.getBoolean("Do you really want to delete this hotel (Y/N)?: ")) {
            check = false;
        } else {
            for (Hotel h : this) {
                if (id.equals(h.getId())) {
                    this.remove(h);
                    return true;
                }
            }
        }
        return check;
    }

    @Override
    public void writetoFile() { //save
        try {
            FileWriter fw = new FileWriter("Hotel.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Hotel o : this) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
        }
    }
    
    public <Hotel> boolean readFromFile(File fileName) {
        this.clear();
        boolean status = false;
        try {
            if (!fileName.exists()) {
                System.err.println("Empty file !.");
                return false;
            }
            InputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                try {
                    Hotel x = (Hotel) ois.readObject();
                    this.add(x);
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            
            ois.close();
            fis.close();
            status = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }


//    @Override
//    public void readFromFile() { //open
//        FileReader fr = null;
//        BufferedReader br = null;
//
//        try {
//            fr = new FileReader("Hotel.dat");
//            br = new BufferedReader(fr);
//
//            while (true) {
//                Hotel a = new Hotel();
//                String line = br.readLine();
//                if (line == null) {
//                    break;
//                }
//                String txt[] = line.split(", ");
//                a.setId(txt[0]);
//                a.setName(txt[1]);
//                a.setRoomAvailable(Integer.parseInt(txt[2]));
//                a.setAddress(txt[3]);
//                a.setPhone(txt[4]);
//                a.setRating(txt[5]);
//                this.add(a);
//
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(HotelList.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(HotelList.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                br.close();
//                fr.close();
//            } catch (IOException ex) {
//            }
//
//        }
//
//    }
}
