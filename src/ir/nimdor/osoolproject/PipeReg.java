package ir.nimdor.osoolproject;

public class PipeReg {
    ControlVariables controlVariables;
    NonControlVariables nonControlVariables;
    Instruction instruction ;

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
}
