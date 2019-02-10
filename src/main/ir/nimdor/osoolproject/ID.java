package ir.nimdor.osoolproject;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

//Dorsa
public class ID extends Component {
    PipeReg prev , next;
    Instruction instruction ;
    int[] registerfile  ;
    ArrayList<Integer> read_values  ;

    public ID (int []  registerfile){
        this.registerfile =  registerfile;

    }

    @Override
    public void run(PipeReg prev , PipeReg next ) {
        read_values = new ArrayList<>();

        this.prev = prev;
        this.next = next;
        instruction = prev.getInstruction();
        next.setInstruction(instruction);
        next.getNonControlVariables().setPc(prev.getNonControlVariables().getPc());

        updatememory();
        updatecontrolvalues();
    }
    @Override
    public void printInfo() {
        prev.instruction.print();
        System.out.println("ID information :");
        System.out.println("read values :");
        read_values.forEach(System.out::println);
        System.out.println("$t0 - $t4 : ");
        System.out.println(registerfile[8] + " " + registerfile[9] + " " + registerfile[10] + " " + registerfile[11] + " " + registerfile[12]);
        System.out.println("ra :" + registerfile[31]);
        System.out.println("sp : " + registerfile[29]);


    }

    private void updatememory() {

        NonControlVariables nonvar = next.getNonControlVariables();
        nonvar.setRd(registerfile[instruction.getRd()]);
        nonvar.setRs(registerfile[instruction.getRs()]);
        nonvar.setRt(registerfile[instruction.getRt()]);
        read_values.add(nonvar.getRs());
        read_values.add(nonvar.getRt());
        read_values.add(nonvar.getRd());
    }
    private void updatecontrolvalues() {

        ControlVariables controlVariables = new ControlVariables();
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

    public boolean write ( int index , int data , boolean regdst ) {
        if (regdst == false) return false;
        registerfile[index] = data;
        return true;
    }


}
