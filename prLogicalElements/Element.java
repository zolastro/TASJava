package prLogicalElements;
public interface Element {
    public void negate();
    public boolean isPositive();
    public Element clone();
    public boolean isLiteral();
}

