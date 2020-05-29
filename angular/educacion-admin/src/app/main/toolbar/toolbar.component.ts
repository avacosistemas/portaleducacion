import { Component, ViewEncapsulation } from '@angular/core';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { FuseConfigService } from '@fuse/services/config.service';
import { FuseSidebarService } from '@fuse/components/sidebar/sidebar.service';
import { Injector } from '@angular/core';
import { AbstractComponent } from '../../modules/fwk/core/component/abstract-component.component';
import { environment } from 'environments/environment';
import { AuthService } from '../../modules/fwk/core/service/security/auth.service';
import { User } from '../../modules/fwk/core/model/user';
import { SpinnerService } from '../../modules/fwk/core/module/spinner/service/spinner.service';
import { DialogService } from '../../modules/fwk/core/service/dialog-service/dialog.service';
import { MatDialog } from '@angular/material';
import { GenericHttpService } from '../../modules/fwk/core/service/generic-http-service/generic-http.service';
import { LocalStorageService } from '../../modules/fwk/core/service/local-storage/local-storage.service';
import { HTTP_METHODS } from '../../modules/fwk/core/model/ws-def';

@Component({
    selector   : 'fuse-toolbar',
    templateUrl: './toolbar.component.html',
    styleUrls  : ['./toolbar.component.scss'],
    encapsulation: ViewEncapsulation.None
})

export class FuseToolbarComponent extends AbstractComponent
{

    userStatusOptions: any[];
    languages: any;
    selectedLanguage: any;
    showLoadingBar: boolean;
    horizontalNav: boolean;
    noNav: boolean;
    pageTitle: any;
    urls: any;
    user: User;
    userDetailUrl: string;
    spinnerControl: any;
    constructor(
        private dialog: MatDialog,
        private dialogService: DialogService,
        private spinnerService: SpinnerService,
        private genericHttpService: GenericHttpService,
        private localStorage: LocalStorageService,
        private sidebarService: FuseSidebarService,
        private translateFuse: TranslateService,
        private authService: AuthService,
        injector: Injector
    )
    {
        super(injector);
        this.setUpI18n(    {
            name: 'toolbar',
            lang: 'es',
            dictionary: {
                menu_text: 'sort',
                menu_user_icon: 'account_circle',
                menu_user_item_1: 'Mis Datos',
                menu_user_item_4: 'Cambiar contraseña',
                menu_user_item_5: 'Cerrar sesión',
                
            }
          });
        this.userStatusOptions = [
            {
                'title': 'Online',
                'icon' : 'icon-checkbox-marked-circle',
                'color': '#4CAF50'
            },
            {
                'title': 'Away',
                'icon' : 'icon-clock',
                'color': '#FFC107'
            },
            {
                'title': 'Do not Disturb',
                'icon' : 'icon-minus-circle',
                'color': '#F44336'
            },
            {
                'title': 'Invisible',
                'icon' : 'icon-checkbox-blank-circle-outline',
                'color': '#BDBDBD'
            },
            {
                'title': 'Offline',
                'icon' : 'icon-checkbox-blank-circle-outline',
                'color': '#616161'
            }
        ];

        this.languages = [
            {
                'id'   : 'es',
                'title': 'Spanish',
                'flag' : 'es'
            }
        ];

        this.selectedLanguage = this.languages[0];

        this.router.events.subscribe(
            (event) => {
                if ( event instanceof NavigationStart )
                {
                    this.showLoadingBar = true;
                }
                if ( event instanceof NavigationEnd )
                {
                    this.showLoadingBar = false;
                }
            });

        this.configService.onConfigChanged.subscribe((settings) => {
            this.horizontalNav = settings.layout.navigation === 'top';
            this.noNav = settings.layout.navigation === 'none';
        });
        this.urls = environment;
        this.spinnerControl = this.spinnerService.getControlGlobalSpinner();
	this.user = new User();
        // this.authService.subscribeChangeUser((user) => {
        //     this.user = user;
        // });
    }

    toggleSidebarOpened(key)
    {
        this.sidebarService.getSidebar(key).toggleOpen();
    }

    search(value)
    {
        // Do your search here...
        console.log(value);
    }


    setLanguage(lang)
    {
        // Set the selected language for toolbar
        this.selectedLanguage = lang;

        // Use the selected language for translations
        this.translateFuse.use(lang.id);
    }

    getUsername(){
        if (this.user){
            return this.user.username;
        }
        return '';
    }

    onLogout(){
        this.authService.logout();
    }

    goUserDetails(){
        // this.navigate(environment.URL_CLIENTE_DETAIL, undefined);
        this.showDialogProfile();
    }
    
    showDialogProfile(){
        this.spinnerControl.show();
        this.componentDefService.getByName('usuario_user_detail_definition').subscribe(componentDef => {
            if (componentDef.ws){
                const wsDef = this.localStorage.clone(componentDef.ws);
                wsDef.method = HTTP_METHODS.get;
                this.genericHttpService.callWs(wsDef).subscribe(userdata => {      
                    console.log(userdata);              
                    this.spinnerControl.hide();
                    const dialogRef = this.dialogService.showFormDialog(this.dialog, 
                                                                           componentDef.i18n,
                                                                                componentDef.formsDef.update, 
                                                                                    {width: '470px'},
                                                                                        userdata[0]);
                });
            }
        });
    }

    getI18nName(): string {
        return 'toolbar';
    }
    onInit() {
    }
}
