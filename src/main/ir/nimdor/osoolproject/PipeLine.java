package ir.nimdor.osoolproject;

public class PipeLine {

    private PipeReg IFReg;
    private PipeReg IDReg;
    private PipeReg EXReg;
    private PipeReg MEMReg;
    private PipeReg WBReg;
    private IF IFcomponent;
    private ID IDcomponent;
    private EX EXcomponent;
    private MEM MEMcomponent;
    private WB WBcomponent;
    private int clock = 0;

    public PipeLine(PipeReg IFReg, PipeReg IDReg, PipeReg EXReg, PipeReg MEMReg, PipeReg WBReg,
                    IF IFcomponent, ID IDcomponent, EX EXcomponent, MEM MEMcomponent, WB WBcomponent) {
        this.IFReg = IFReg;
        this.IDReg = IDReg;
        this.EXReg = EXReg;
        this.MEMReg = MEMReg;
        this.WBReg = WBReg;
        this.IFcomponent = IFcomponent;
        this.IDcomponent = IDcomponent;
        this.EXcomponent = EXcomponent;
        this.MEMcomponent = MEMcomponent;
        this.WBcomponent = WBcomponent;
    }

    public boolean run() {
        try {
//            System.err.println("ho");
            clock++;
            System.out.println("------------------------PROGRAM DATA -----------------------------");
            System.out.print("branch:" + EXReg.getControlVariables().isBranch());
            System.out.print(" memRead:" + EXReg.getControlVariables().isMemRead());
            System.out.print(" memToReg:" + MEMReg.getControlVariables().isMemToReg());
            System.out.print(" memWrite:" + EXReg.getControlVariables().isMemWrite());
            System.out.print(" aluSrc:" + IDReg.getControlVariables().isAluSrc());
            System.out.print(" regWrite:" + WBReg.getControlVariables().isRegWrite());
            System.out.print(" aluOP:" + IDReg.getControlVariables().getAluOP());
            System.out.println();

            WBcomponent.run(MEMReg, WBReg);
            MEMcomponent.run(EXReg, MEMReg);
            EXcomponent.run(IDReg, EXReg);
            IDcomponent.run(IFReg, IDReg);
            IFcomponent.run(WBReg, IFReg);
            IFcomponent.printInfo();
            System.out.println(" clock: " + clock);
//            System.out.println("IF/ID");
            IFReg.printInfo();

            IDcomponent.printInfo();
//            System.out.println("ID/EX");
            IDReg.printInfo();

            EXcomponent.printInfo();
//            System.out.println("EX/MEM");
            EXReg.printInfo();

            MEMcomponent.printInfo();
//            System.out.println("MEM/WB");
            MEMReg.printInfo();

            WBcomponent.printInfo();

            System.out.println("-----------------------------------------------------");
            return true;
        } catch (Exception e) {
            System.err.println("WTF?");
            e.printStackTrace();
            return false;
        }
    }


    public PipeReg getIFReg() {
        return IFReg;
    }

    public void setIFReg(PipeReg IFReg) {
        this.IFReg = IFReg;
    }

    public PipeReg getIDReg() {
        return IDReg;
    }

    public void setIDReg(PipeReg IDReg) {
        this.IDReg = IDReg;
    }

    public PipeReg getEXReg() {
        return EXReg;
    }

    public void setEXReg(PipeReg EXReg) {
        this.EXReg = EXReg;
    }

    public PipeReg getMEMReg() {
        return MEMReg;
    }

    public void setMEMReg(PipeReg MEMReg) {
        this.MEMReg = MEMReg;
    }

    public PipeReg getWBReg() {
        return WBReg;
    }

    public void setWBReg(PipeReg WBReg) {
        this.WBReg = WBReg;
    }

    public IF getIFcomponent() {
        return IFcomponent;
    }

    public void setIFcomponent(IF IFcomponent) {
        this.IFcomponent = IFcomponent;
    }

    public ID getIDcomponent() {
        return IDcomponent;
    }

    public void setIDcomponent(ID IDcomponent) {
        this.IDcomponent = IDcomponent;
    }

    public EX getEXcomponent() {
        return EXcomponent;
    }

    public void setEXcomponent(EX EXcomponent) {
        this.EXcomponent = EXcomponent;
    }

    public MEM getMEMcomponent() {
        return MEMcomponent;
    }

    public void setMEMcomponent(MEM MEMcomponent) {
        this.MEMcomponent = MEMcomponent;
    }

    public WB getWBcomponent() {
        return WBcomponent;
    }

    public void setWBcomponent(WB WBcomponent) {
        this.WBcomponent = WBcomponent;
    }
}
