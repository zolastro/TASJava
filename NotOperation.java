import java.util.List;

class NotOperation extends LogicalOperation {
    public NotOperation(List<LogicalOperation> components) {
        super(components);
        operation = Operation.NOT;
    }
    public LogicalOperation negate() { // si esta en estado de next hace un negate all a sus componentes si esta en un estado de negate all pasa al estado next
        
    	if(isStateNext) {
    		isStateNext = false;
    		components = negateAll(components);
    		return components.get(0); // al ser un not siempre hay solo una ||| DEBERIAMOS BORRAR ESTO DEL ARBOL
    	}else {
    		isStateNext = true;
    		return components.get(0);
    	}
    }

    public String toString() {
        return operation + components.toString();
    } 
}