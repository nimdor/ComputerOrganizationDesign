package ir.nimdor.osoolproject;

public class Main {
    public static void main(String[] args) {
        int[] registers = new int[Configs.REGISTERS_SIZE];
        int[] memory = new int[Configs.MEMORY_SIZE];
        PipeLine pipeLine = setupPipeLine(registers, memory);
    }

    public static PipeLine setupPipeLine(int[] registers, int[] memory){
        PipeReg ifPipReg = new PipeReg();
        PipeReg idPipReg = new PipeReg();
        PipeReg exPipReg = new PipeReg();
        PipeReg memPipReg = new PipeReg();
        PipeReg wbPipReg = new PipeReg();
        IF IFinstance = new IF();
        ID IDinstance = new ID();
        EX EXinstance = new EX();
        MEM MEMinstance = new MEM(memory);
        WB WBinstance = new WB(registers);
        PipeLine pipeLine = new PipeLine(ifPipReg, idPipReg, exPipReg, memPipReg, wbPipReg
            ,IFinstance, IDinstance, EXinstance, MEMinstance, WBinstance);
        return pipeLine;
    }
}
