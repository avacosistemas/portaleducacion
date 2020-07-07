import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { catchError, map, tap } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';

import { Injector } from '@angular/core';

import { environment } from 'environments/environment';
import { User } from 'app/modules/fwk/core/model/user';
import { CRUD } from 'app/modules/fwk/core/service/crud-service/crud';
import { CrudService } from 'app/modules/fwk/core/service/crud-service/crud.service';

@Injectable()
export class UserService extends CrudService<User> implements CRUD<User> {

  constructor(protected injector: Injector) {
      super(environment.USER_CRUD_URL, injector);
  }
}
