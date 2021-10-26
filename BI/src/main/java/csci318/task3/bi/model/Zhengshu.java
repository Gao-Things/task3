package csci318.task3.bi.model;

public class Zhengshu {

    private int value;

    public Zhengshu(int value) {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Zhengshu{" +
                "value=" + value +
                '}';
    }
}
