/**
 * 
 */
package ar.com.avaco.codegenerator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.com.avaco.codegenerator.dto.RelationEntity;
import ar.com.avaco.codegenerator.dto.RelationType;
import ar.com.avaco.commons.domain.EntityDef;
import ar.com.avaco.commons.domain.FieldDef;

/**
 * @author Claudio
 *
 */
public class UmlByEntityDefService {
	public String umlByEntitiesDef(List<EntityDef> entitiesDef) {
		List<RelationEntity> relations = new ArrayList<>();		
		StringBuilder builder = new StringBuilder();
		for (EntityDef entityDef : entitiesDef) {
			String rootKey = entityDef.getKey();
			String key = entityDef.getKey().toUpperCase();
			builder.append("[");
			builder.append(rootKey);
			builder.append("],");
			if(entityDef.getFields() != null) {
			List<FieldDef> objsFields = entityDef.getFields();
			objsFields.forEach(f -> {
				EntityDef entityFinded = entitiesDef.stream()
							.filter(e -> e.getKey().toUpperCase().equals(f.getType().toUpperCase()))
							 .findFirst().orElse(null);
				if(entityFinded == null) {
					System.out.println("No se encontro la entidad asociada al campo objeto " + f.getKey() + " de la entidad " + rootKey);
				}else {
					String relation = f.getRelation();
					if(relation == null) {
						relation = RelationType.ONE_TO_ONE.name();
					}else {
						relation = relation.toUpperCase();
					}
					final String relationFinal = relation;
					RelationEntity relationEntity = relations.stream().filter(r -> {
						return r.getEntityDef().getKey().toUpperCase().equals(key) &&
								relationFinal.equals(r.getType().name());
					}).findFirst().orElse(null);
					if (relationEntity== null) {						
					    relationEntity = new RelationEntity(RelationType.valueOf(relation.toUpperCase()), entityDef);
						relationEntity.addRelation(entityFinded);
					    relations.add(relationEntity);
					}else {
						relationEntity.addRelation(entityFinded);
					}
				}
			});
			}
		}
		
		for (RelationEntity relationEntity : relations) {
			builder.append(relationEntity.toString());
			builder.append(",");
		}

		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	public static void main(String[] args) {
		List<EntityDef> ets = new ArrayList<>();
		ets.add(getEntityDef("ClaseA", RelationType.MANY_TO_MANY, "ClaseB"));
		ets.add(getEntityDef("ClaseB", RelationType.MANY_TO_MANY, "ClaseC"));
		ets.add(getEntityDef("ClaseC", null, "ClaseX"));
		UmlByEntityDefService service = new UmlByEntityDefService();
		System.out.println(service.umlByEntitiesDef(ets));
	}

	private static EntityDef getEntityDef(String entityName, RelationType type, String fieldName) {
		EntityDef entityDef = new EntityDef();
		entityDef.setKey(entityName);
		FieldDef fieldDef = new FieldDef();
		fieldDef.setType(FieldDef.TYPE_OBJECT);
		fieldDef.setKey(fieldName);
		if (type != null) {
			fieldDef.setRelation(type.name());
		}
		entityDef.setFields(Arrays.asList(fieldDef));
		return entityDef;
	}
}
