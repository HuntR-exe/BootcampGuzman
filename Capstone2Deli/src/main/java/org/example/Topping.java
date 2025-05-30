package org.example;

public class Topping {
    private String name;
    private String type;
    private boolean extra;

    public Topping(String name, String type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }
}
