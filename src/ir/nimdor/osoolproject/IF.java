package ir.nimdor.osoolproject;

public class IF extends Component {
    PipeReg prev , next  ;
    int pc ;
    @Override
    public void run(PipeReg prev , PipeReg next) {
        this.prev =  prev ;
        this.next = next ;
        pc = get_value ();
    }

    public int get_value(){

        return pc + 1 ;
    }

}
