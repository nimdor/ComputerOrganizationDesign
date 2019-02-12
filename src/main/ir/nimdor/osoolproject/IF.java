package ir.nimdor.osoolproject;

// DORSA

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IF extends Component {
    PipeReg prev, next;
    int pc;
    String instruction_string;
    Instruction instruction, pending_instruction;
    ArrayList<Tag> tags;
    int stall = 0;
    int stall_condition;
    Instruction cache_instruction ;
    boolean pc_controll = false;
    public IF() {
        tags = new ArrayList<>();
        pc = 0;
        prev = null;
        next = null;
        pending_instruction = null;
        stall_condition = 0;
        create_list();   // makes the tags to read ! for beq
    }


    @Override
    public void run(PipeReg prev, PipeReg next) {
        this.prev = prev;
        this.next = next;
        next.getControlVariables().setStall(false);
        if (stall_condition == 1) {
            handle();
            instruction = next.getInstruction();
            return;
        }else if(stall_condition == 2){
            handleLW();
            instruction = next.getInstruction();
            return;
        }

        pc = get_value();
        //System.out.println("#pc value : " + pc);
        instruction_string = get_instruction(pc);
        while (instruction_string.contains(":")){
            pc ++ ;
            instruction_string = get_instruction(pc);
        }
        instruction = get_binary_insturction(instruction_string);
        //instruction.print();

        if (instruction.getOp() == 4) {
            set_stall_condition();
            pending_instruction = instruction;
            run(prev, next);
            return;

        }
//        System.out.println((cache_instruction == null)?"null":"object" +  cache_instruction.getOp() + " " + instruction.getOp() );
        if (cache_instruction != null && cache_instruction.getOp() == 35 && instruction.getOp() != 35) {

                if ( cache_instruction.getRt() == instruction.getRt() || cache_instruction.getRt() == instruction.getRs()){
                    pending_instruction = instruction;
                    stall_condition = 2;
                    stall = 1 ;
                    run (prev,next) ;
                    return ;
                }
        }
        next.setInstruction(instruction);
        pc += 1;
        next.getNonControlVariables().setPc(pc);
//        System.out.println("NO STALL " + next.getControlVariables().isStall());
        cache_instruction = instruction;
    }

    void set_stall_condition() {

        stall_condition = 1;
        stall = 4;
    }

    void handleLW() {
        if (stall == 1) {
            next.setInstruction(pending_instruction);
            cache_instruction = pending_instruction ;
            pc += 1;
            next.getNonControlVariables().setPc(pc);
            stall--;
            return;
        }
        cache_instruction = null;
        next.getControlVariables().setStall(true);
        next.setInstruction(new Instruction("stall", next.getInstruction().getTags()));
        stall--;
        if (stall == 0) {
            stall_condition = 0;
        }
        return;
    }


    void handle() {
        if (stall == 4) {
            next.setInstruction(pending_instruction);
            cache_instruction = pending_instruction ;
            pc += 1;
            next.getNonControlVariables().setPc(pc);
            stall--;
            return;
        }
        cache_instruction = null ;
        next.getControlVariables().setStall(true);
        next.setInstruction(new Instruction("stall", next.getInstruction().getTags()));
        stall--;
        if (stall == 0) {
            stall_condition = 0;
        }
        return;
    }

    @Override
    public void printInfo() {
        System.out.print("IF information: ");
        instruction.print();
//        System.out.println("Instruction : ");
        //TODO
//        System.out.println("Clock : " + (pc * 4) + 16);    // starting pc or ending pc ?
        System.out.print("PC: " + ((pc * 4)+16));    // starting pc or ending pc ?
        System.out.println("PCsrc : " + pc_controll);
    }

    private void create_list() {


        // The name of the file to open.
        String fileName = "file.txt";

        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int index = 0;
            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains(":")) {
                    line = line.substring(0, line.length() - 1); // remove the ":"
                    tags.add(new Tag(line, index));
                }

                index++;
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    // decide the value of pc --> MUX    ----> from prev
    public int get_value() {
        pc_controll = prev.isPc_control();

        if (prev.isPc_control())
            pc = prev.getNonControlVariables().getPc();


        return pc;
    }

    // read instruction of the indexed line from the file  --> input line
    private String get_instruction(int pcc) {   // ( done )
        String line = "null";
        try {
            line = Files.readAllLines(Paths.get("file.txt")).get(pcc);
        } catch (Exception e) {
            System.out.println("EXCEPT CONDITION HAPPENED ! ");
            stall_condition = 1 ;
            stall = 100;
            run(prev,next);

        }
        return line;
    }

    // translate string instruction to binary instruction  --> instruction memory
    private Instruction get_binary_insturction(String instruct) { // ( done )
        Instruction instruction = new Instruction(instruct, tags);
        return instruction;
    }


}
