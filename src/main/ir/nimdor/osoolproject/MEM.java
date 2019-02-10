package ir.nimdor.osoolproject;

public class MEM extends Component {
    int[] memory;
    private int lastRead;
    public MEM(int[] memory) {
        this.memory = memory;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        Commons.forwardPipeReg(prev, next);
        lastRead = -1;
        if (prev.getControlVariables().isMemWrite()){
            memory[prev.getInstruction().getOffset() + prev.getNonControlVariables().getRs()] =
                    prev.getNonControlVariables().getRt();
            next.getInstruction().setRd(next.getInstruction().getRt());
        }else if(prev.getControlVariables().isMemRead()){
            next.getNonControlVariables()
                    .setMEMResult(memory[prev.getInstruction().getOffset() + prev.getNonControlVariables().getRs()]);
            lastRead = memory[prev.getNonControlVariables().getRd() + prev.getNonControlVariables().getRt()];
            prev.setMEMcacheRD();
            next.getInstruction().setRd(next.getInstruction().getRt());
        }
        next.setPc_control(prev.getControlVariables().isBranch() & prev.getNonControlVariables().getEXZeroResult());
        next.getNonControlVariables().setPc(prev.getInstruction().getOffset());
    }

    @Override
    public void printInfo() {
        System.out.println("Last thing read from memory: " + lastRead);
    }
}
