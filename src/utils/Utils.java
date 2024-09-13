/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

public class Utils {
//Validation
    private static Scanner sc = new Scanner(System.in);
    
//    public static void showFrame(){
//        System.out.println("-------------------------------------------------------------------------");
//        System.out.println("ID |\tName                |Room Available|Address                  |Phone       |Rating ");
//    }
    
    public static void menu() {
        System.out.println("HOTEL MANAGEMENT APP");
        System.out.println("1. Add new hotel.");
        System.out.println("2. Check exist hotel.");
        System.out.println("3. Update hotel's info");
        System.out.println("4. Delete hotel");
        System.out.println("5. Search hotel");
        System.out.println("6. Display hotel's list (DESCENDING).");
        System.out.println("7. Quit");
    }

    public static void reportStatus(boolean status) {
        if (status) {
            System.out.println("-----SUCCESS-----");
        } else {
            System.out.println("-----FAIL-----");
        }
    }

    public static boolean continueMenu() {
        return Utils.getBoolean("Do you want to continue? (Y/N): ");
    }

    public static boolean getBoolean(String welcome) {
        sc = new Scanner(System.in);
        boolean check = false;
        System.out.print(welcome);
        String str = sc.nextLine();
        if ("y".equalsIgnoreCase(str)) {
            check = true;
        }
        return check;
    }

    public static String getString(String text) {
        String string = "";
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            System.out.print(text);
            string = sc.nextLine();
            if (string.isEmpty()) {
                check = true;
            } else {
                check = false;
            }

        } while (check);
        return string;
    }

    public static int getInteger(String text, int min, int max) {
        sc = new Scanner(System.in);
        int n;
        while (true) {
            try {
                System.out.print(text);
                n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    break;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number in range " + min + " -> " + max + ".");    
            }
        }
        
        return n;
    }

    public static double getDouble(String text, double min, double max) {
        sc = new Scanner(System.in);
        double n;
        while (true) {
            try {
                System.out.print(text);
                n = Double.parseDouble(sc.nextLine());
                if (min <= n && n <= max) {
                    break;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.print("Please enter an integer number in range " + min + " -> " + max + ": ");
            
            }
        }
        
        return n;
    }

    public static boolean updateBoolean(String welcome, boolean oldValue) {
        sc = new Scanner(System.in);
        boolean check = false;
        System.out.print(welcome);
        String str = sc.nextLine();
        if ("y".equalsIgnoreCase(str)) {
            check = true;
        } else if (str.length() == 0) {
            check = oldValue;
        }
        return check;
    }

    public static int updateInteger(String welcome, int min, int max, int oldValue) {
        int result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String str = sc.nextLine();
                if (str.length() == 0) {
                    result = oldValue;
                    check = false;
                } else {
                    result = Integer.parseInt(str);
                    if (result >= min && result <= max) {
                        check = false;
                    } else {
                        check = true;
                    }
                }
            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static double updateDouble(String welcome, double min, double max, double oldValue) {
        double result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String str = sc.nextLine();
                if (str.length() == 0) {
                    result = oldValue;
                    check = false;
                } else {
                    result = Double.parseDouble(str);
                    if (result >= min && result <= max) {
                        check = false;
                    } else {
                        check = true;
                    }
                }

            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldValue) {
        String result = "";
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.length() == 0) {
                result = oldValue;
                check = false;
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

}
