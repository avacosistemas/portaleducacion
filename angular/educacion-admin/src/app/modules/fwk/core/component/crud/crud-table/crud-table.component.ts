import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog, MatSort } from '@angular/material';
import { Observable } from 'rxjs/Observable';
import { Subscriber } from 'rxjs/Subscriber';
import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

import { Injector } from '@angular/core';
import { AbstractComponent } from '../../abstract-component.component';
import { TableDef } from '../../../model/table-def';
import { CrudModalComponent } from '../crud-modal/crud-modal.component';
import { GenericHttpService } from '../../../service/generic-http-service/generic-http.service';
import { BasicModalComponent } from '../basic-modal/basic-modal.component';
import { LocalStorageService } from '../../../service/local-storage/local-storage.service';
import { SpinnerService } from '../../../module/spinner/service/spinner.service';
import { ACTION_TYPES } from '../../../model/component-def/action-def';
import { HTTP_METHODS } from '../../../model/ws-def';
import { FileService } from '../../../service/file/file.service';
import { CrudDef } from '../../../model/component-def/crud-def';
import { FormDef } from '../../../model/form-def';
import { fuseAnimations } from '@fuse/animations';
import { DialogService } from '../../../service/dialog-service/dialog.service';
import { GridDef } from '../../../model/component-def/grid-def';
import { ExpressionService } from '../../../service/expression-service/expression.service';
import { FormGridModalComponent } from '../../form-grid-dialog/form-grid.dialog.component';
import { ActionDefService } from '../../../service/action-def-service/action-def.service';
import { DisplayCondition } from '../../../model/component-def/display-condition';
import { DynamicFieldConditionIf } from '../../../model/dynamic-form/dynamic-field-condition-if';
import { Params } from '@angular/router';
import { PARAMETERS } from '@angular/core/src/util/decorators';

const ACTION_COLUMN = '_action';
const DELETE_COLUMN = 'delete';
@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-crud-table',
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.scss'],
  animations: fuseAnimations
})
export class CrudTableComponent extends AbstractComponent implements OnInit {
  resetSelects: any;

  @Input() crud: any;
  statustable: StatusTable<any>;
  @Input() editForm: any;
  @Input() _datasource: MatTableDataSource<any>;
  @Input() grid: GridDef;
  @Input() onClickRow: any;
  @Input() urlDelete: any;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @Output() status =  new EventEmitter(true);
  genericHttpService: GenericHttpService;
  selectedRowIndex: number;
  _selects: boolean;
  rows: any[];
  _existElementsToSelect: boolean;
  private localStorageService: any;
  private spinnerService: SpinnerService;
  private spinnerGeneralControl: any;
  private fileService: FileService;
  private columnDefId: string;
  private dialogService: DialogService;
  private expressionService: ExpressionService;
  private actionDefService: ActionDefService;
  initOk: boolean;
  @Input() selectable: boolean;
  constructor(private  dialog: MatDialog, public injector: Injector) {
    super(injector);
    this.setUpI18n({
      name: 'crud_table',
      lang: 'es',
      dictionary: {
        itemsPerPageLabel: 'Items por página',
        boolean_true: 'Si',
        boolean_false: 'No',
        action_delete: 'Eliminar',
        grid_action_button_delete: 'Eliminar'
      }
    });
    this.statustable = new StatusTable<any>();
    this.genericHttpService = injector.get(GenericHttpService);
    this.localStorageService = injector.get(LocalStorageService);
    this.spinnerService = injector.get(SpinnerService);
    this.fileService = injector.get(FileService);
    this.dialogService = injector.get(DialogService);
    this.expressionService = injector.get(ExpressionService);
    this.actionDefService = injector.get(ActionDefService);
  }

  onInit() {
    this.selectable = this.selectable ? true : false;
    this.spinnerGeneralControl = this.spinnerService.getControlGlobalSpinner();
    this.selects = false;
    this._existElementsToSelect = false;
    this.statustable = new StatusTable<any>();
    this.paginator._intl.itemsPerPageLabel = this.translate('itemsPerPageLabel');
    

    if (this.hasActions()){
      const existActions = this.grid.displayedColumns.find(c => c === ACTION_COLUMN);
      if (existActions === undefined || !existActions){
        const displayedColumns = [ACTION_COLUMN];
        this.grid.displayedColumns.forEach(column => {
          displayedColumns.push(column);
        });
        this.grid.displayedColumns = displayedColumns;
      }
      
    }

    if (this.hasGeneralActions()){
      const existDelete = this.grid.displayedColumns.find(c => c === DELETE_COLUMN);
      if (existDelete === undefined){
        const displayedColumns = [DELETE_COLUMN];
        this.grid.displayedColumns.forEach(column => {
          displayedColumns.push(column);
        });
        this.grid.displayedColumns = displayedColumns;
      }
      if (this.grid.deleteAction || this.grid.deleteColumn){
        let actions: any = [{
          actionNameKey: 'grid_action_button_delete',
          actionName: this.translate('grid_action_button_delete'),
          confirm: true,
          icon: 'delete',
          ws: {
            url: this.urlDelete,
            method: 'DELETE'
          }
        }];
        if (this.grid.actions){
          actions = actions.concat(this.grid.actions);
        }
        this.grid.actions = actions;
      }
    }

    const columnDefId = this.grid.columnsDef.find(c => c.id);
    if (columnDefId){
      this.columnDefId = columnDefId.columnDef;
    }
    this.initOk = true;
  }
  
  hasActions(){
    return this.grid.actions || this.grid.deleteAction || this.grid.deleteColumn ? true : false;
  }
  getGeneralActionsColumnName(){
    return DELETE_COLUMN;
  }
  getActionsColumnName(){
    return ACTION_COLUMN; 
  }

  hasGeneralActions(){
    if (this.selectable){
      return true;
    }else if (this.grid){
      const hasDeleteAction = this.grid.deleteAction || 
                                this.grid.deleteColumn;
      return hasDeleteAction;
    }
    return false;
  }
  getActionsByElement(element){
    return this.actionDefService.getActions(this.grid.displayedActionsCondition, this.grid.actions, element);
  }
  submitAction(action, entity){
    if (this.columnDefId){
      entity.id = entity[this.columnDefId];
    }
    if (action.gridModal){
      this.dialogService.showGridModal(action.actionName, entity[action.gridModal.fromArrayField], action.gridModal.gridDef);
    }else if (action.confirm){
        this.actionDefService.submitAction(action, entity, this.crud.i18nCurrentCrudComponent, undefined).subscribe(r => {
            this.crud.findAll(); 
            this.spinnerGeneralControl.hide();
            this.notificationService.notifySuccess(this.crud.translate('success_message'));
      });
    }else if (action.form){
      action.submitButtonKey = 'Guardar';
      const data = { 
                     entity: entity,
                     config: action,
                     fields: action.form,
                     i18n: this.crud.i18nCurrentCrudComponent,
                  };
      const dialogRef = this.dialog.open(BasicModalComponent, {
        width:  this.crud.crudDef.dialogConfig &&
        this.crud.crudDef.dialogConfig.width ?
        this.crud.crudDef.dialogConfig.width : '320px',
        panelClass: 'control-mat-dialog',
        data: data
      });

      dialogRef.afterClosed().subscribe(result => {
        this.crud.findAll();
      });
    } else if (action.formDef){
      action.submitButtonKey = 'Guardar';
      const data = { 
        entity: entity,
        config: action,
        formDef: action.formDef,
        fields: action.formDef.fields,
        i18n: this.crud.i18nCurrentCrudComponent,
     };
      const dialogRef = this.dialog.open(BasicModalComponent, {
      width:  this.crud.crudDef.dialogConfig &&
      this.crud.crudDef.dialogConfig.width ?
      this.crud.crudDef.dialogConfig.width : '320px',
      panelClass: 'control-mat-dialog',
      data: data
      });

      dialogRef.afterClosed().subscribe(result => {
      this.crud.findAll();
      });
    }
    else{
      if (ACTION_TYPES.file_download === action.actionType){
          this.spinnerGeneralControl.show();
          this.fileService.downloadFileByAction(action, entity).subscribe(r => {
            this.crud.findAll();
            this.spinnerGeneralControl.hide();
          }, e => {

          }, () => {
            this.spinnerGeneralControl.hide();
          });
      } else if (ACTION_TYPES.redirect === action.actionType){
        var url: String = action.redirect.url;
                
        const queryParams: Params = this.getQueryParams(action.redirect.querystring, entity);
        
        if (queryParams['externalUrl']) {
          url = queryParams['externalUrl'];
          if (!url.startsWith('http://') &&  !url.startsWith('https://')) {
            url = 'http://' + url;
          }
        }

        if (action.redirect.openTab) {
          
          var queryParamsString = "";

          if (queryParams && queryParams != undefined) {

            if (action.redirect.idUrl != undefined && action.redirect.idUrl && queryParams['id'] != undefined){
              queryParamsString = "/" + queryParams['id'];
            } else {

              var first = true;
              
              Object.getOwnPropertyNames(queryParams).forEach(param => { 
                if (param != 'externalUrl') {
                  if (!first) {
                    queryParamsString = queryParamsString + "&";
                  } else {
                    queryParamsString = queryParamsString + "?";
                    first = false;
                  }
                  queryParamsString = queryParamsString + param + "=" + queryParams[param]
                }
              });
            }
          
          }

          var win = window.open(url + queryParamsString, '_blank');
          win.focus();

        } else {
          this.router.navigate([url], { queryParams });
        }
      }else{
          this.spinnerGeneralControl.show();
          this.genericHttpService.callWs(action.ws, entity).subscribe(r => {
            this.crud.findAll(); 
            this.notificationService.notifySuccess(this.crud.translate('success_message'));
          }, e => {}, () => {
            this.spinnerGeneralControl.hide();
          });
      }
    }
  }
  getQueryParams(querystring: any, entity: any): Params {
    const queryParams: Params = {};
    Object.keys(querystring).forEach( key => {
      queryParams[key] = entity[querystring[key]];
    });
    return queryParams;
  }

  onClickRowEvent(row) {
    if (this.crud === undefined){
      return;
    }
    const url = this.crud.crudDef.ws.url;
    let id  = this.grid.columnsDef.find(c => c.id);
    id = id ? id.columnDef : 'id';
    this.selectedRowIndex = row [id];
    if (this.crud.crudDef.forceGetDetail){
      this.genericHttpService.basicGet(url, {id: row[id]}, undefined, {id: 'id'}).subscribe(r => {
        if (r){
          this.processObj(r);
        }
      });
    }else{
      this.processObj(row);
    }
    
  }
  private processObj(obj){
    if (this.onClickRow) {
      this.onClickRow(obj, this.injector);
    } else if (this.crud) {
      let data;
      const formUpdate = this.getFormUpdate(this.crud.crudDef);
      const formRead = this.getFormRead(this.crud.crudDef); 
      let nameFunc = this.crud.crudDef.name;
      if (nameFunc === undefined){
        nameFunc = '';
      }
      if (formUpdate) {
        data = {
          isEdit: true,
          entity: obj,
          formDef: formUpdate,
          formName: 'formUpdate',
          funcName: nameFunc,
          fields: this.localStorageService.clone(formUpdate.fields),
          handlerFieldSourceData: this.crud.handlerFieldSourceData,
          crud: this.crud
        };
        this.displayCrudModal(data, '-modal update-modal');
      } else if (formRead) {
        data = {
          isRead: true,
          formDef: formRead,
          entity: obj,
          formName: 'formRead',
          funcName: nameFunc,
          fields: this.localStorageService.clone(formRead.fields),
        };
        this.displayCrudModal(data, '-modal read-modal');
      }else if (this.crud.crudDef.dialogs){
        if (this.crud.crudDef.dialogs.read){
          const ref = this;
          data = {
            isEdit: false,
            dialog: this.crud.crudDef.dialogs.read,
            entity: obj,
            formName: 'customFormRead',
            funcName: nameFunc,
            onSubmitActions: (actionDef, entity) => {
              ref.actionDefService.submitAction(actionDef, entity, this.crud.i18nComponent, this.crud.crudDef.dialogConfig).subscribe(r => {
                this.notificationService.notifySuccess(this.crud.translate('success_message'));
               }, e => { }, () => {
                dialogRef.close();
              });
            },
          };
          
          const dialogRef = this.dialog.open(FormGridModalComponent, {
            width: 'auto',
            panelClass: 'control-mat-dialog',
            data: data
          });
          
          dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
          });
        }
      }
    }
  }
  private displayCrudModal(data: any, modalName) {
    if (data) {
      data.funcName = data.funcName ? data.funcName + modalName : modalName;

      const dialogRef = this.dialog.open(CrudModalComponent, {
        width: this.crud.crudDef.dialogConfig &&
          this.crud.crudDef.dialogConfig.width ?
          this.crud.crudDef.dialogConfig.width : '320px',
        panelClass: 'control-mat-dialog',
        data: data
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });
    }
  }

  getFormRead(crudDef: any): FormDef {
    if (crudDef.forms && crudDef.forms.read){
      const form = new FormDef();
      form.fields = crudDef.forms.read;
      return form;
    }else if (crudDef.formsDef && crudDef.formsDef.read){
      return crudDef.formsDef.read;
    }
    return undefined;
  }
  getFormUpdate(crudDef: CrudDef): FormDef {
    if (crudDef.forms && crudDef.forms.update){
      const form = new FormDef();
      form.fields = crudDef.forms.update;
      return form;
    }else if (crudDef.formsDef && crudDef.formsDef.update){
      return crudDef.formsDef.update;
    }
    return undefined;
  }

  getValue(element, attribute, def = null) {
    let obj;
    if (attribute.indexOf('.') > 0) {
      obj = element;
      const props = attribute.split('.');
      props.forEach(attr => {
        obj = obj[attr];
      });
      return obj;
    } else {
      obj = element[attribute];
    }
    if (typeof obj === 'boolean') {
        if (obj) {
          return this.translate('boolean_true');
        }
      return this.translate('boolean_false');
    } else if (obj === 'true') {
      return this.translate('boolean_true');
    } else if (obj === 'false') {
      return this.translate('boolean_false');
    } else if (def != null && def && def.translate && obj){
      const prefix = 'grid_column_value_translate_';
      const attr = attribute.replace('.', '_').toLowerCase();
      const value = obj.toLowerCase();
      const key = prefix + attr + '_' + value;
      return this.translate(key);
    }
    return obj;
  }

  translate(key: string): string{
    let value = key;
    if (this.crud){
      if (this.crud.i18nComponent){
        value = this.crud.i18nComponent.translate(key);
      }
    }
      if (value === key){
      value = super.translate(key);
    }
    return value;
  }

  get datasource(): MatTableDataSource<any> {
    return this._datasource;
  }

  @Input()
  set datasource(datasource: MatTableDataSource<any>) {
    if (datasource) {
      this._datasource = datasource;
      this.statustable.statusChanges.subscribe((statustable) => {
        this.status.emit(statustable);
      });
      setTimeout(() => {
        this._datasource.paginator = this.paginator;
        this._datasource.sort = this.sort;
      }, 1);

      this.rows = [];
      this._datasource.data.forEach((element, index) => {
        const selectable = this.isRowSelectable(element, this.grid.selectCondition);
        this.rows.push(new Row( element, index, false, selectable));
      });

      this.statustable.rows = this.rows;
    }
  }

  isRowSelectable(element: any, displaySelectCondition: DynamicFieldConditionIf): any {
    return this.expressionService.eval(displaySelectCondition, element);
  }

  getRowId(row){
    let id  = this.grid.columnsDef.find(c => c.id);
    id = id ? id.columnDef : 'id';
    return row[id];
  }

  getRow(element) {
    return this.rows.filter(e => e.obj === element)[0];
  }
  isDisableSort(columnKey) {
    const def = this.grid.columnsDef.find(x => x.columnDef === columnKey);
    if (def.sort && def.sort.disabled !== undefined) {
      return def.sort.disabled;
    }
    const sort = this.grid.sortAllColumns;
    return sort === undefined ? true : !sort;
  }

  orderStart(columnKey) {
    const def = this.grid.columnsDef.find(x => x.columnDef === columnKey);
    if (def.sort && def.sort.type !== undefined) {
      return def.sort.type;
    }
    return 'asc';
  }

  getSortDef(columnKey) {
    let sort = !this.grid.sortAllColumns;
    let type;
    const def = this.grid.columnsDef.find(x => x.columnDef === columnKey);
    // En el caso de que el campo tenga definido un sort, tomar la definicion de este
    if (def.sort && def.sort.disabled !== undefined) {
      sort = def.sort.disabled;
      type = def.sort.type;
    }

    const sortDef = {
      disabled: sort === undefined ? true : sort,
      start: type === undefined ? 'asc' : type,
    };

    return sortDef;
  }
  get selects(): boolean {
    let selects = true;
    this.rows.forEach(element => {
      if (!element.select) {
        selects = false;
      }
    });
    return this._selects && selects;
  }

  set selects(select: boolean) {
    this._selects = select;
    if (this.resetSelects) {
      this.resetSelects = false;
    } else {
      this.rows.forEach(element => {
        element.select = this._selects;
      });
    }

  }
  
  @Input() 
  set tabledef(tabledef){
    this.grid = tabledef;
  }

  resetSelectAll() {
    this.resetSelects = true;
    this._selects = false;
  }

  getI18nName(): string {
    return 'crud_table';
  }
}

export class Row {
  rowNumber: number;
  _select: boolean;
  obj: any;
  observer: Subscriber<Row>;
  statusChanges: Observable<Row>;
  selectable: boolean;

  constructor(obj: any , rowNumber, select: boolean, selectable) {
    this._select = select;
    this.selectable = selectable;
    this.obj = obj;
    this.rowNumber = rowNumber;
    this.statusChanges = new  Observable<Row>((observer) => {
      this.observer = observer;
    });
  }

  get select(): boolean {
    return this._select;
  }

  set select(select: boolean) {
    if (this.selectable){
      this._select = select;
      if (this.observer) {
        this.observer.next() ;
      }
    }
  }

}

export class StatusTable<E> {
  _selectedRows: E[];
  _rows: Row[];
  observer: Subscriber<StatusTable<E>>;

  statusChanges = new  Observable<StatusTable<E>>((observer) => {
    this.observer = observer;
  });

  set rows(rows: Row[]) {
    this._rows = rows;
    this._rows.forEach((row) => {
      row.statusChanges.subscribe(() => {
        this.observer.next(this);
      });
    });
    if (this.observer) {
      this.observer.next(this);
    }
  }

  set selects(toSelect: E[]) {
    this._selectedRows = toSelect;
  }

  get selects(): E[] {
    if (this._rows) {
      const filters = this._rows.filter(e => e.select === true);
      const selects = [];
      filters.forEach(element => {
        selects.push(element.obj);
      });
      return selects;
    }
    return null;
  }

  existSelectedItems() {
    const selects = this.selects;
    if (selects) {
      return selects.length > 0;
    }
    return false;
  }


}
