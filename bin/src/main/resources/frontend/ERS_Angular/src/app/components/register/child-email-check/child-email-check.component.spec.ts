import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildEmailCheckComponent } from './child-email-check.component';

describe('ChildEmailCheckComponent', () => {
  let component: ChildEmailCheckComponent;
  let fixture: ComponentFixture<ChildEmailCheckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChildEmailCheckComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChildEmailCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
