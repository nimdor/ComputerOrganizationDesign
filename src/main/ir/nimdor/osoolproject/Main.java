package ir.nimdor.osoolproject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PipeLine pipeLine = setupPipeLine();
        Scanner input = new Scanner(System.in);
        int clockNumber = input.nextInt();
        while (clockNumber > 0) {
            pipeLine = setupPipeLine();
            while (clockNumber > 0) {
                pipeLine.run();
                clockNumber--;
            }
            clockNumber = input.nextInt();
        }
    }

    static PipeLine setupPipeLine() {             //  where is register value setup ?! #
        int[] registers = new int[Configs.REGISTERS_SIZE];
        int[] tempregisters = new int[Configs.REGISTERS_SIZE];
        registers[29] = 16711680;
        int[] memory = new int[Configs.MEMORY_SIZE];
        memory[256] = 100;
        PipeReg ifPipReg = new PipeReg();
        PipeReg idPipReg = new PipeReg();
        PipeReg exPipReg = new PipeReg();
        PipeReg memPipReg = new PipeReg();
        PipeReg wbPipReg = new PipeReg();
        IF IFinstance = new IF();
        ID IDinstance = new ID(registers);
        EX EXinstance = new EX(tempregisters);
        MEM MEMinstance = new MEM(memory);
        WB WBinstance = new WB(registers);
        return new PipeLine(ifPipReg, idPipReg, exPipReg, memPipReg, wbPipReg
                , IFinstance, IDinstance, EXinstance, MEMinstance, WBinstance);
    }
}
