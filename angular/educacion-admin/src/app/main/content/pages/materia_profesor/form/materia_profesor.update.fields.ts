import { PREFIX_DOMAIN_API } from "environments/environment";

export const MATERIA_PROFESOR_UPDATE_FORM_FIELDS_DEF = [
  
  {
     key: 'idProfesor',
     labelKey: 'materia_profesor_update_form_fields_def_field_idprodfesor',
     label: 'idProfesor',
     type: 'hidden',
     mappingQuerystring: true,
     controlType: 'hidden'
   },
 
 /*
  {
     key: 'nivel',
     labelKey: 'materia_profesor_update_form_fields_def_field_nivel',
     controlType: 'autocomplete',
     label: 'Nivel',
     required: true,
     options: {
       transferIdToField: 'idNivel',
       elementLabel: 'descripcion',
       elementValue: 'id',
       useNativeFilter: false,
       selectElementOrCleanField: 'Debe seleccionar un elemento o limpiar el campo'
     },
     apiOptions: {
       queryString: {
         descripcion: 'nivel'
       },
       url: PREFIX_DOMAIN_API + 'ws-rest-educacion/niveles/'
     }
   },
   { 
     key: 'idNivel',   
     controlType: 'hidden'
   },
 */
   {
     key: 'materia',
     labelKey: 'materia_profesor_update_form_fields_def_field_materia',
     controlType: 'autocomplete',
     label: 'Materia',
     required: true,
     options: {
       transferIdToField: 'idMateria',
       elementLabel: 'descripcionNivel',
       elementValue: 'id',
       useNativeFilter: false,
       selectElementOrCleanField: 'Debe seleccionar un elemento o limpiar el campo'
     },
     apiOptions: {
       queryString: {
         descripcion: 'materia'
       },
       url: PREFIX_DOMAIN_API + 'ws-rest-educacion/materias/'
     }
   },
   { 
     key: 'idMateria',   
     controlType: 'hidden'
   }
 ];