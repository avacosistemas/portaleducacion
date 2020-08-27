/**
 * 
 */
package ar.com.avaco.codegenerator.dto;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import ar.com.avaco.commons.domain.EntityDef;

/**
 * @author Claudio
 *
 */
public class RelationEntity {
	
	private EntityDef entityDef;
	private RelationType type;
	private List<EntityDef> relations;
	
	public RelationEntity(RelationType type, EntityDef entityDef) {
		this.relations = new ArrayList<>();
		this.type = type;
		this.entityDef = entityDef;
	}
	
	public RelationType getType() {
		return type;
	}

	public void setType(RelationType type) {
		this.type = type;
	}
	
	public EntityDef getEntityDef() {
		return entityDef;
	}

	public void setEntityDef(EntityDef entityDef) {
		this.entityDef = entityDef;
	}

	public List<EntityDef> getRelations() {
		return relations;
	}
	
	public void setRelations(List<EntityDef> relations) {
		this.relations = relations;
	}
	
	public void addRelation(EntityDef entityDef) {
		EntityDef finded = this.relations.stream().filter(r -> {
			return r.getKey().toUpperCase().equals(entityDef.getKey().toUpperCase());
		}).findFirst().orElse(null);
		if(finded == null) {
			this.relations.add(entityDef);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(); 
		for (EntityDef entityDef : relations) {
			String relationString = MessageFormat.format("[{0}]{1}[{2}],", 
								 this.entityDef.getKey(), 
								 this.getRelationType(this.getType()), 
								 entityDef.getKey());
			builder.append(relationString);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	private String getRelationType(RelationType type) {
		switch (type) {
		case MANY_TO_MANY: return "1..*-1..*";
		case MANY_TO_ONE: return "1..*-1";
		case ONE_TO_MANY: return "1-1..*";
		case ONE_TO_ONE: return "1-1";
		case SIMPLE_ASSOCIATION: return "->";
		default:
			return "->";
		}
	}

}
