package ir.nimdor.osoolproject;

public enum  Commands {
    add(32) , sub(34) ,Rtype(0) ,and(36),or(37), nor (39) , slt(42) , beq (4) , lw ( 35 ) , sw (43);


    private final int value;

    Commands(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

}
