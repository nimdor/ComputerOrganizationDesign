package ir.nimdor.osoolproject;

public class Main {
    public static void main(String[] args) {
        PipeLine pipeLine = setupPipeLine();
    }

    public static PipeLine setupPipeLine(){
        PipeReg ifPipReg = new PipeReg();
        PipeReg idPipReg = new PipeReg();
        PipeReg exPipReg = new PipeReg();
        PipeReg memPipReg = new PipeReg();
        PipeReg wbPipReg = new PipeReg();
        PipeLine pipeLine = new PipeLine(ifPipReg, idPipReg, exPipReg, memPipReg, wbPipReg);
        return pipeLine;
    }
}
