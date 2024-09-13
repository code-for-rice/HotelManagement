package view;


import controller.HotelList;

import utils.Utils;

public class HotelManagement {
    public static void main(String[] args) {
        
        HotelList h = new HotelList();      
        h.readFromFile(); 
        
        int choice;
        do {
            Utils.menu();
            choice = Utils.getInteger("Select your choice: ", 1, 7);
            switch (choice) {
                case 1: // add
                    do {
                        Utils.reportStatus(h.add());
                    } while (Utils.continueMenu());
                    break;
                case 2: // check exist
                    do {
                        h.checkExist();
                    } while (Utils.continueMenu());
                    break;
                case 3: // update
                    do {
                        h.update();
                    } while (Utils.continueMenu());
                    break;
                case 4: // delete
                    do {
                        Utils.reportStatus(h.delete());
                    } while (Utils.continueMenu());
                    break;
                case 5: // search
                    do {
                        h.search();
                    } while (Utils.continueMenu());
                    break;
                case 6: // display
                    h.display();
                    break;
                case 7: // exit
                    h.writetoFile();
                    System.out.println("THANKS FOR USING APP!!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter an integer from 1->7");
                    break;
            }
        } while (choice != 7);
    }
}
