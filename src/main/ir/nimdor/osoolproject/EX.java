package ir.nimdor.osoolproject;

public class EX extends Component {
    private int ALUinp1, ALUinp2, ALUout;
    private boolean ALUzero;
    private int[] registers;
    private Instruction instruction;
    private int oldpc, newpc;

    public EX(int[] registers) {
        this.registers = registers;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        Commons.forwardPipeReg(prev, next);
        oldpc = prev.getNonControlVariables().getPc();
        newpc = prev.getInstruction().getOffset();
        instruction = prev.getInstruction();
        if (next.getMEMcacheRD() != -1) {
            registers[next.getMEMcacheRD()] = next.getMEMcacheRDval();
        }
        if (prev.getControlVariables().isStall())
            return;
        ALUinp1 = ALUinp2 = ALUout = 0;
        ALUzero = false;
        ALUinp1 = registers[prev.getInstruction().getRs()];
        if (prev.getControlVariables().isAluSrc()) {
            ALUinp2 = prev.getInstruction().getOffset();
        } else {
            ALUinp2 = registers[prev.getInstruction().getRt()];
        }
        if (prev.getControlVariables().getAluOP() == 2) {
            if (prev.getInstruction().getFunct() == Commands.add.getValue()) {
                ALUout = ALUinp1 + ALUinp2;
            } else if (prev.getInstruction().getFunct() == Commands.sub.getValue()) {
                ALUout = ALUinp1 - ALUinp2;
            } else if (prev.getInstruction().getFunct() == Commands.and.getValue()) {
                ALUout = ALUinp1 & ALUinp2;
            } else if (prev.getInstruction().getFunct() == Commands.or.getValue()) {
                ALUout = ALUinp1 | ALUinp2;
            } else if (prev.getInstruction().getFunct() == Commands.nor.getValue()) {
                ALUout = ~(ALUinp1 | ALUinp2);
            } else if (prev.getInstruction().getFunct() == Commands.slt.getValue()) {
                if (ALUinp1 < ALUinp2) {
                    ALUout = 1;
                } else {
                    ALUout = 0;
                }
            }
            registers[prev.getInstruction().getRd()] = ALUout;
        } else if (prev.getControlVariables().getAluOP() == 0) {
            ALUout = ALUinp1 + ALUinp2;
        } else if (prev.getControlVariables().getAluOP() == 1) {
//            if (prev.getInstruction().funct == Commands.beq.getValue()) {
                ALUzero = (ALUinp1 - ALUinp2 == 0);
//            }
        }
        next.getNonControlVariables().setEXLogicalResult(ALUout);
        next.getNonControlVariables().setEXZeroResult(ALUzero);
    }

    @Override
    public void printInfo() {
        System.out.print("EX information: ");
        instruction.print();
        System.out.print("Main ALU: input:" + ALUinp1 + " " + ALUinp2 + " output: " + ALUout + " zero: " + ALUzero);
        System.out.println(" Secondary outs: " + oldpc + " " + newpc);
    }
}
