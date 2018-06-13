package model.enums;

public enum ORDER_TYPE {
    BUY(0,"购买"),
    REFUND(1,"退款");

    private String name;
    private int index;
    private ORDER_TYPE(int index , String name) {
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

