import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAfterLoginComponent } from './admin-after-login.component';

describe('AdminAfterLoginComponent', () => {
  let component: AdminAfterLoginComponent;
  let fixture: ComponentFixture<AdminAfterLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAfterLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAfterLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
