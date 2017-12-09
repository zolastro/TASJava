import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<LogicalOperation> literals = new ArrayList<LogicalOperation>();
        literals.add(new Literal(null));
        literals.add(new Literal(null));
        
        LogicalOperation orOperation1 = new OrOperation(literals);
        LogicalOperation orOperation2 = new OrOperation(literals);

        List<LogicalOperation> orList = new ArrayList<LogicalOperation>();
        orList.add(orOperation1);
        orList.add(orOperation2);
        LogicalOperation andOperation = new AndOperation(orList);        
        System.out.println(andOperation.negate());
        
    }
}