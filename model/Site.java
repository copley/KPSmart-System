package model;

import java.util.List;

public class Site {
	private int id;
	private String location;
	private SiteMap siteMap;

	public Site(int id, String location, SiteMap siteMap) {
		this.id = id;
		this.location = location;
		this.siteMap = siteMap;
	}

}
