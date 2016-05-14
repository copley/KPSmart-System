package model.map;

public class DijkstraNode {
	int siteID;
	int previousSiteID;//-1 if there is no previous site!
	Route previousRoute;//null if there is no previous site!
	double durationFromStart;

	/** 
	 * @param siteID - id of current site represented by this node
	 * @param prevSiteID - id of site from which this node was reached (-1 if this is the start node)
	 * @param route - Route used to get from previous site
	 * @param durationFromStart - total duration to get from start site to this site during the Dijkstra search
	 */
	 public DijkstraNode(int siteID, int prevSiteID, Route prevRoute, double durationFromStart) {
		this.siteID = siteID;
		this.previousSiteID = prevSiteID;
		this.durationFromStart = durationFromStart;
		this.previousRoute = prevRoute;
	}

	public int getSiteID() {
		return siteID;
	}

	public int getPrevSiteID() {
		return previousSiteID;
	}

	public double getDurationFromStart() {
		return durationFromStart;
	}

	public Route getPrevRoute() {
		return previousRoute;
	}

}
