package model.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraSearchWithPriority {
	private Site start;
	private Site finish;
	private final Map<Site, List<Route>> siteToRoutes;
	private int priority;

	public DijkstraSearchWithPriority(Site start, Site finish, Map<Site, List<Route>> siteToRoutes, int priority) {
		this.start = start;
		this.finish = finish;
		this.siteToRoutes = siteToRoutes;
		this.priority = priority;
	}

	public List<Route> findShortestRoute() {
		Queue<DijkstraNode> fringe = new PriorityQueue<DijkstraNode>();
		Map<Integer, DijkstraNode> visitedNodes = new HashMap<Integer, DijkstraNode>();
		// allows access to _visited_ nodes via their site ID

		int startID = start.getID();
		DijkstraNode startNode = new DijkstraNode(startID, -1, null, 0);
		fringe.offer(startNode);

		while (!fringe.isEmpty()) {
			// get the node with the smallest distance so far
			DijkstraNode currentNode = fringe.poll();// pops out the lowest
														// ranked according to
														// compareTo...
			// if it has already been visited, do nothing,
			// otherwise add all the sites that it is related to onto the stack
			if (!visitedNodes.containsKey(currentNode.getSiteID())) {
				int currentSiteID = currentNode.getSiteID();
				visitedNodes.put(currentSiteID, currentNode);
				if (currentSiteID == finish.getID()) {
					// found the shortest path yay!
					break;// break out of the while loop...
				}

				List<Route> routesOut = siteToRoutes.get(currentNode.getSiteID());
				for (Route route : routesOut) {
					//if the route is not in service, do not consider
					if(!route.isInService()){
						break;
					}
					//if the priority is 2, then AIR mode routes cannot be used
					if(this.priority == 2 && route.getType() == Type.AIR){
						break;
					}
					// work out what the next site is along this route
					int nextSiteID = route.getDestination();
					// check if next site has already been visited - do not add
					// to the priority queue if it has!
					if (!visitedNodes.containsKey(nextSiteID)) {
						double costToNext = route.getDuration();
						double totalCostToNext = currentNode.getDurationFromStart() + costToNext;
						fringe.offer(new DijkstraNode(nextSiteID, currentNode.getSiteID(), route, totalCostToNext));
					}

				}
			}
		}

		// at this point the search has finished...
		// might not have found _any_ path between the points because they are
		// in disconnected parts,
		// so better double check...
		if (!visitedNodes.containsKey(finish.getID())) {
			return null;
		}
		// otherwise, work backwards from the final node to build up the set of
		// routes on the shortest path
		LinkedList<Route> ans = new LinkedList<Route>();

		DijkstraNode pathNode = visitedNodes.get(finish.getID());
		while (pathNode != null) {
			Route preceedingRoute = pathNode.getPrevRoute();
			if (preceedingRoute == null) {
				pathNode = null;
			} else {
				ans.addFirst(preceedingRoute);
				pathNode = visitedNodes.get(pathNode.getPrevSiteID());
			}
		}

		return ans;
	}


}
