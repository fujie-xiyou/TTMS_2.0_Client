package model;

import model.enums.ORDER_STATUS;
import model.enums.ORDER_TYPE;

import java.time.LocalDateTime;
import java.util.List;
public class Order {
    private int id;
    private LocalDateTime dateTime;
    private int uid;
    private ORDER_TYPE type;
    private ORDER_STATUS status;
    //    private int itemCount;
    private int total;
    private List<OrderItem> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public ORDER_TYPE getType() {
        return type;
    }

    public void setType(ORDER_TYPE type) {
        this.type = type;
    }

    public ORDER_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_STATUS status) {
        this.status = status;
    }

//    public int getItemCount() {
//        return itemCount;
//    }
//
//    public void setItemCount(int itemCount) {
//        this.itemCount = itemCount;
//    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
