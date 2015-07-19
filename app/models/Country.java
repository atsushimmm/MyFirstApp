package models;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.EmbeddedId;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import play.db.jpa.JPA;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table ( name = "countries" )
public class Country extends play.db.jpa.GenericModel {
        @Id
	@Column(name="idx")
	public String idx;
	@Column (name="country")
	public String country;

	@Column (name="country_name")
	public String country_name; 

	@OneToMany(mappedBy="_country")
	public List<City> cities;
}
