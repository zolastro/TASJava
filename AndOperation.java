import java.util.List;

class AndOperation extends LogicalOperation {
    
    public AndOperation(List<LogicalOperation> components) {
        super(components);
        operation = Operation.AND;
    }
    LogicalOperation negate() {    	
    	operation = Operation.OR;
    	components = negateAll(this.components);
    	
       return this;
    } 
    public String toString() {
        return operation + components.toString();
    }
}