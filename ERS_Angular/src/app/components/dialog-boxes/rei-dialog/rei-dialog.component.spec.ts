import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReiDialogComponent } from './rei-dialog.component';

describe('ReiDialogComponent', () => {
  let component: ReiDialogComponent;
  let fixture: ComponentFixture<ReiDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReiDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReiDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
