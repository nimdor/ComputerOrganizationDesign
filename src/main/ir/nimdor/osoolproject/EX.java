package ir.nimdor.osoolproject;

public class EX extends Component {
    private int ALUinp1, ALUinp2, ALUout;
    private boolean ALUzero;

    public EX() {
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        Commons.forwardPipeReg(prev, next);
        ALUinp1 = ALUinp2 = ALUout = 0;
        ALUzero = false;
        ALUinp1 = prev.getNonControlVariables().getRs();
        if (prev.getControlVariables().isAluSrc()) {
            ALUinp2 = prev.getNonControlVariables().getRd();
        } else {
            ALUinp2 = prev.getNonControlVariables().getRt();
        }
        if (prev.getControlVariables().getAluOP() == 2) {
            if (prev.getInstruction().funct == Commands.add.getValue()) {
                ALUout = ALUinp1 + ALUinp2;
            } else if (prev.getInstruction().funct == Commands.sub.getValue()) {
                ALUout = ALUinp1 - ALUinp2;
            } else if (prev.getInstruction().funct == Commands.and.getValue()) {
                ALUout = ALUinp1 & ALUinp2;
            } else if (prev.getInstruction().funct == Commands.or.getValue()) {
                ALUout = ALUinp1 | ALUinp2;
            } else if (prev.getInstruction().funct == Commands.nor.getValue()) {
                ALUout = ~(ALUinp1 | ALUinp2);
            } else if (prev.getInstruction().funct == Commands.slt.getValue()) {
                if (ALUinp1 < ALUinp2) {
                    ALUout = 1;
                } else {
                    ALUout = 0;
                }
            }
        } else if (prev.getControlVariables().getAluOP() == 0) {
            ALUout = ALUinp1 + ALUinp2;
        } else if (prev.getControlVariables().getAluOP() == 1) {
            if (prev.getInstruction().funct == Commands.beq.getValue()) {
                ALUzero = (ALUinp1 - ALUinp2 == 0);
            }
        }
        next.getNonControlVariables().setEXLogicalResult(ALUout);
        next.getNonControlVariables().setEXZeroResult(ALUzero);
    }

    @Override
    public void printInfo() {
        System.out.println("Main ALU input: " + ALUinp1 + " " + ALUinp2);
        System.out.println("Main ALU output: " + ALUout + " zero: " + ALUzero);

    }
}
