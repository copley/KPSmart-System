package model.map;

public class DijkstraNode implements Comparable<DijkstraNode>{
	int siteID;
	int previousSiteID;//-1 if there is no previous site!
	int previousRouteID;//-1 if no previous site!
	double durationFromStart;

	/**
	 * @param siteID - id of current site represented by this node
	 * @param prevSiteID - id of site from which this node was reached (-1 if this is the start node)
	 * @param route - Route used to get from previous site
	 * @param durationFromStart - total duration to get from start site to this site during the Dijkstra search
	 */
	 public DijkstraNode(int siteID, int prevSiteID, int prevRouteID, double durationFromStart) {
		this.siteID = siteID;
		this.previousSiteID = prevSiteID;
		this.durationFromStart = durationFromStart;
		this.previousRouteID = prevRouteID;
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

	public int getPrevRouteID() {
		return previousRouteID;
	}
	@Override
	public int compareTo(DijkstraNode other) {
		double diffETC = this.durationFromStart - other.getDurationFromStart();
		if (diffETC >0){return 1;}
		if (diffETC <0){return -1;}
		return 0;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("siteID: " + siteID + "\n");
		string.append("previousSiteID: " + previousSiteID + "\n");
		string.append("durationFromStart: " + durationFromStart + "\n");
		string.append("previousRouteID: " + previousRouteID + "\n");
		return string.toString();
	}
}
