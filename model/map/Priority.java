package model.map;

public enum Priority {

	INTERNATIONAL_AIR{
		public String toString(){
			return "International Air";
		}
	},
	INTERNATIONAL_STANDARD{
		public String toString(){
			return "International Standard";
		}
	},
	DOMESTIC_AIR{
		public String toString(){
			return "Domestic Air";
		}
	},
	DOMESTIC_STANDARD{
		public String toString(){
			return "Domestic Standard";
		}
	};
	
}
