package model.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraSearchWithPriority {
	private final int startSiteID;
	private final int finishSiteID;
	private final SiteMap siteMap;
	private final model.map.Priority priority;

	public DijkstraSearchWithPriority(int startSiteID, int finishSiteID, SiteMap siteMap, model.map.Priority priority) {
		this.startSiteID = startSiteID;
		this.finishSiteID = finishSiteID;
		this.siteMap = siteMap;
		this.priority = priority;
	}

	public List<Integer> findShortestRoute() {
		Queue<DijkstraNode> fringe = new PriorityQueue<DijkstraNode>();
		Map<Integer, DijkstraNode> visitedNodes = new HashMap<Integer, DijkstraNode>();
		// allows access to _visited_ nodes via their site ID

		DijkstraNode startNode = new DijkstraNode(startSiteID, -1, -1, 0);
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
				String currentSite = siteMap.getSiteNamefromID(currentSiteID);
				visitedNodes.put(currentSiteID, currentNode);
				if (currentSiteID == finishSiteID) {
					// found the shortest path yay!
					break;// break out of the while loop...
				}

				List<Route> routesOn = siteMap.getRoutesOn(currentNode.getSiteID());
				if (routesOn != null) {
					for (Route route : routesOn) {
						// if the site is not the origin of the route, do not
						// consider
						if (!route.getOrigin().equals(currentSite)) {
							continue;
						}
						// if the route is not in service, do not consider
						if (!route.isInService()) {
							continue;
						}
						// if the priority is STANDARD, then AIR mode routes cannot be
						// used
						if (((this.priority == model.map.Priority.DOMESTIC_STANDARD)
								|| (this.priority == model.map.Priority.INTERNATIONAL_STANDARD))
								&& route.getType() == Type.AIR) {
							continue;
						}
						// work out what the next site is along this route
						int nextSiteID = siteMap.getSiteIDfromLocation(route.getDestination());
						// check if next site has already been visited - do not
						// add
						// to the priority queue if it has!
						if (!visitedNodes.containsKey(nextSiteID)) {
							double costToNext = route.getDuration();
							double totalCostToNext = currentNode.getDurationFromStart() + costToNext;
							fringe.offer(new DijkstraNode(nextSiteID, currentNode.getSiteID(), route.getID(),
									totalCostToNext));
						}

					}
				}
			}
		}


		// at this point the search has finished...
		// might not have found _any_ path between the points because they are
		// in disconnected parts,
		// so better double check...
		if (!visitedNodes.containsKey(finishSiteID)) {
			return null;
		}
		// otherwise, work backwards from the final node to build up the set of
		// routes on the shortest path
		LinkedList<Integer> ans = new LinkedList<Integer>();

		DijkstraNode pathNode = visitedNodes.get(finishSiteID);
		while (pathNode != null) {
			int preceedingRouteID = pathNode.getPrevRouteID();
			if (preceedingRouteID == -1) {
				pathNode = null;
			} else {
				ans.addFirst(preceedingRouteID);
				pathNode = visitedNodes.get(pathNode.getPrevSiteID());
			}
		}
		return ans;
	}

}
