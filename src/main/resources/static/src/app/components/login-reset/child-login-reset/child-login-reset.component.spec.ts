import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildLoginResetComponent } from './child-login-reset.component';

describe('ChildLoginResetComponent', () => {
  let component: ChildLoginResetComponent;
  let fixture: ComponentFixture<ChildLoginResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChildLoginResetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChildLoginResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
