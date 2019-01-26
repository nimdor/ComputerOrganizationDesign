package ir.nimdor.osoolproject;

public class NonControlVariables {
    private int rs;
    private int rd;
    private int rt;
    private int rsAddress;
    private int rdAddress;
    private int rtAddress;
    private int pc;

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

    public int getRsAddress() {
        return rsAddress;
    }

    public void setRsAddress(int rsAddress) {
        this.rsAddress = rsAddress;
    }

    public int getRdAddress() {
        return rdAddress;
    }

    public void setRdAddress(int rdAddress) {
        this.rdAddress = rdAddress;
    }

    public int getRtAddress() {
        return rtAddress;
    }

    public void setRtAddress(int rtAddress) {
        this.rtAddress = rtAddress;
    }
}
