package book.store.model;

public class BookV {
	
	 private Long bId;
	    
	    private String bName;
	   
	    private String bDesc;
	
	    private int bPrice;
	 
	    private int bDiscount;

	    private int bQuantity;

		public Long getbId() {
			return bId;
		}

		public void setbId(Long bId) {
			this.bId = bId;
		}

		public String getbName() {
			return bName;
		}

		public void setbName(String bName) {
			this.bName = bName;
		}

		public String getbDesc() {
			return bDesc;
		}

		public void setbDesc(String bDesc) {
			this.bDesc = bDesc;
		}

		public int getbPrice() {
			return bPrice;
		}

		public void setbPrice(int bPrice) {
			this.bPrice = bPrice;
		}

		public int getbDiscount() {
			return bDiscount;
		}

		public void setbDiscount(int bDiscount) {
			this.bDiscount = bDiscount;
		}

		public int getbQuantity() {
			return bQuantity;
		}

		public void setbQuantity(int bQuantity) {
			this.bQuantity = bQuantity;
		}
	    
}
