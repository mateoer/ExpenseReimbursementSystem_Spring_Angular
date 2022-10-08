import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildPasswordCheckComponent } from './child-password-check.component';

describe('ChildPasswordCheckComponent', () => {
  let component: ChildPasswordCheckComponent;
  let fixture: ComponentFixture<ChildPasswordCheckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChildPasswordCheckComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChildPasswordCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
