package ir.nimdor.osoolproject;

public class PipeReg {
    ControlVariables controlVariables;
    NonControlVariables nonControlVariables;
    Instruction instruction ;
    boolean pc_control ;
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
}
