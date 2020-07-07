import { REGEX_KEY_LETTER_NUMBERS_DASH_UNDERCODE_WITH_FIRST_LETTER } from "app/modules/fwk/constants";



export const USER_SEARCH_FIELDS = [{               
  key: 'username',
  labelKey: 'Nombre de Usuario',
  controlType: 'textbox',
  maxLength: 50,
  validation: {
    regexKey: REGEX_KEY_LETTER_NUMBERS_DASH_UNDERCODE_WITH_FIRST_LETTER
  }
},
{
  key: 'name',
  labelKey: 'Nombre',
  controlType: 'textbox',
  maxLength: 100
},
{
  key: 'lastname',
  labelKey: 'Apellido',
  controlType: 'textbox',
  maxLength: 100
},
{
  key: 'email',
  labelKey: 'Email',
  controlType: 'email',
  maxLength: 100,
  options: {
    ignoreNatValidation: true
  }
}
];

