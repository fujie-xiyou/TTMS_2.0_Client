package model;

import model.enums.ORDER_ITEM_STATUS;

public class OrderItem {
    private int id;
    private int orderID;
    private int ticketID;
    private ORDER_ITEM_STATUS status;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public ORDER_ITEM_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_ITEM_STATUS status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
