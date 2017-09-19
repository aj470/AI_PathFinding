package Model;

import View.Map;

public class UCS extends Search
{
    @Override
    public void findPath()
    {
        Node curr = null;
        while(!fringe.isEmpty())
        {
            curr = fringe.poll(); // get next node in the priority queue
            Map.colorClosed(curr.getCoord());
            closedSet.add(curr);

            if(curr.getCoord().getRow() == goal.getCoord().getRow() && curr.getCoord().getCol() == goal.getCoord().getCol())
            {
                //stop timer
                timeE = System.currentTimeMillis();
                Map.timerlabel.setText("Time: " + getRunTime() + "ms");
                found = true;
                break;
            }

            curr.findNeighbors();
            for(Node n : curr.getNeighbors())
            {
                if(!inClosed(n) && !doesContain(n))
                {
                    n.totalCost += n.getCost() + curr.totalCost;
                    n.setParent(curr);
                    Map.colorOpen(n.getCoord());
                    fringe.add(n);
                }
                double newCost = curr.totalCost + n.getCost();
                if(doesContain(n) && newCost < n.totalCost)
                {
                    fringe.remove(n);
                    Map.colorOpen(n.getCoord());
                    n.totalCost = newCost;
                    n.setParent(curr);
                    fringe.add(n);
                }
            }
        }

        if(found)
        {
            while(curr != start)
            {
                Map.colorFinal(curr.getCoord());
                path.add(curr);
                curr = curr.getParent();
            }
        }
        else
        {
            //unable to find path
        }
    }
}
