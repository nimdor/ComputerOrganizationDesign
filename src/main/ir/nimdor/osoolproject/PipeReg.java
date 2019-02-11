package ir.nimdor.osoolproject;

import java.util.ArrayList;

public class PipeReg {
    private ControlVariables controlVariables = new ControlVariables();
    private NonControlVariables nonControlVariables = new NonControlVariables();
    private Instruction instruction = new Instruction("stall", new ArrayList<Tag>() {{}});
    private boolean pc_control;
    private int MEMcacheRD, MEMcacheRDval;

    public int getMEMcacheRD() {
        return MEMcacheRD;
    }

    public void setMEMcacheRD(int MEMcacheRD) {
        this.MEMcacheRD = MEMcacheRD;
    }

    public int getMEMcacheRDval() {
        return MEMcacheRDval;
    }

    public void setMEMcacheRDval(int MEMcacheRDval) {
        this.MEMcacheRDval = MEMcacheRDval;
    }

    public ControlVariables getControlVariables() {
        return controlVariables;
    }

    public void setControlVariables(ControlVariables controlVariables) {
        this.controlVariables = controlVariables;
    }

    public NonControlVariables getNonControlVariables() {
        return nonControlVariables;
    }

    public void setNonControlVariables(NonControlVariables nonControlVariables) {
        this.nonControlVariables = nonControlVariables;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public boolean isPc_control() {
        return pc_control;
    }

    public void setPc_control(boolean pc_control) {
        this.pc_control = pc_control;
    }

    public void printInfo() {

        System.out.print("pipereg vars:");
        System.out.print(" RS:" + nonControlVariables.getRs());
        System.out.print(" RD:" + nonControlVariables.getRd());
        System.out.print(" RT:" + nonControlVariables.getRt());
        System.out.print(" // regDest:" + controlVariables.isRegDest());
        System.out.print(" branch:" + controlVariables.isBranch());
        System.out.print(" memRead:" + controlVariables.isMemRead());
        System.out.print(" memToReg:" + controlVariables.isMemToReg());
        System.out.print(" memWrite:" + controlVariables.isMemWrite());
        System.out.print(" aluSrc:" + controlVariables.isAluSrc());
        System.out.print(" regWrite:" + controlVariables.isRegWrite());
        System.out.print(" stall:" + controlVariables.isStall());
        System.out.print(" aluOP:" + controlVariables.getAluOP());
        System.out.println();


    }
}
