package models;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import play.db.jpa.JPA;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table ( name = "cities" )
public class City extends play.db.jpa.GenericModel {
        @Id
	@Column (name="idx")
	public String idx;

	@Column (name="city")
	public String city_name; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country")
	private Country _country;
}
