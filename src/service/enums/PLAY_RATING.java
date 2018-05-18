package service.enums;

public enum PLAY_RATING {
	
	//演出级别
		CHILD(1,"儿童"),//儿童可观看
		TEENAGE(2,"青少年"),//青少年可观看
		ADULT(3,"成人"); //成人可观看
		
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		private int index;
		private PLAY_RATING(int index , String name) {
			this.index = index;
			this.name = name;
		}
		@Override
		public  String toString() {
			return name;
		}
}
