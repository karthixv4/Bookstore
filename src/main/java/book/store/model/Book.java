package book.store.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "book_details")
public class Book {

	    @Id
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	    private Long bId;
	    @Column(nullable = false,name = "book_name", unique = true, length = 45)
	    private String bName;
	    @Column(nullable = false,name = "book_desc", length = 200)
	    private String bDesc;
	    @Column(nullable = false,name = "book_price",length = 1000)
	    private int bPrice;
	    @Column(nullable = false,name = "book_disc", length = 45)
	    private int bDiscount;
	    @Column(name = "book_quantity", length = 45)
	    private int bQuantity;
	    @Column(name = "file_name")
		private String fileName;
		
		@Column(name = "file_path")
		private String filePath;
		
		@Column(name = "file_type")
		private String fileType;
		
	    public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}


		public String getFilePath() {
			return filePath;
		}


		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}


		public String getFileType() {
			return fileType;
		}


		public void setFileType(String fileType) {
			this.fileType = fileType;
		}





		@ManyToOne
	    private Genre genre;

	   
	  
	   
	


		public Genre getGenre() {
			return genre;
		}


		public void setGenre(Genre genre) {
			this.genre = genre;
		}


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


		


		//calculate price after discount
	    public int getPriceAfterApplyingDiscount() {
	        int d = (int) ((this.getbDiscount() / 100.0) * this.getbPrice());
	        return this.getbPrice() - d;
	    }

	}


