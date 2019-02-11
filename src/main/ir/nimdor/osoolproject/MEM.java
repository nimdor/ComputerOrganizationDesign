package ir.nimdor.osoolproject;

public class MEM extends Component {
    int[] memory;
    Instruction instruction;
    private int lastRead;

    public MEM(int[] memory) {
        this.memory = memory;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        prev.setMEMcacheRD(-1);
        Commons.forwardPipeReg(prev, next);
        instruction = prev.getInstruction();
        if (prev.getControlVariables().isStall())
            return;
        lastRead = -1;
        if (prev.getControlVariables().isMemWrite()) {
            memory[prev.getInstruction().getOffset() + prev.getNonControlVariables().getRs()] =
                    prev.getNonControlVariables().getRt();
            next.getInstruction().setRd(next.getInstruction().getRt());
        } else if (prev.getControlVariables().isMemRead()) {
            next.getNonControlVariables()
                    .setMEMResult(memory[prev.getInstruction().getOffset() + prev.getNonControlVariables().getRs()]);
            lastRead = memory[prev.getInstruction().getOffset() + prev.getNonControlVariables().getRs()];
            next.getInstruction().setRd(next.getInstruction().getRt());
            prev.setMEMcacheRD(prev.getInstruction().getRt());
            prev.setMEMcacheRDval(lastRead);
        }
        next.setPc_control(prev.getControlVariables().isBranch() & prev.getNonControlVariables().getEXZeroResult());
        next.getNonControlVariables().setPc(prev.getInstruction().getOffset());
    }

    @Override
    public void printInfo() {
        System.out.print("MEM information :");
        instruction.print();
        System.out.println("Last thing read from memory: " + lastRead);
    }
}
