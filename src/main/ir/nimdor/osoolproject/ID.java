package ir.nimdor.osoolproject;


import java.util.ArrayList;

//Dorsa
public class ID extends Component {
    private Instruction instruction;
    private int[] registerfile;
    private ArrayList<Integer> read_values;
    PipeReg prev , next ;
    public ID(int[] registerfile) {
        this.registerfile = registerfile;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        read_values = new ArrayList<>();
        instruction = prev.getInstruction();
        next.setControlVariables(new ControlVariables());
        next.setInstruction(instruction);
        if (prev.getControlVariables().isStall()) {
            next.getControlVariables().setStall(true);
            return;
        }
        updatememory(next);
        updatecontrolvalues(prev ,next);
        next.getNonControlVariables().setPc(prev.getNonControlVariables().getPc());
    }

    @Override
    public void printInfo() {
        System.out.print("ID information: ");
        instruction.print();
        System.out.print("read values: ");
        read_values.forEach((n) -> System.out.print(" " + n));
        System.out.print("   $t0 - $t4 : ");
        System.out.print(registerfile[8] + " " + registerfile[9] + " " + registerfile[10] + " " +
                registerfile[11] + " " + registerfile[12]);
        System.out.print("  ra :" + registerfile[31]);
        System.out.println("  sp : " + registerfile[29]);
    }

    private void updatememory(PipeReg next) {
        NonControlVariables nonvar = new NonControlVariables();
        nonvar.setRd(registerfile[instruction.getRd()]);
        nonvar.setRs(registerfile[instruction.getRs()]);
        nonvar.setRt(registerfile[instruction.getRt()]);
        read_values.add(nonvar.getRs());
        read_values.add(nonvar.getRt());
        read_values.add(nonvar.getRd());
        next.setNonControlVariables(nonvar);
    }

    private void updatecontrolvalues(PipeReg prev , PipeReg next) {
        ControlVariables controlVariables = new ControlVariables();
        controlVariables.setStall( prev.getControlVariables().isStall());
        // set controls based on opcode
        if (instruction.getOp() == Commands.Rtype.getValue()) {
            controlVariables.setAluOP(2);
            controlVariables.setRegDest(true);
            controlVariables.setAluSrc(false);
            controlVariables.setMemToReg(false);
            controlVariables.setRegWrite(true);
            controlVariables.setMemRead(false);
            controlVariables.setMemWrite(false);
            controlVariables.setBranch(false);
        } else if (instruction.getOp() == Commands.lw.getValue() || instruction.getOp() == Commands.sw.getValue()) {
            controlVariables.setAluOP(0);
            if (instruction.getOp() == Commands.lw.getValue()) {
                controlVariables.setRegDest(false);
                controlVariables.setAluSrc(true);
                controlVariables.setMemToReg(true);
                controlVariables.setRegWrite(true);
                controlVariables.setMemRead(true);
                controlVariables.setMemWrite(false);
                controlVariables.setBranch(false);
            } else {
                controlVariables.setRegDest(false);// not impo
                controlVariables.setAluSrc(true);
                controlVariables.setMemToReg(false); // not impo
                controlVariables.setRegWrite(false);
                controlVariables.setMemRead(false);
                controlVariables.setMemWrite(true);
                controlVariables.setBranch(false);

            }
        } else if (instruction.getOp() == Commands.beq.getValue()) {
            controlVariables.setAluOP(1);
            controlVariables.setRegDest(false); // not impo
            controlVariables.setAluSrc(false);
            controlVariables.setMemToReg(false); // not impo
            controlVariables.setRegWrite(false);
            controlVariables.setMemRead(false);
            controlVariables.setMemWrite(false);
            controlVariables.setBranch(true);
        }
        next.setControlVariables(controlVariables);
    }
}
