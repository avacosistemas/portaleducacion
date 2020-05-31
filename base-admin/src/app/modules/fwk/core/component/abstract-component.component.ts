import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { FormGroup } from '@angular/forms';
import { OnDestroy, AfterViewInit } from '@angular/core/src/metadata/lifecycle_hooks';
import { Subscription } from 'rxjs/Subscription';
import { Injector } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { ErrorHandler } from '@angular/core/src/error_handler';
import { I18nService } from '../service/i18n-service/i18n.service';

import { I18n } from '../model/i18n';
import { NotificationService } from '../service/notification/notification.service';
import { i18ns } from '../../memory/i18ns';
import { CRUDS } from '../service/crud-def-service/crud-def.service';
import { FuseConfigService } from '@fuse/services/config.service';
import { Router } from '@angular/router';
import { ComponentDefService } from '../service/component-def-service/component-def.service';


export abstract class AbstractComponent implements OnInit{
  i18nService: I18nService;
  configService: FuseConfigService;
  onConfigChanged: Subscription;
  fuseSettings: any;
  i18n: I18n;
  i18nName: string;
  i18nLoaded: boolean;
  notificationService: NotificationService;
  router: Router;
  name: string;
  componentDefService: ComponentDefService;
  componentDef: any;
  constructor(injector: Injector) {
    this.i18nService = injector.get(I18nService);
    this.configService = injector.get(FuseConfigService);
    this.notificationService = injector.get(NotificationService);
    this.componentDefService = injector.get(ComponentDefService);
    this.router = injector.get(Router);
    this.i18nLoaded = false;
  }

  // Deprecated
  getI18nName(): string {
    return '';
  }

  getName(){
    return this.name;
  }
  
  setUpI18n(i18n) {
    this.i18nService.addI18n(i18n);
  }

  // Deprecated
  onInit() {}

  // Deprecado
  setUpCrudDef(crudDef) {
    CRUDS.push(crudDef);
  }

  setUpComponentDef(componentDef){
    this.componentDef = componentDef;
    this.name = componentDef.name;
    this.componentDefService.create(componentDef);
  }

  customFuseConfig() {
    return undefined;
  }

  ngOnInit() {
    this.onConfigChanged =
    this.configService.onConfigChanged.subscribe(
        (newSettings) => {
            this.fuseSettings = newSettings;
        }
    );
    this.i18nService.getByName(this.getI18nName()).subscribe(
      i18n => {
                this.i18n = i18n;
                this.i18nLoaded = true;
                console.log(`init i18n -> ${this.getI18nName()}`);
                console.log(this.i18n);
              },
      e => {}
    );
    this.onInit();
  }

  translate(key: string) {
    return this.i18n.translate(key);
  }

  navigateWithInjector(url: string, param, injector: Injector){
    const rt = injector.get(Router);
    rt.navigateByUrl(this.buildURL(url, param));
  }

  navigate(url: string, param){
    this.router.navigateByUrl(this.buildURL(url, param));
  }

  private buildURL(url: string, param){
    if (param){
      Object.getOwnPropertyNames(param).forEach((val, idx) => {
        if (url.includes(':' + val)){
          url = url.replace(':' + val, param[val]);
        }
      });
    }
    return '/' + url;
  }
}
