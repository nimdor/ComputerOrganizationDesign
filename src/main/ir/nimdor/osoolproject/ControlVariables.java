package ir.nimdor.osoolproject;

public class ControlVariables {
    private int aluOP;
    private boolean regDest, branch, memRead, memToReg, memWrite, aluSrc, regWrite, Stall;


    public int getAluOP() {
        return aluOP;
    }

    public void setAluOP(int aluOP) {
        this.aluOP = aluOP;
    }

    public boolean isRegDest() {
        return regDest;
    }

    public void setRegDest(boolean regDest) {
        this.regDest = regDest;
    }

    public boolean isBranch() {
        return branch;
    }

    public void setBranch(boolean branch) {
        this.branch = branch;
    }

    public boolean isMemRead() {
        return memRead;
    }

    public void setMemRead(boolean memRead) {
        this.memRead = memRead;
    }

    public boolean isMemToReg() {
        return memToReg;
    }

    public void setMemToReg(boolean memToReg) {
        this.memToReg = memToReg;
    }

    public boolean isMemWrite() {
        return memWrite;
    }

    public void setMemWrite(boolean memWrite) {
        this.memWrite = memWrite;
    }

    public boolean isAluSrc() {
        return aluSrc;
    }

    public void setAluSrc(boolean aluSrc) {
        this.aluSrc = aluSrc;
    }

    public boolean isRegWrite() {
        return regWrite;
    }

    public void setRegWrite(boolean regWrite) {
        this.regWrite = regWrite;
    }


    public boolean isStall() {
        return Stall;
    }

    public void setStall(boolean stall) {
        Stall = stall;
    }
}
