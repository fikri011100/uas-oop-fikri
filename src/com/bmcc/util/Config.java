package com.bmcc.util;

import com.bmcc.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
    private final ArrayList<Admin> admins;
    private final ArrayList<Player> player;
    private final ArrayList<Organizer> organizers;
    private final ArrayList<Event> events;
    private final ArrayList<Transaction> transactions;
    private final ArrayList<Announcement> announcements;
    private final ArrayList<Complaint> complaints;

    public Config(ArrayList<Admin> admins, ArrayList<Player> player, ArrayList<Organizer> organizers, ArrayList<Event> events, ArrayList<Transaction> transactions, ArrayList<Announcement> announcements, ArrayList<Complaint> complaints) {
        this.admins = admins;
        this.player = player;
        this.organizers = organizers;
        this.events = events;
        this.transactions = transactions;
        this.announcements = announcements;
        this.complaints = complaints;
    }

    public boolean checkDataExist() {
        return !admins.isEmpty() && !player.isEmpty() && !organizers.isEmpty() && !events.isEmpty() && !transactions.isEmpty() && !announcements.isEmpty();
    }

    public void addDefaultFile() {
        if (new File(Path.ADMIN_PATH).exists()) {
            readJson(Path.ADMIN_PATH);
        }
        if (new File(Path.PLAYER_PATH).exists()) {
            readJson(Path.PLAYER_PATH);
        }
        if (new File(Path.ORGANIZER_PATH).exists()) {
            readJson(Path.ORGANIZER_PATH);
        }
        if (new File(Path.EVENT_PATH).exists()) {
            readJson(Path.EVENT_PATH);
        }
        if (new File(Path.TRANSACTION_PATH).exists()) {
            readJson(Path.TRANSACTION_PATH);
        }
        if (new File(Path.ANNOUNCEMENT_PATH).exists()) {
            readJson(Path.ANNOUNCEMENT_PATH);
        }
        if (new File(Path.COMPLAINT_PATH).exists()) {
            readJson(Path.COMPLAINT_PATH);
        }
    }

    private void readJson(String filename) {
        JSONParser json = new JSONParser();

        try(FileReader file = new FileReader(filename)) {
            Object object = json.parse(file);
            JSONArray arr = (JSONArray) object;

            switch (filename) {
                case Path.ADMIN_PATH:
                    arr.forEach(o -> {
                        getJsonAdmin((JSONObject) o);
                    });
                    break;
                case Path.PLAYER_PATH:
                    arr.forEach(o -> {
                        getJsonPlayer((JSONObject) o);
                    });
                    break;
                case Path.ORGANIZER_PATH:
                    arr.forEach(o -> {
                        getJsonOrganizer((JSONObject) o);
                    });
                    break;
                case Path.EVENT_PATH:
                    arr.forEach(o -> {
                        getJsonEvent((JSONObject) o);
                    });
                    break;
                case Path.TRANSACTION_PATH:
                    arr.forEach(o -> {
                        getJsonTransaction((JSONObject) o);
                    });
                    break;
                case Path.ANNOUNCEMENT_PATH:
                    arr.forEach(o -> {
                        getJsonAnnouncement((JSONObject) o);
                    });
                    break;
                case Path.COMPLAINT_PATH:
                    arr.forEach(o -> {
                        getJsonComplaint((JSONObject) o);
                    });
                    break;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void getJsonComplaint(JSONObject o) {
        JSONObject obj = (JSONObject) o.get("complaint");

        String username = (String) obj.get("username");
        String complaint = (String) obj.get("complaint");

        complaints.add(new Complaint(username, complaint));
    }

    private void getJsonAnnouncement(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("announcement");

        String headline = (String) obj.get("headline");
        String content = (String) obj.get("content");

        announcements.add(new Announcement(headline, content));
    }

    private void getJsonTransaction(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("transaction");

        String usernamePlayer = (String) obj.get("usernamePlayer");
        String idEvent = (String) obj.get("idEvent");
        String status = (String) obj.get("status");

        transactions.add(new Transaction(usernamePlayer, idEvent, status));
    }

    private void getJsonEvent(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("event");

        String idEvent = (String) obj.get("idEvent");
        String name = (String) obj.get("name");
        String sport = (String) obj.get("sport");
        String level = (String) obj.get("level");
        String place = (String) obj.get("place");
        String tanggal = (String) obj.get("tanggal");
        int min = Integer.parseInt(String.valueOf(obj.get("min")));
        int max = Integer.parseInt(String.valueOf(obj.get("max")));
        int price = Integer.parseInt(String.valueOf(obj.get("price")));
        String status = (String) obj.get("status");
        String usernameOrganizer = (String) obj.get("usernameOrganizer");
        events.add(new Event(idEvent, name, sport, level, place, tanggal, min, max, price, status , usernameOrganizer));
    }

    private void getJsonOrganizer(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("organizer");

        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        int status = Integer.parseInt(String.valueOf(obj.get("status")));
        String nama = (String) obj.get("nama");

        organizers.add(new Organizer(username, password, status, nama));
    }

    private void getJsonPlayer(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("player");

        String username = (String) obj.get("username");
        String password = (String) obj.get("password");
        int status =  Integer.parseInt(String.valueOf(obj.get("status")));
        int jumlahPertandingan = Integer.parseInt(String.valueOf(obj.get("jumlahPertandingan")));
        String nama = (String) obj.get("nama");

        player.add(new Player(username, password, status, jumlahPertandingan, nama));
    }

    private void getJsonAdmin(JSONObject object) {
        JSONObject obj = (JSONObject) object.get("admin");

        String name = (String) obj.get("username");
        String password = (String) obj.get("password");

        admins.add(new Admin(name, password));
    }

    public void saveExit() {
        JSONArray arr;

        if (!admins.isEmpty()) {
            arr = new JSONArray();

            for (Admin admin : admins) {
                JSONObject obj = new JSONObject();
                obj.put("username", admin.getUsername());
                obj.put("password", admin.getPassword());

                JSONObject object = new JSONObject();
                object.put("admin", obj);
                arr.add(object);
            }
            try (FileWriter fr = new FileWriter(Path.ADMIN_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!player.isEmpty()) {
            arr = new JSONArray();

            for (Player pl : player) {
                JSONObject obj = new JSONObject();
                obj.put("username", pl.getUsername());
                obj.put("password", pl.getPassword());
                obj.put("status", pl.getStatus());
                obj.put("jumlahPertandingan", pl.getJumlahPertandingan());
                obj.put("nama", pl.getNama());

                JSONObject object = new JSONObject();
                object.put("player", obj);
                arr.add(object);
            }
            try (FileWriter fr = new FileWriter(Path.PLAYER_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!organizers.isEmpty()) {
            arr = new JSONArray();

            for (Organizer pl : organizers) {
                JSONObject obj = new JSONObject();
                obj.put("username", pl.getUsername());
                obj.put("password", pl.getPassword());
                obj.put("status", pl.getStatus());
                obj.put("nama", pl.getNama());

                JSONObject object = new JSONObject();
                object.put("organizer", obj);
                arr.add(object);
            }
            try (FileWriter fr = new FileWriter(Path.ORGANIZER_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!events.isEmpty()) {
            arr = new JSONArray();

            for (Event event : events) {
                JSONObject obj = new JSONObject();
                obj.put("idEvent", event.getId());
                obj.put("name", event.getName());
                obj.put("sport", event.getSport());
                obj.put("level", event.getLevel());
                obj.put("place", event.getPlace());
                obj.put("tanggal", event.getTanggal());
                obj.put("min", event.getMin());
                obj.put("max", event.getMax());
                obj.put("price", event.getPrice());
                obj.put("status", event.getStatus());
                obj.put("usernameOrganizer", event.getUsernameOrganizer());

                JSONObject objects = new JSONObject();
                objects.put("event", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.EVENT_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!transactions.isEmpty()) {
            arr = new JSONArray();

            for (Transaction tr : transactions) {
                JSONObject obj = new JSONObject();
                obj.put("usernamePlayer", tr.getUsernamePlayer());
                obj.put("idEvent", tr.getIdEvent());
                obj.put("status", tr.getStatus());

                JSONObject objects = new JSONObject();
                objects.put("transaction", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.TRANSACTION_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!announcements.isEmpty()) {
            arr = new JSONArray();

            for (Announcement ann : announcements) {
                JSONObject obj = new JSONObject();
                obj.put("headline", ann.getHeadline());
                obj.put("content", ann.getContent());

                JSONObject objects = new JSONObject();
                objects.put("announcement", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.ANNOUNCEMENT_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!complaints.isEmpty()) {
            arr = new JSONArray();

            for (Complaint com : complaints) {
                JSONObject obj = new JSONObject();
                obj.put("username", com.getUsername());
                obj.put("complaint", com.getComplaint());

                JSONObject objects = new JSONObject();
                objects.put("complaint", obj);
                arr.add(objects);
            }
            try (FileWriter fr = new FileWriter(Path.COMPLAINT_PATH)){
                fr.write(arr.toJSONString());
                fr.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
