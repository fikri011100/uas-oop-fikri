package com.bmcc;

import com.bmcc.model.*;
import com.bmcc.util.Config;

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
    private static ArrayList<Announcement> announcements;
    private static ArrayList<Complaint> complaints;
    private static ArrayList<Lencana> lencanas;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);

        //setup arraylist
        admins = new ArrayList<>();
        player = new ArrayList<>();
        organizers = new ArrayList<>();
        events = new ArrayList<>();
        transactions = new ArrayList<>();
        announcements = new ArrayList<>();
        complaints = new ArrayList<>();
        lencanas = new ArrayList<>();

        Config config = new Config(admins, player, organizers, events, transactions, announcements, complaints, lencanas);
        if (!config.checkDataExist()) {
            config.addDefaultFile();
        }

        //menu awal
        String choiceMenu, username, password, choice, name;
        do {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("Choose menu [1 | 2]: ");
            choiceMenu = scanner.nextLine();
        } while (!choiceMenu.equals("1") && !choiceMenu.equals("2"));
        if (choiceMenu.equals("1")) {
            choiceMenu = "Register";
        } else {
            choiceMenu = "Login";
        }

        if (choiceMenu.equals("Register")) {
            //register
            System.out.println("UAS FIKRI 2301886531");
            System.out.println("====================");
            do {
                System.out.println("1. Organizer");
                System.out.println("2. Player");
                System.out.println("Choose Role [1 | 2]: ");
                choice = scanner.nextLine();
            } while (!choice.equals("1") && !choice.equals("2"));
            if (choice.equals("1")) {
                choice = "Organizer";
            } else {
                choice = "Player";
            }
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
            boolean login = false;
            System.out.println("UAS FIKRI 2301886531");
            do {
                System.out.println("====================");
                System.out.println("1. Admin");
                System.out.println("2. Organizer");
                System.out.println("3. Player");
                System.out.println("Choose Role [1-3]: ");
                choice = scanner.nextLine();
            } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
            if (choice.equals("1"))
                choice = "Admin";
            else if (choice.equals("2"))
                choice = "Organizer";
            else
                choice = "Player";
            do {
                System.out.println("Enter Username");
                System.out.println(">>");
                username = scanner.nextLine();
                System.out.println("Enter Password");
                System.out.println(">>");
                password = scanner.nextLine();
                login = login(username, password, choice);
            } while (login == false);
        }

        //homepage
        showAnnouncement();
        System.out.println("Welcome to Fikri's App");
        System.out.println("======================");
        System.out.println("Menu");
        if (choice.equals("Admin")) {
            adminMenu(config);
        } else if (choice.equals("Organizer")) {
            organizerMenu(username, config);
        } else {
            playerMenu(username, config);
        }
    }

    private static void showEventAll() {
        int temp = 1;
        System.out.println("KEGIATAN ");
        System.out.println("===============================================================================================================================================");
        System.out.println("| No | Nama            | Tempat          | Jenis      | Level          | Tanggal     | Min | Max | Harga       | Status     | Organizer       |");
        System.out.println("===============================================================================================================================================");
        for (Event e : events) {
            Organizer org = null;
            for (Organizer o : organizers) {
                if (o.getUsername().equals(e.getUsernameOrganizer())) {
                    org = o;
                }
            }
            System.out.printf("| %-2d | %-15s | %-15s | %-10s | %-14s | %-11s | %-3d | %-3d | Rp. %-7d | %-10s | %-15s |\n", temp, e.getName(), e.getPlace(), e.getSport(), e.getLevel(), generateDateFormat(e.getTanggal()), e.getMin(), e.getMax(), e.getPrice(), e.getStatus(), org.getNama());
            temp++;
        }
    }

    private static void playerMenu(String username, Config config) {
        int command = 0;
        boolean ex = true;
        do {
            System.out.println("1. Order Event");
            System.out.println("2. Event Ordered");
            System.out.println("3. Cancel Order");
            System.out.println("4. About Me");
            System.out.println("5. Give Lencana");
            System.out.println("6. Exit");
            System.out.println(">>");
            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    orderEvent(username);
                    break;
                case 2:
                    showEventOrdered(username);
                    break;
                case 3:
                    cancelOrder(username);
                    break;
                case 4:
                    aboutMe(username);
                    break;
                case 5:
                    giveLencana();
                    break;
                case 6:
                    config.saveExit();
                    ex = false;
                    return;
            }
        } while (ex == true);
    }

    private static void giveLencana() {
        if (player.size() > 0) {
            String lencana, username = null, user;
            System.out.println("| Nama            | Username        |");
            for (Player pl : player) {
                System.out.printf("| %-15s | %-15s |\n", pl.getNama(), pl.getUsername());
            }
            do {
                System.out.println("Input Username : ");
                user = scanner.nextLine();
                for (Player pl : player) {
                    if (pl.getUsername().equals(user))
                        username = pl.getUsername();
                }
            } while (user == null);

            System.out.println("Input Lencana : ");
            lencana = scanner.nextLine();

            lencanas.add(new Lencana(username, lencana));
        }
    }

    private static void showAnnouncement() {
        if (announcements.size() > 0) {
            Announcement ann = announcements.get(announcements.size() - 1);
            System.out.println("PEMBERITAHUAN!!!!");
            System.out.println(ann.getHeadline());
            System.out.println(ann.getContent());
            System.out.println("\n");
        }
    }

    private static void aboutMe(String username) {
        String level = "";
        int temp = 1;
        for (Player pl : player) {
            if (pl.getUsername().equals(username)) {
                level = pl.getLevel(pl.getJumlahPertandingan());
                System.out.println("====================");
                System.out.println("======ABOUT ME======");
                System.out.println("====================");
                System.out.printf("|Level : %s\n", level);
                System.out.println("|Lencana            |");
                for (Lencana len : lencanas) {
                    if (len.getUsername().equals(username)) {
                        System.out.println("| " + temp + " " + len.getLencana());
                    }
                    temp++;
                }
                System.out.println("====================\n");
            }
        }
    }

    private static void cancelOrder(String username) {
        String id = null, idEvent;
        System.out.println("KEGIATAN ");
        System.out.println("=====================================================================================================================================");
        System.out.println("| Id    | Nama            | Tempat          | Jenis      | Level          | Tanggal     | Organizer       | Harga       | Status     |");
        System.out.println("=====================================================================================================================================");
        for (Transaction tr : transactions) {
            if (!tr.getStatus().equals("dibatalkan")) {
                Event event = null;
                for (Event e : events) {
                    if (tr.getIdEvent().equals(e.getId())) {
                        event = e;
                    }
                }
                Organizer org = null;
                for (Organizer o : organizers) {
                    if (o.getUsername().equals(event.getUsernameOrganizer())) {
                        org = o;
                    }
                }
                if (tr.getUsernamePlayer().equals(username)) {
                    System.out.printf("| %-5s | %-15s | %-15s | %-10s | %-14s | %-11s | %-15s | Rp. %-7d | %-10s |\n", event.getId(), event.getName(), event.getPlace(), event.getSport(), event.getLevel(), generateDateFormat(event.getTanggal()), org.getNama(), event.getPrice(), event.getStatus());
                }
            }
        }
        System.out.println("=====================================================================================================================================");
        do {
            System.out.println("\nInsert ID Event : ");
            idEvent = scanner.nextLine();
            for (Transaction tr : transactions) {
                if (tr.getIdEvent().equals(idEvent)) {
                    id = tr.getIdEvent();
                    tr.setStatus("dibatalkan");
                }
            }
        } while (id == null);
        System.out.println("Berhasil!");
    }

    private static void showEventOrdered(String username) {
        int temp = 1;
        System.out.println("KEGIATAN ");
        System.out.println("===============================================================================================================================================");
        System.out.println("| No | Nama            | Tempat          | Jenis      | Level          | Tanggal     | Organizer       | Harga       | Status     | Pesanan   |");
        System.out.println("===============================================================================================================================================");
        for (Transaction tr : transactions) {
            Event event = null;
            for (Event e : events) {
                if (tr.getIdEvent().equals(e.getId())) {
                    event = e;
                }
            }
            Organizer org = null;
            for (Organizer o : organizers) {
                if (o.getUsername().equals(event.getUsernameOrganizer())) {
                    org = o;
                }
            }
            if (tr.getUsernamePlayer().equals(username)) {
                System.out.printf("| %-2d | %-15s | %-15s | %-10s | %-14s | %-11s | %-15s | Rp. %-7d | %-10s | %-10s |\n", temp, event.getName(), event.getPlace(), event.getSport(), event.getLevel(), generateDateFormat(event.getTanggal()), org.getNama(), event.getPrice(), event.getStatus(), tr.getStatus());
                temp++;
            }
        }
        System.out.println("===============================================================================================================================================");
    }

    private static void orderEvent(String username) {
        int numberEvent;
        String idEvent = null;
        showEventAll();

        do {
            System.out.println("\nInsert Number Event : ");
            numberEvent = Integer.parseInt(scanner.nextLine());
            for (int i = 1; i < events.size(); i++) {
                if (numberEvent == i) {
                    Event ev = events.get(i - 1);
                    Player pl = null;
                    idEvent = ev.getId();
                    ev.increaseCount();
                    for (Player player : player) {
                        if (player.getUsername().equals(username)) {
                            pl = player;
                        }
                    }
                    pl.increaseMatch();
                }
            }
        } while (idEvent == null);
        transactions.add(new Transaction(username, idEvent, "sukses"));
        System.out.println("Berhasil!");
    }

    private static void organizerMenu(String username, Config config) {
        int command = 0;
        boolean ex = true;
        do {
            System.out.println("1. Create Event");
            System.out.println("2. My Event");
            System.out.println("3. Exit");
            System.out.println(">>");
            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1:
                    createEvent(username);
                    break;
                case 2:
                    showMyEvent(username);
                    break;
                case 3:
                    config.saveExit();
                    ex = false;
                    return;
            }
        } while (ex == true);
    }

    private static void showMyEvent(String username) {
        int temp = 1;
        System.out.println("KEGIATAN " + username);
        System.out.println("============================================================================================================================");
        System.out.println("| No | Nama            | Tempat          | Jenis      | Level          | Tanggal     | Min | Max | Harga       | Status     |");
        System.out.println("============================================================================================================================");
        for (Event e : events) {
            Organizer org = null;
            for (Organizer o : organizers) {
                if (o.getUsername().equals(username)) {
                    org = o;
                }
            }
            if (e.getUsernameOrganizer().equals(username)) {
                System.out.printf("| %-2d | %-15s | %-15s | %-10s | %-14s | %-11s | %-3d | %-3d | Rp. %-7d | %-10s |\n", temp, e.getName(), e.getPlace(), e.getSport(), e.getLevel(), generateDateFormat(e.getTanggal()), e.getMin(), e.getMax(), e.getPrice(), e.getStatus());
                temp++;
            }
        }
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

    private static void createEvent(String username) {
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
        } while (tgl <= 1 || tgl >= 31);
        do {
            System.out.println("Input Bulan (1-12): ");
            bulan = Integer.parseInt(scanner.nextLine());
        } while (bulan <= 1 || bulan >= 12);
        do {
            System.out.println("Input Tahun : ");
            tahun = Integer.parseInt(scanner.nextLine());
        } while (tahun <= 2021 || tahun >= 2030);
        tglFull = tgl + "-" + bulan + "-" + tahun;

        System.out.println("Input Minimum Player : ");
        min = Integer.parseInt(scanner.nextLine());

        System.out.println("Input Maximum Player : ");
        max = Integer.parseInt(scanner.nextLine());

        System.out.println("Input Price : ");
        price = Integer.parseInt(scanner.nextLine());

        events.add(new Event(id, name, sport, level, place, tglFull, min, max, price, 0, "Berjalan", username));
    }

    private static void adminMenu(Config config) {
        int command = 0;
        boolean ex = true;
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
                    createAnnouncement();
                    break;
                case 4:
                    registerPermission();
                    break;
                case 5:
                    config.saveExit();
                    ex = false;
                    return;
            }
        } while (ex == true);
    }

    private static void registerPermission() {
        String input, usernameRegis;
        do {
            System.out.println("1. Organizer");
            System.out.println("2. Player");
            System.out.println("Enter Type [1-2]: ");
            input = scanner.nextLine();
        } while (!input.equals("1") && !input.equals("2"));
        if (input.equals("1")) {
            if (organizers.size() > 0) {
                Organizer organizer = null;

                System.out.println("DATA AKUN ORGANIZER");
                System.out.println("| username        | nama            |");
                for (Organizer org : organizers) {
                    System.out.printf("| %-15s | %-15s |\n", org.getUsername(), org.getNama());
                }
                System.out.println("enter username : ");
                usernameRegis = scanner.nextLine();
                for (Organizer org : organizers) {
                    if (org.getUsername().equals(usernameRegis)) {
                        organizer = org;
                    }
                }
                if (organizer == null) {
                    System.out.println("Tidak ada Username " + usernameRegis);
                    return;
                }
                organizer.setStatus(1);
                System.out.println("Berhasil!");
            } else {
                System.out.println("No Data");
            }
        } else {
            if (player.size() > 0) {
                Player pl = null;

                System.out.println("DATA AKUN PLAYER");
                System.out.println("| username        | nama            |");
                for (Player player : player) {
                    System.out.printf("| %-15s | %-15s |\n", player.getUsername(), player.getNama());
                }
                System.out.println("enter username : ");
                usernameRegis = scanner.nextLine();
                for (Player player : player) {
                    if (player.getUsername().equals(usernameRegis)) {
                        pl = player;
                    }
                }
                if (pl == null) {
                    System.out.println("Tidak ada Username " + usernameRegis);
                    return;
                }
                pl.setStatus(1);
                System.out.println("Berhasil!");
            } else {
                System.out.println("No Data");
            }
        }
    }

    private static void createAnnouncement() {
        String header, content;

        System.out.println("Enter Header: ");
        header = scanner.nextLine();

        System.out.println("Enter Content: ");
        content = scanner.nextLine();

        announcements.add(new Announcement(header, content));
    }

    private static void blockAccount() {
        String input, blocked;
        do {
            System.out.println("1. Organizer");
            System.out.println("2. Player");
            System.out.println("Enter Type [1-2]: ");
            input = scanner.nextLine();
        } while (!input.equals("1") && !input.equals("2"));
        if (input.equals("1")) {
            if (organizers.size() > 0) {
                Organizer organizer = null;

                System.out.println("DATA BLOKIR AKUN ORGANIZER");
                System.out.println("| username        | nama            |");
                for (Organizer org : organizers) {
                    System.out.printf("| %-15s | %-15s |\n", org.getUsername(), org.getNama());
                }
                System.out.println("enter username : ");
                blocked = scanner.nextLine();
                for (Organizer org : organizers) {
                    if (org.getUsername().equals(blocked)) {
                        organizer = org;
                    }
                }
                if (organizer == null) {
                    System.out.println("Tidak ada Username " + blocked);
                    return;
                }
                organizer.setStatus(2);
                System.out.println("Berhasil!");
            } else {
                System.out.println("No Data");
            }
        } else {
            if (player.size() > 0) {
                Player pl = null;

                System.out.println("DATA BLOKIR AKUN PLAYER");
                System.out.println("| username        | nama            |");
                for (Player player : player) {
                    System.out.printf("| %-15s | %-15s |\n", player.getUsername(), player.getNama());
                }
                System.out.println("enter username : ");
                blocked = scanner.nextLine();
                for (Player player : player) {
                    if (player.getUsername().equals(blocked)) {
                        pl = player;
                    }
                }
                if (pl == null) {
                    System.out.println("Tidak ada Username " + blocked);
                    return;
                }
                pl.setStatus(2);
                System.out.println("Berhasil!");
            } else {
                System.out.println("No Data");
            }
        }
    }

    private static void showPengaduan() {
        if (complaints.size() > 0) {
            System.out.println("DATA PENGADUAN");
            System.out.println("| name            | pengaduan        ");
            for (Complaint com : complaints) {
                System.out.printf("| %15s | %15s |\n", com.getUsername(), com.getComplaint());
            }
        }
    }

    private static boolean login(String username, String password, String choice) {
        boolean stat = false;
        if (choice.equals("Admin")) {
            for (Admin admin : admins) {
                stat = true;
            }
        } else if (choice.equals("Organizer")) {
            for (Organizer organizer : organizers) {
                if (organizer.getUsername().equals(username) && organizer.getPassword().equals(password)) {
                    if (organizer.getStatus() == 1) {
                        stat = true;
                    } else if (organizer.getStatus() == 0) {
                        System.out.println("Akun belum ter-verifikasi");
                    } else {
                        System.out.println("Akun ter-blokir");
                    }
                }
            }
        } else {
            for (Player player : player) {
                if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                    if (player.getStatus() == 1) {
                        stat = true;
                    } else if (player.getStatus() == 0) {
                        System.out.println("Akun belum ter-verifikasi");
                    } else {
                        System.out.println("Akun ter-blokir");
                    }
                }
            }
        }
        return stat;
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
