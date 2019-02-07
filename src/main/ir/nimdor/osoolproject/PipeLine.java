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

    public boolean run(){
        try {
            IFcomponent.run(MEMReg, IFReg);
            IDcomponent.run(IFReg, IDReg);
            EXcomponent.run(IDReg, EXReg);
            MEMcomponent.run(EXReg, MEMReg);
            WBcomponent.run(MEMReg, WBReg);
            IFcomponent.printInfo();
            IDcomponent.printInfo();
            EXcomponent.printInfo();
            MEMcomponent.printInfo();
            WBcomponent.printInfo();
            return true;
        }catch (Exception e) {
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
