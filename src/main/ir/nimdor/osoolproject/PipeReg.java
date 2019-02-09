package ir.nimdor.osoolproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PipeReg {
    ControlVariables controlVariables = new ControlVariables();
    NonControlVariables nonControlVariables = new NonControlVariables();
    Instruction instruction = new Instruction("stall", new ArrayList<Tag>() {{new Tag("stall", 0);}});
    boolean pc_control ;


    public void setPc_control(boolean pc_control) {
        this.pc_control = pc_control;
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
}
