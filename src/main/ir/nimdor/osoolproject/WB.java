package ir.nimdor.osoolproject;

public class WB extends Component {

    int[] registers;
    private Instruction instruction;

    public WB(int[] registers) {
        this.registers = registers;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        instruction = prev.getInstruction();
        if (prev.getControlVariables().isStall())
            return;
        if (prev.getControlVariables().isMemToReg()) {
            Commons.writeToReg(registers, prev.getInstruction().getRd(), prev.getNonControlVariables().getMEMResult(),
                    prev.getControlVariables().isRegWrite());
        } else {
            Commons.writeToReg(registers, prev.getInstruction().getRd(),
                    prev.getNonControlVariables().getEXLogicalResult(),
                    prev.getControlVariables().isRegWrite());
        }
    }

    @Override
    public void printInfo() {
        System.out.print("WB information: ");
        instruction.print();
    }
}
