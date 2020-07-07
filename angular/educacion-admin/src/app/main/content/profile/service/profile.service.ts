import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { catchError, map, tap } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';

import { Injector } from '@angular/core';
import { environment } from 'environments/environment';

import { Profile } from '../model/profile';
import { CrudService } from 'app/modules/fwk/core/service/crud-service/crud.service';
import { CRUD } from 'app/modules/fwk/core/service/crud-service/crud';

@Injectable()
export class ProfileService extends CrudService<Profile> implements CRUD<Profile> {

  constructor(protected injector: Injector) {
      super(environment.PROFILE_CRUD_URL, injector);
  }
}
