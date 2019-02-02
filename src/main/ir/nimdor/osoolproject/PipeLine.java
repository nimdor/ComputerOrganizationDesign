package main.ir.nimdor.osoolproject;

public class PipeLine {
    private PipeReg IF;
    private PipeReg ID;
    private PipeReg EX;
    private PipeReg MEM;
    private PipeReg WB;

    public PipeLine(PipeReg IF, PipeReg ID, PipeReg EX, PipeReg MEM, PipeReg WB) {
        this.IF = IF;
        this.ID = ID;
        this.EX = EX;
        this.MEM = MEM;
        this.WB = WB;
    }

}
