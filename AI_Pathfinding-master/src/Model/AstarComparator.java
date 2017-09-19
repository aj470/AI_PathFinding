package Model;

import java.util.Comparator;

public class AstarComparator implements Comparator<Node>
{
    public int compare(Node m, Node n)
    {
        if(m.getfCost() > n.getfCost())
        {
            return 1;
        }
        else if (m.getfCost() < n.getfCost())
        {
            return -1;
        }
        else
        {
            if(m.gethCost() > n.gethCost())
                return 1;
            else
                return -1;
        }
    }
}
