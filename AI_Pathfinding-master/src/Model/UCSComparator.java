package Model;

import java.util.Comparator;

public class UCSComparator implements Comparator<Node>
{
    public int compare(Node m, Node n)
    {
        if(m.totalCost < n.totalCost)
            return -1;
        else if(m.totalCost > n.totalCost)
            return 1;
        return 0;
    }
}
