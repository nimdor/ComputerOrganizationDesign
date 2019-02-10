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
    int MEMcacheRS, MEMcacheRT, EXcacheRS, EXcacheRT, MEMcacheRSval, MEMcacheRTval, EXcacheRSval, EXcacheRTval;

    public int getMEMcacheRD() {
        return MEMcacheRD;
    }

    public void setMEMcacheRD(int MEMcacheRD) {
        this.MEMcacheRD = MEMcacheRD;
    }

    public int getEXcacheRD() {
        return EXcacheRD;
    }

    public void setEXcacheRD(int EXcacheRD) {
        this.EXcacheRD = EXcacheRD;
    }

    public int getMEMcacheRDval() {
        return MEMcacheRDval;
    }

    public void setMEMcacheRDval(int MEMcacheRDval) {
        this.MEMcacheRDval = MEMcacheRDval;
    }

    public int getEXcacheRDval() {
        return EXcacheRDval;
    }

    public void setEXcacheRDval(int EXcacheRDval) {
        this.EXcacheRDval = EXcacheRDval;
    }

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

    public void printInfo(){

        System.out.println("pipreg noncontrol variables :");
        System.out.println("RS : "+ nonControlVariables.getRs());
        System.out.println("RD : " + nonControlVariables.getRd() );
        System.out.println("RT : " + nonControlVariables.getRt());


    }
}
