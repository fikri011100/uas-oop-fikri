package com.bmcc;

import com.bmcc.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static ArrayList<Admin> admins;
    private static ArrayList<Player> player;
    private static ArrayList<Organizer> organizers;
    private static ArrayList<Event> events;
    private static ArrayList<Transaction> transactions;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        //menu awal
        String choiceMenu, username, password, choice, name;
        do {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("Choose menu : ");
            choiceMenu = scanner.nextLine();
        } while (!choiceMenu.equals("Register") && !choiceMenu.equals("Login"));

        if (choiceMenu.equals("Register")) {
            //register
            System.out.println("UAS FIKRI 2301886531");
            System.out.println("====================");
            do {
                System.out.println("1. Organizer");
                System.out.println("2. Player");
                System.out.println("Choose Role : ");
                choice = scanner.nextLine();
            } while (!choice.equals("Organizer") && !choice.equals("Player"));
            System.out.println("Enter Name");
            System.out.println(">>");
            name = scanner.nextLine();
            System.out.println("Enter Username");
            System.out.println(">>");
            username = scanner.nextLine();
            System.out.println("Enter Password");
            System.out.println(">>");
            password = scanner.nextLine();
            if (choice.equals("Organizer")) {
                organizers.add(new Organizer(username, password, 0, name));
            } else {
                player.add(new Player(username, password, 0, 0, name));
            }
        } else {
            //login
            System.out.println("UAS FIKRI 2301886531");
            do {
                System.out.println("====================");
                System.out.println("1. Admin");
                System.out.println("2. Organizer");
                System.out.println("3. Player");
                System.out.println("Choose Role : ");
                choice = scanner.nextLine();
            } while (!choice.equals("Admin") && !choice.equals("Organizer") && !choice.equals("Player"));
            System.out.println("Enter Username");
            System.out.println(">>");
            username = scanner.nextLine();
            System.out.println("Enter Password");
            System.out.println(">>");
            password = scanner.nextLine();
            login(username, password, choice);
        }

        //homepage
        System.out.println("Welcome to Fikri's App");
        System.out.println("======================");
        System.out.println("Menu");
        if (choice.equals("Admin")) {
            adminMenu();
        } else if (choice.equals("Organizer")) {
            organizerMenu();
        } else if (choice.equals("Player")) {
            playerMenu(username);
        }
    }

    private static void showEventAll() {

    }

    private static void playerMenu(String username) {
        int command = 0;
        do {
            System.out.println("1. Order Event");
            System.out.println("2. Event Ordered");
            System.out.println("3. Cancel Order");
            System.out.println("4. About Me");
            System.out.println("5. Exit");
            System.out.println(">>");
            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    orderEvent(username);
                    break;
                case 2:
                    showEventOrdered();
                    break;
                case 3:
                    cancelOrder();
                    break;
                case 4:
                    aboutMe();
            }
        } while (command != 5);
    }

    private static void aboutMe() {

    }

    private static void cancelOrder() {

    }

    private static void showEventOrdered() {

    }

    private static void orderEvent(String username) {
        int numberEvent;
        String idEvent = "";
        showEventAll();
        System.out.println("\nInsert Number Event : ");
        numberEvent = Integer.parseInt(scanner.nextLine());

        do {
            for (int i = 1; i < events.size(); i++) {
                if (numberEvent == i) {
                    idEvent = events.get(i).getId();
                }
            }
        } while (idEvent.equals(""));
        transactions.add(new Transaction(username, idEvent, "sukses"));
    }

    private static void organizerMenu() {
        int command = 0;
        do {
            System.out.println("1. Create Event");
            System.out.println("2. My Event");
            System.out.println("3. Exit");
            System.out.println(">>");
            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    showMyEvent();
                    break;
            }
        } while (command != 3);
    }

    private static void showMyEvent() {

    }

    private static void getSport() {
        System.out.println("List Sport");
        System.out.println("1. Badminton");
        System.out.println("2. Tenis");
        System.out.println("3. Renang");
        System.out.println("4. Gym");
    }

    private static void getLevel() {
        System.out.println("List Level");
        System.out.println("1. Beginner");
        System.out.println("2. Intermediate");
        System.out.println("3. Advance");
    }

    private static void createEvent() {
        int min, max, price, tgl, bulan, tahun;
        String id, name, sport, level, place, status, tglFull;

        id = generateRandomString();

        System.out.println("Input Event's Name : ");
        name = scanner.nextLine();
        do {
            getSport();
            System.out.println("Input Sport Type : ");
            sport = scanner.nextLine();
        } while (!sport.equals("Badminton") && !sport.equals("Tenis") && !sport.equals("Renang") && !sport.equals("Gym"));
        do {
            getLevel();
            System.out.println("Input Level Type : ");
            level = scanner.nextLine();
        } while (!level.equals("Beginner") && !level.equals("Intermediate") && !level.equals("Advance"));
        System.out.println("Input Place : ");
        place = scanner.nextLine();
        do {
            System.out.println("Input Tanggal (1 - 31) : ");
            tgl = Integer.parseInt(scanner.nextLine());
        } while (tgl >= 1 && tgl <= 31);
        do {
            System.out.println("Input Bulan (1-12): ");
            bulan = Integer.parseInt(scanner.nextLine());
        } while (bulan >= 1 && bulan <= 12);
        do {
            System.out.println("Input Tahun : ");
            tahun = Integer.parseInt(scanner.nextLine());
        } while (tahun >= 2021 && tahun <= 2030);
        tglFull = tgl + "-" + bulan + "-" + tahun;

        System.out.println("Input Minimum Player : ");
        min = Integer.parseInt(scanner.nextLine());

        System.out.println("Input Maximum Player : ");
        max = Integer.parseInt(scanner.nextLine());

        System.out.println("Input Price : ");
        price = Integer.parseInt(scanner.nextLine());

        events.add(new Event(id, name, sport, level, place, tglFull, min, max, price, "Berjalan"));
    }

    private static void adminMenu() {
        int command = 0;
        do {
            System.out.println("1. Pengaduan");
            System.out.println("2. Blokir Akun");
            System.out.println("3. Buat Pengumuman");
            System.out.println("4. Permintaan Registrasi");
            System.out.println("5. Exit");
            System.out.println(">>");
            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    showPengaduan();
                    break;
                case 2:
                    blockAccount();
                    break;
                case 3:
                    makeAnnouncement();
                    break;
                case 4:
                    registerPermission();
            }
        } while (command != 5);
    }

    private static void registerPermission() {

    }

    private static void makeAnnouncement() {

    }

    private static void blockAccount() {

    }

    private static void showPengaduan() {
    }

    private static void login(String username, String password, String choice) {

    }

    private static String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    private static String generateDateFormat(String tanggal) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        SimpleDateFormat dateFormatted = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = simpleDateFormat.parse(tanggal);
            tanggal = dateFormatted.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tanggal;
    }

}
