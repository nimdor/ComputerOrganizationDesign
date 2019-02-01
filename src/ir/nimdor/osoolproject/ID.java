package ir.nimdor.osoolproject;
//Dorsa
public class ID extends Component {
    PipeReg prev , next;
    Instruction instruction ;

    @Override
    public void run(PipeReg prev , PipeReg next ) {
        this.prev = prev ;
        this.next = next ;
        instruction = prev.getInstruction() ;

        updatememory () ;
        updatecontrolvalues();
        setcontrolvalues();
        setNonControlValues ();
    }

    private void updatememory(){


    }
    private void updatecontrolvalues(){

    }

    private void setcontrolvalues (){

    }

    private void  setNonControlValues (){

    }

}
