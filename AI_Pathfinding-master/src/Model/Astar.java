package Model;

import View.Map;

import java.util.PriorityQueue;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class Astar extends Search
{

    public void findPath(String Heuristic)
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
                Map.timerlabel.setText("Time: " + getRunTime() + "ms ");
                found = true;
                break;
            }

            curr.findNeighbors();
            for(Node n : curr.getNeighbors())
            {
                if(!inClosed(n) && !doesContain(n))
                {
                    if(Heuristic.equals("Manhattan"))
                    {
                        n.sethCost(Man_distance(n, goal));
                    }
                    if(Heuristic.equals("bird_eye"))
                    {
                        n.sethCost(bird_eye(n, goal));
                    }
                    if(Heuristic.equals("diagonal"))
                    {
                        n.sethCost(diagonal(n, goal));
                    }
                    if(Heuristic.equals("Chebyshev"))
                    {
                        n.sethCost(Chebyshev(n, goal));
                    }
                    if(Heuristic.equals("Tie_Cross"))
                    {
                        n.sethCost(Tie_Cross(n, goal));
                    }

                    n.setgCost(n.getCost()+curr.getgCost());
                    n.setParent(curr);
                    n.setfCost(n.getfCost());
                    Map.colorOpen(n.getCoord());
                    fringe.add(n);
                }

                double newCost = curr.getgCost() + n.getCost();
                if(doesContain(n) && (newCost < curr.getCost() + n.getCost()))
                {
                    fringe.remove(n);
                    Map.colorOpen(n.getCoord());
                    n.setgCost(newCost);
                    n.setfCost(n.getfCost());
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
                curr = curr.getParent();
            }
        }
        else
        {
            //unable to find path
        }

    }




    public double Man_distance(Node current, Node goal)
    {
        int x1 = current.getCoord().getRow();
        int y1 = current.getCoord().getCol();
        int x2 = goal.getCoord().getRow();
        int y2 = goal.getCoord().getCol();

        return (Math.abs(x2-x1)+Math.abs(y2-y1));
    }

    public double bird_eye(Node current, Node goal)
    {
        int x1 = current.getCoord().getRow();
        int y1 = current.getCoord().getCol();
        int x2 = goal.getCoord().getRow();
        int y2 = goal.getCoord().getCol();

        return Math.sqrt(Math.pow(2,(x2-x1))+Math.pow(2, (y2-y1)));
    }

    public double diagonal(Node current, Node goal)
    {
        double x = Math.abs(current.getCoord().getRow() - goal.getCoord().getRow());
        double y = Math.abs(current.getCoord().getCol() - goal.getCoord().getCol());
        return  2 * (x + y) + (Math.sqrt(2)  - 2 ) * Math.min(x,y );
    }
    public double Chebyshev(Node current, Node goal)
    {
        return Math.max(Math.abs(current.getCoord().getRow() - goal.getCoord().getRow()), Math.abs(current.getCoord().getCol() - goal.getCoord().getCol()));

    }

    public double Tie_Cross(Node current, Node goal)
    {
				/*double x1 = current.getCoord().getRow() - goal.getCoord().getRow();
				double y1 = current.getCoord().getCol() - goal.getCoord().getCol();
				double x2 = current.getCoord().getRow() - goal.getCoord().getRow();
				double y2 =  current.getCoord().getCol() - goal.getCoord().getCol();
				double evaluate = Math.abs(x1*y2 - x2*y1);
				//evaluate += (evaluate * 0.0001);
				*/
        double heuristic = Math.sqrt(2) * Math.min((Math.abs(start.getCoord().getRow() - goal.getCoord().getRow())), (Math.abs(current.getCoord().getCol()- goal.getCoord().getCol())));// - min(abs(startx - goalx), abs(starty - goaly))
        return heuristic *= (1.0 + (0.25/160));//return evaluate;
    }


    @Override
    public void findPath() {
        // TODO Auto-generated method stub

    }


}


