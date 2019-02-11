package ir.nimdor.osoolproject;

public class WB extends Component {

    int[] memory;

    public WB(int[] memory) {
        this.memory = memory;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        if(prev.getControlVariables().isStall())
            return;
        if (prev.getControlVariables().isMemToReg()) {
            Commons.writeToReg(memory, prev.getInstruction().getRd(), prev.getNonControlVariables().getMEMResult(),
                    prev.getControlVariables().isRegWrite());
        } else {
            Commons.writeToReg(memory, prev.getNonControlVariables().getRd(), prev.getNonControlVariables().getEXLogicalResult(),
                    prev.getControlVariables().isRegWrite());
        }
    }

    @Override
    public void printInfo() {

    }

    public boolean write(int index, int data, boolean regdst) {
        if (regdst == false) return false;
        memory[index] = data;
        return true;
    }
}
