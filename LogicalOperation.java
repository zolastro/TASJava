import java.util.ArrayList;
import java.util.List;

public abstract class LogicalOperation {
    public List<LogicalOperation> components;
    public static List<LogicalOperation> negateAll(List<LogicalOperation> components) {
        List<LogicalOperation> ans = new ArrayList<LogicalOperation>();
        ans.add(new NotOperation(components));
        return ans;
    }
    public LogicalOperation(List<LogicalOperation> components) {
        this.components = components;
    }
    abstract LogicalOperation negate();
}

class NotOperation extends LogicalOperation {
    public NotOperation(List<LogicalOperation> components) {
        super(components);
    }
    public LogicalOperation negate() {
        return null;
    }

    public String toString() {
        return "NotOperation " + components.toString();
    } 
}

class AndOperation extends LogicalOperation {
    
    public AndOperation(List<LogicalOperation> components) {
        super(components);
    }
    LogicalOperation negate() {
        return new OrOperation(negateAll(this.components));
    } 
    public String toString() {
        return "AndOperation " + components;
    }
}

class OrOperation extends LogicalOperation {
    public OrOperation(List<LogicalOperation> components) {
        super(components);
    }
    LogicalOperation negate() {
        return new AndOperation(negateAll(this.components));
    }

    public String toString() {
        return "OrOperation " + components.toString();
    }
}

class Literal extends LogicalOperation {
    public Literal(List<LogicalOperation> components) {
        super(components);
    }
    LogicalOperation negate() {
        return null;
    }

    public String toString() {
        return "Literal";
    }
}

