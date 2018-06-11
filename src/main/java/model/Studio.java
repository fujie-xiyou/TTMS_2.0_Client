package model;

import model.enums.SEAT_STATUS;

import java.util.LinkedList;
import java.util.List;

public class Studio {
    private int id;
    private String name;
    private int row;
    private int col;
    private int count;
    private String introduction;
    private Seat[][] seats;

    public Studio(int id, String name, int row, int col, int count) {
        this.id = id;
        this.name = name;
        this.row = row;
        this.col = col;
        this.count = count;
        this.seats = null;

    }

    @Override
    public String toString() {
        return name;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public static List<Studio> getStdios() {
        List<Studio> studios = new LinkedList<>();
        studios.add(new Studio(1, "一号厅", 8, 7, 56));
        studios.add(new Studio(2, "二号厅", 5, 6, 30));
        studios.add(new Studio(3, "三号厅", 6, 6, 36));
        return studios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Studio() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void copyFrom(Studio studio) {
        this.setId(studio.getId());
        this.setName(studio.getName());
        this.setRow(studio.getRow());
        this.setCol(studio.getCol());
        this.setCount(studio.getCount());
        this.setIntroduction(studio.getIntroduction());
//        this.setSeats(new Seat[studio.getRow()][studio.getCol()]);
//        Seat[][] seats = this.getSeats();
//        Seat[][] oldSeats = studio.getSeats();
//        for (int i = 0; i < seats.length; i++) {
//            for (int j = 0; j < seats[0].length; j++) {
//                seats[i][j] = oldSeats[i][j].copy();
//            }
//        }

    }


}
