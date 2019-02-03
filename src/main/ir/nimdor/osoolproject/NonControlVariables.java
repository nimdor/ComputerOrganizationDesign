package ir.nimdor.osoolproject;


public class NonControlVariables {// addresses are not used ! the addresses are in the instruction ! }
    private int rs;
    private int rd;
    private int rt;
    private int pc;
    private int EXLogicalResult;
    private boolean EXZeroResult;
    private int MEMResult;

    public int getEXLogicalResult() {
        return EXLogicalResult;
    }

    public void setEXLogicalResult(int EXLogicalResult) {
        this.EXLogicalResult = EXLogicalResult;
    }

    public boolean getEXZeroResult() {
        return EXZeroResult;
    }

    public void setEXZeroResult(boolean EXZeroResult) {
        this.EXZeroResult = EXZeroResult;
    }

    public int getMEMResult() {
        return MEMResult;
    }

    public void setMEMResult(int MEMResult) {
        this.MEMResult = MEMResult;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public int getRd() {
        return rd;
    }

    public void setRd(int rd) {
        this.rd = rd;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

}
