import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MngDialogComponent } from './mng-dialog.component';

describe('MngDialogComponent', () => {
  let component: MngDialogComponent;
  let fixture: ComponentFixture<MngDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MngDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MngDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
