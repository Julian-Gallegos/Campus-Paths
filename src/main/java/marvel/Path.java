package marvel;

/**
 * This class represents one step in a path from a parent node to a child node in a graph
 */
public class Path {

    private static final boolean DEBUG = false;
    private String parent, child, edge;

    // Representation Invariant:
    // For all parent and child, parent != child
    // parent, child, and edge != null
    //
    //
    // Abstraction Function:
    //  AF(this) = a triple of Strings, t, such that
    //   t.parent connects to t.child through t.edge

    public Path(String parentNode, String childNode, String edge) {
        this.parent = parentNode;
        this.child = childNode;
        this.edge = edge;
        this.checkRep();
    }

    public String getParent() {
        return this.parent;
    }

    public String getChild() {
        return this.child;
    }

    public String getEdge() {
        return this.edge;
    }

    private void checkRep() {
        if (DEBUG) {
            // Check data members are not null
            assert this.parent != null;
            assert this.child != null;
            assert this.edge != null;

            // Check parent != child;
            assert !this.parent.equals(this.child);
        }
    }
}
