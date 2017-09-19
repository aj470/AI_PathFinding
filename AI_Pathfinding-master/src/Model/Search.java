package Model;

import App.Heuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public abstract class Search
{
    public static PriorityQueue<Node> fringe;
    public static HashSet<Node> closedSet = new HashSet<>();
    public static ArrayList<Node> path = new ArrayList<>();
    Node start;
    Node goal;
    Grid grid = Heuristic.g;
    boolean found = false;
    long timeS, timeE;

    public void init(Comparator comp)
    {
        path.add(start);
        fringe = new PriorityQueue<Node>(comp);

        start = new Node(grid.getsVertex().get(0));
        start.setParent(start);
        goal = new Node(grid.getgVertex().get(0));

        fringe.add(start);

        //start timer
        timeS = System.currentTimeMillis();
    }

    public boolean doesContain(Node n)
    {
        for(Node m : fringe)
        {
            if((n.getCoord().getRow() == m.getCoord().getRow()) && (n.getCoord().getCol()) == m.getCoord().getCol())
            {
                return true;
            }
        }
        return false;
    }

    public boolean inClosed(Node n)
    {
        for(Node m : closedSet)
        {
            if((n.getCoord().getRow() == m.getCoord().getRow()) && (n.getCoord().getCol()) == m.getCoord().getCol())
            {
                //System.out.println(n.getCoord().getRow() + " " + n.getCoord().getCol());
                return true;
            }
        }
        return false;
    }

    public long getRunTime()
    {
        return timeE - timeS;
    }

    public double calcGCost(Vertex s)
    {
        return Math.abs(0);
    }

    public abstract void findPath();

}
