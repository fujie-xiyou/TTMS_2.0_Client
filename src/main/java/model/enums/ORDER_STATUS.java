package model.enums;

public enum ORDER_STATUS {
    TBU(0,"待使用"),//To be used
    COMPLETED(1,"已完成"),
    REFUNDED(2,"已退款");

    private String name;
    private int index;
    private ORDER_STATUS(int index , String name) {
        this.index = index;
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
}

