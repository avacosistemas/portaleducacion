import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { catchError, map, tap } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';

import { Injector } from '@angular/core';
import { environment } from 'environments/environment';
import { Role } from '../model/role';
import { CrudService } from 'app/modules/fwk/core/service/crud-service/crud.service';
import { CRUD } from 'app/modules/fwk/core/service/crud-service/crud';


@Injectable()
export class RoleService extends CrudService<Role> implements CRUD<Role> {

  constructor(protected injector: Injector) {
      super(environment.ROLE_CRUD_URL, injector);
  }

  getFirst(): Observable<any>{
    const observable = new Observable(o => {
      this.findAll().subscribe(r => {
        if (r){
          o.next(r[0]);
          o.complete();
        }
      });
    });
    return observable;
  }
}
