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
        if (prev.controlVariables.isMemWrite()){
            memory[prev.nonControlVariables.getRd() + prev.getNonControlVariables().getRt()] =
                    prev.getNonControlVariables().getRs();
        }else if(prev.controlVariables.isMemRead()){
            next.nonControlVariables
                    .setMEMResult(memory[prev.nonControlVariables.getRd() + prev.getNonControlVariables().getRt()]);
            lastRead = memory[prev.nonControlVariables.getRd() + prev.getNonControlVariables().getRt()];
        }
        next.setPc_control(prev.controlVariables.isBranch() & prev.nonControlVariables.getEXZeroResult());
    }

    @Override
    public void printInfo() {
        System.out.println("Last thing read from memory: " + lastRead);
    }
}
