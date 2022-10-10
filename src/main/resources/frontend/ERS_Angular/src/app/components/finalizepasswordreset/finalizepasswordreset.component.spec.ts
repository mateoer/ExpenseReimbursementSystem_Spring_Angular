import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalizepasswordresetComponent } from './finalizepasswordreset.component';

describe('FinalizepasswordresetComponent', () => {
  let component: FinalizepasswordresetComponent;
  let fixture: ComponentFixture<FinalizepasswordresetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinalizepasswordresetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinalizepasswordresetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
