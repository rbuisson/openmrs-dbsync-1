package org.openmrs.eip.app.db.sync.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.openmrs.eip.app.db.sync.entity.light.EncounterLight;
import org.openmrs.eip.app.db.sync.entity.light.OrderSetLight;
import org.openmrs.eip.app.db.sync.entity.light.PatientLight;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "order_group")
@AttributeOverride(name = "id", column = @Column(name = "order_group_id"))
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderGroup extends BaseChangeableDataEntity {
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private PatientLight patient;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "encounter_id")
	private EncounterLight encounter;
	
	@ManyToOne
	@JoinColumn(name = "order_set_id")
	private OrderSetLight orderSet;
	
}
