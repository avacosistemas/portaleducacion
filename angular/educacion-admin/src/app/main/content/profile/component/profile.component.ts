import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { FuseConfigService } from '@fuse/services/config.service';
import { Injector } from '@angular/core';
import { of } from 'rxjs/observable/of';
import { Observable } from 'rxjs/Observable';
import { ProfileService } from '../service/profile.service';
import { RoleService } from '../../role/service/role.service';
import { PermissionService } from '../../permission/service/permission.service';
import { PROFILE_SEARCH_FIELDS } from '../form/profile.search.fields';
import { environment } from 'environments/environment';
import { AbstractComponent } from 'app/modules/fwk/core/component/abstract-component.component';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProfileComponent extends AbstractComponent implements OnInit{
  
  constructor(public service: ProfileService,
              injector: Injector) {
    super(injector);
    const roleService: RoleService = injector.get(RoleService);
    const roles = roleService.findAll();
    let rol;
    if (roles){
      rol = roles[0];
    }

  }
  
  getCrudName(){
    return 'profile';
  }

  getI18nName(): string {
    return 'profile';
  }

  handlerFieldSourceData(keyComponent: string, entity: any, injector: Injector){
    // if (PERMISSIONS_COMPONENT_KEY === keyComponent){
    //   console.log('Handler field source data -> ' + keyComponent);
    //   const permissionService: PermissionService = injector.get(PermissionService);
    //   return permissionService.findAll();
      /*else{

        const obs = new Observable<any>(observer => {
          permissionService.findAll().subscribe(r => {
            entity[keyComponent].forEach(elEntity => {
              r = r.filter(e => e.id !== elEntity.id);
            });
            observer.next(r);
          });
        });
        return obs;
      }*/
      
    // }else if (ROLE_COMPONENT_KEY === keyComponent){
    //   console.log('Handler field source data -> ' + keyComponent);
    //   const roleService: RoleService = injector.get(RoleService);
    //   return  roleService.getFirst();
    // }
    return of();
  }

  onInit() {}
}
