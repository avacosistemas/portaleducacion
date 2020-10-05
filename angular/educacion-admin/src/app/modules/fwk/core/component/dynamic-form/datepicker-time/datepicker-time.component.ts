import { Component, Injector, OnInit } from '@angular/core';
import { DynamicFieldFormComponent } from '../dynamic-field-form/dynamic-field-form.component';

@Component({
  selector: 'app-datepicker-time',
  templateUrl: './datepicker-time.component.html',
  styleUrls: ['./datepicker-time.component.scss'],
})
export class DatepickerTimeComponent extends DynamicFieldFormComponent implements OnInit  {

  constructor(public injector: Injector) { super(injector); }
  config = {
    firstDayOfWeek: 'su',
    monthFormat: 'MMM, YYYY',
    disableKeypress: false,
    allowMultiSelect: false,
    closeOnSelect: undefined,
    closeOnSelectDelay: 100,
    onOpenDelay: 0,
    weekDayFormat: 'ddd',
    appendTo: document.body,
    drops: 'up',
    opens: 'right',
    showNearMonthDays: true,
    showWeekNumbers: false,
    enableMonthSelector: true,
    format: 'YYYY-MM-DD HH:mm',
    yearFormat: 'YYYY',
    showGoToCurrent: true,
    dayBtnFormat: 'DD',
    monthBtnFormat: 'MMM',
    hours12Format: 'hh',
    hours24Format: 'HH',
    meridiemFormat: 'A',
    minutesFormat: 'mm',
    minutesInterval: 30,
    secondsFormat: 'ss',
    secondsInterval: 1,
    showSeconds: false,
    showTwentyFourHours: true,
    timeSeparator: ':',
    multipleYearsNavigateBy: 10,
    showMultipleYearsNavigation: true,
    locale: 'es-AR',
    // min:'2017-08-29 15:50',
    // minTime:'2017-08-29 15:50'
  };

  ngOnInit() {
    if (!this.parentForm.controls[this.field.key].value) {
      this.parentForm.controls[this.field.key].patchValue(new Date().toJSON().split('T')[0] + ' 00:00');
    }
  }
}
