package ir.nimdor.osoolproject;

public class Tag {

    String s;
    int index;

    public Tag(String s, int ind) {
        this.s = s;
        index = ind;


    }


    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public boolean is_equal(String input) {
        if (s.equals(input))
            return true;
        return false;
    }
}
