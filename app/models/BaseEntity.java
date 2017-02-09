package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

@MappedSuperclass
public abstract class BaseEntity extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@CreatedTimestamp
	@Column(updatable=false)
	public Timestamp createdOn;

	@Version
	public Timestamp lastUpdate;
}
